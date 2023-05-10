package com.github.lllinear.jobapi

import com.github.lllinear.jobapi.managers.JobManager
import com.github.lllinear.jobapi.managers.UserManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class JobCommandExecutor: CommandExecutor, TabExecutor {
    private val options = listOf("attach", "info")

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        if (args.size == 1) {
            val optionList = ArrayList<String>()
            for (option in options) {
                if (!option.contains(args[0])) {
                    continue
                }
                optionList.add(option)
            }
            return optionList
        } else if ((args[0] == "attach" || args[0] == "info") && args.size == 2) {
            val playerNameList = ArrayList<String>()
            for (player in Bukkit.getOnlinePlayers()) {
                if (!player.name.contains(args[1])) {
                    continue
                }
                playerNameList.add(player.name)
            }
            return playerNameList.toMutableList()
        } else if ((args[0] == "attach" || args[0] == "info") && args.size == 3) {
            val jobIdList = ArrayList<String>()
            for (jobId in JobManager.getJobMap().keys) {
                if (!jobId.contains(args[2])) {
                    continue
                }
                jobIdList.add(jobId)
            }
            return jobIdList
        }

        return null
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("lllinear.command.job")) {
            sender.sendMessage("${ChatColor.RED}You don't have enough permission to use this command.")
            return false
        }

        if (!options.contains(args[0])) {
            sender.sendMessage("${ChatColor.GOLD}/job set <playerName> <jobId> - ${ChatColor.WHITE} Set player's job.")
            sender.sendMessage("${ChatColor.GOLD}/job info <playerName> <jobId> - ${ChatColor.WHITE} Show info gui to player.")
        }

        if (args[0] == "attach") {
            if (args.size < 3) {
                sender.sendMessage("${ChatColor.GOLD}/job set <playerName> <jobId> - ${ChatColor.WHITE} Set player's job.")
                return false
            }

            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage("${ChatColor.RED}Player not found.")
                return false
            }

            val player = Bukkit.getPlayer(args[1])!!
            val job = JobManager.getJob(args[2])
            UserManager.setJob(player, job, true)
            sender.sendMessage("Set ${player.name}'s job to ${job.name}")
            return true
        } else if (args[0] == "info") {
            if (args.size < 3) {
                sender.sendMessage("${ChatColor.GOLD}/job info <playerName> <jobId> - ${ChatColor.WHITE} Show info gui to player.")
                return false
            }

            if (Bukkit.getPlayer(args[1]) == null) {
                sender.sendMessage("${ChatColor.RED}Player not found.")
                return false
            }

            val name = Bukkit.getPlayer(args[1])!!.name
            UserManager.showJobInfo(Bukkit.getPlayer(name)!!, args[2])
            return true
        }

        return true
    }
}