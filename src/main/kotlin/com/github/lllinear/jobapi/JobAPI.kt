package com.github.lllinear.jobapi

import com.github.lllinear.jobapi.jobs.example.Example
import com.github.lllinear.jobapi.jobs.None
import com.github.lllinear.jobapi.managers.JobManager
import com.github.lllinear.jobapi.managers.UserManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.plugin.java.JavaPlugin

class JobAPI: JavaPlugin(), Listener {
    companion object {
        private lateinit var plugin: JobAPI

        fun getPlugin(): JobAPI {
            return plugin
        }
    }

    override fun onLoad() {
        JobManager.registerJob(None())
        JobManager.registerJob(Example())
    }

    override fun onEnable() {
        plugin = this

        server.pluginManager.registerEvents(this, this)

        getCommand("job")!!.setExecutor(JobCommandExecutor())

        for (player in Bukkit.getOnlinePlayers()) {
            val name = player.name
            val path = "${name}.job"
            UserManager.setJob(player, JobManager.getJob(config.get(path) as String))
        }
    }

    @EventHandler
    private fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val name = player.name
        val path = "${name}.job"
        if (config.contains(path) && UserManager.getJob(name).id != None().id) {
            UserManager.setJob(player, JobManager.getJob(config.get(path) as String))
        }

        val job = UserManager.getJob(name)
        if (job.id != None().id) {
            for (ability in job.getAbilityList()) {
                ability.onJoin(event)
            }
        }
    }

    @EventHandler
    private fun onClick(event: PlayerInteractEvent) {
        val name = event.player.name
        val job = UserManager.getJob(name)
        if (job.id != None().id) {
            for (ability in job.getAbilityList()) {
                ability.onClick(event)
            }
        }
    }

    @EventHandler
    private fun onPlace(event: BlockPlaceEvent) {
        val name = event.player.name
        val job = UserManager.getJob(name)
        if (job.id != None().id) {
            for (ability in job.getAbilityList()) {
                ability.onPlace(event)
            }
        }
    }

    @EventHandler
    private fun onBreak(event: BlockBreakEvent) {
        val name = event.player.name
        val job = UserManager.getJob(name)
        if (job.id != None().id) {
            for (ability in job.getAbilityList()) {
                ability.onBreak(event)
            }
        }
    }

    @EventHandler
    private fun onFishing(event: PlayerFishEvent) {
        val name = event.player.name
        val job = UserManager.getJob(name)
        if (job.id != None().id) {
            for (ability in job.getAbilityList()) {
                ability.onFishing(event)
            }
        }
    }

    @EventHandler
    private fun onDamage(event: EntityDamageEvent) {
        val entity = event.entity
        if (entity is Player) {
            val name = entity.name
            val job = UserManager.getJob(name)
            if (job.id != None().id) {
                for (ability in job.getAbilityList()) {
                    ability.onDamage(event)
                }
            }
        }

        if (event is EntityDamageByEntityEvent) {
            val damager = event.damager
            if (damager is Player) {
                val name = damager.name
                val job = UserManager.getJob(name)
                if (job.id != None().id) {
                    for (ability in job.getAbilityList()) {
                        ability.onAttack(event)
                    }
                }
            }
        }
    }

    @EventHandler
    private fun onLaunch(event: ProjectileLaunchEvent) {
        val shooter = event.entity.shooter
        if (shooter is Player) {
            val name = shooter.name
            val job = UserManager.getJob(name)
            if (job.id != None().id) {
                for (ability in job.getAbilityList()) {
                    ability.onLaunch(event)
                }
            }
        }
    }

    @EventHandler
    private fun onHit(event: ProjectileHitEvent) {
        val shooter = event.entity.shooter
        if (shooter is Player) {
            val name = shooter.name
            val job = UserManager.getJob(name)
            if (job.id != None().id) {
                for (ability in job.getAbilityList()) {
                    ability.onHit(event)
                }
            }
        }
    }

    @EventHandler
    private fun onDeath(event: EntityDeathEvent) {
        val entity = event.entity
        if (entity is Player) {
            val name = entity.name
            val job = UserManager.getJob(name)
            if (job.id != None().id) {
                for (ability in job.getAbilityList()) {
                    ability.onDeath(event)
                }
            }
        }

        if (entity.lastDamageCause != null && entity.killer != null && entity.killer is Player) {
            val killer = entity.killer!!
            val name = killer.name
            val job = UserManager.getJob(name)
            if (job.id != None().id) {
                for (ability in job.getAbilityList()) {
                    ability.onKill(event)
                }
            }
        }
    }

    @EventHandler
    private fun onRespawn(event: PlayerRespawnEvent) {
        val name = event.player.name
        val job = UserManager.getJob(name)
        if (job.id != None().id) {
            for (ability in job.getAbilityList()) {
                ability.onRespawn(event)
            }
        }
    }

    override fun onDisable() {
        saveConfig()
    }
}