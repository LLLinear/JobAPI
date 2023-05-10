package com.github.lllinear.jobapi.jobs.example

import com.github.lllinear.jobapi.abilities.Ability
import com.github.lllinear.jobapi.abilities.AbilityType
import com.github.lllinear.jobapi.abilities.DescriptionHelper
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class ExampleAbility: Ability() {
    init {
        icon = ItemStack(Material.NETHER_STAR)
        name = "Example Ability"
        val helper = DescriptionHelper(AbilityType.PASSIVE, listOf())
        helper.addTag("&a&l쿨다운", "15")
        helper.addTag("&c&l대미지", "30")
        description = helper.toDescription()
    }

    override fun onClick(event: PlayerInteractEvent) {
        Bukkit.broadcastMessage("OnClick")
    }

    override fun onPlace(event: BlockPlaceEvent) {
        Bukkit.broadcastMessage("OnPlace")
    }

    override fun onBreak(event: BlockBreakEvent) {
        Bukkit.broadcastMessage("OnBreak")
    }

    override fun onFishing(event: PlayerFishEvent) {
        Bukkit.broadcastMessage("OnFishing")
    }

    override fun onAttack(event: EntityDamageByEntityEvent) {
        Bukkit.broadcastMessage("OnAttack")
    }

    override fun onLaunch(event: ProjectileLaunchEvent) {
        Bukkit.broadcastMessage("OnLaunch")
    }

    override fun onHit(event: ProjectileHitEvent) {
        Bukkit.broadcastMessage("OnHit")
    }

    override fun onDamage(event: EntityDamageEvent) {
        Bukkit.broadcastMessage("OnDamage")
    }

    override fun onKill(event: EntityDeathEvent) {
        Bukkit.broadcastMessage("OnKill")
    }

    override fun onDeath(event: EntityDeathEvent) {
        Bukkit.broadcastMessage("OnDeath")
    }

    override fun onRespawn(event: PlayerRespawnEvent) {
        Bukkit.broadcastMessage("OnRespawn")
    }

    override fun onJoin(event: PlayerJoinEvent) {
        Bukkit.broadcastMessage("OnJoin")
    }

    override fun onAttach(player: Player) {
        Bukkit.broadcastMessage("OnAttach")
    }
}