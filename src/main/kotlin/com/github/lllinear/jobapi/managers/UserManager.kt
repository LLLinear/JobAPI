package com.github.lllinear.jobapi.managers

import com.github.lllinear.jobapi.JobAPI
import com.github.lllinear.jobapi.jobs.Job
import com.github.lllinear.jobapi.jobs.None
import org.bukkit.*
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


class UserManager {
    companion object {
        private val userJobMap = HashMap<String, Job>()

        /***
         * @param name Player name
         * @return Player job
         */
        fun getJob(name: String): Job {
            if (!userJobMap.containsKey(name)) {
                return None()
            }

            return userJobMap[name]!!
        }

        /***
         * @param player Player
         * @return Player job
         */
        fun getJob(player: Player): Job {
            return getJob(player.name)
        }

        /***
         * @param player Player
         * @param job Job
         * @param isConfigChange If True, change config data
         */
        fun setJob(player: Player, job: Job, isConfigChange: Boolean = false) {
            val name = player.name
            val newJob = job.clone()
            userJobMap[name] = newJob

            if (isConfigChange) {
                JobAPI.getPlugin().config.set("$name.job", newJob.id)
            }

            if (newJob.id != None().id) {
                for (ability in newJob.getAbilityList()) {
                    ability.onAttach(player)
                }
            }
        }

        /***
         * @param player Player
         * @param id Job id
         */
        fun showJobInfo(player: Player, id: String) {
            JobInfoInventory(player, JobManager.getJob(id))
        }
    }
}

class JobInfoInventory(private val player: Player, private val job: Job): Listener {
    private val inv = Bukkit.createInventory(player, 9, job.displayName!!)

    private var page = 0

    init {
        val nextItem = ItemStack(Material.PAPER)
        val nextItemMeta = nextItem.itemMeta!!
        nextItemMeta.setDisplayName("${ChatColor.BOLD}▶")
        nextItem.itemMeta = nextItemMeta
        inv.setItem(8, nextItem)

        setPage()

        player.openInventory(inv)

        Bukkit.getPluginManager().registerEvents(this, JobAPI.getPlugin())
    }

    private fun setPage() {
        if (page == 0) {
            val item = job.icon.clone()
            val itemMeta = item.itemMeta!!
            itemMeta.setDisplayName(job.displayName)
            itemMeta.lore = job.description
            item.itemMeta = itemMeta
            inv.setItem(0, item)
        } else {
            val prevItem = ItemStack(Material.PAPER)
            val prevItemMeta = prevItem.itemMeta!!
            prevItemMeta.setDisplayName("${ChatColor.BOLD}◀")
            prevItem.itemMeta = prevItemMeta
            inv.setItem(0, prevItem)
        }

        for (i in 1..7) {
            inv.setItem(i, ItemStack(Material.AIR))
        }

        for (i in 0..6) {
            val abilityList = job.getAbilityList()
            if (abilityList.size <= page * 7 + i) {
                break
            }
            val ability = abilityList[page * 7 + i]
            val item = ability.icon.clone()
            val itemMeta = item.itemMeta!!
            itemMeta.setDisplayName(ability.displayName)
            itemMeta.lore = ability.description
            item.itemMeta = itemMeta

            inv.setItem(i + 1, item)
        }
    }

    @EventHandler
    private fun onClick(event: InventoryClickEvent) {
        if (event.clickedInventory != inv) {
            return
        }

        event.isCancelled = true

        var maxPage = job.getAbilityList().size / 7
        maxPage = if (job.getAbilityList().size % 7 == 0) maxPage - 1 else maxPage

        if (event.slot == 0 && page > 0) {
            page--
            setPage()
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 1.0f)
        } else if (event.slot == 8 && page < maxPage) {
            page++
            setPage()
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_HARP, 1.0f, 1.0f)
        }
    }

    @EventHandler
    private fun onDrag(event: InventoryDragEvent) {
        if (event.inventory == inv) {
            event.isCancelled = true
        }
    }

    @EventHandler
    private fun onClose(event: InventoryCloseEvent) {
        if (event.inventory == inv) {
            HandlerList.unregisterAll(this)
        }
    }
}