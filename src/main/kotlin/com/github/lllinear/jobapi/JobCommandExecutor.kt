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
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        if (args.size == 1) {
            return mutableListOf("set")
        } else if (args[0] == "set" && args.size == 2) {
            val playerNameList = ArrayList<String>()
            for (player in Bukkit.getOnlinePlayers()) {
                playerNameList.add(player.name)
            }
            return playerNameList.toMutableList()
        } else if (args[0] == "set" && args.size == 3) {
            return JobManager.getJobMap().keys.toMutableList()
        }

        return null
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("lllinear.command.job")) {
            sender.sendMessage("${ChatColor.RED}You don't have enough permission to use this command.")
            return false
        }

        if (args.size < 3 || args[0] == null || args[0] != "set" || args[1] == null || args[2] == null) {
            sender.sendMessage("${ChatColor.GOLD}/job set <playerName> <jobId> - ${ChatColor.WHITE} Set player's job.")
            return false
        }

        if (Bukkit.getPlayer(args[1]) == null) {
            sender.sendMessage("${ChatColor.RED}Player not found.")
            return false
        }

        if (JobManager.getJob(args[2]) == null) {
            sender.sendMessage("${ChatColor.RED}Job not found.")
            return false
        }

        val name = Bukkit.getPlayer(args[1])!!.name
        val job = JobManager.getJob(args[2])!!
        UserManager.setJob(name, job, true)
        sender.sendMessage("Set $name's job to ${job.getName()}")
        return true
    }
}