package com.github.lllinear.jobapi.abilities

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Item
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.*
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class ExampleAbility: Ability() {
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

    override fun getIcon(): ItemStack {
        return ItemStack(Material.IRON_SWORD)
    }

    override fun getName(): String {
        return "Example Name"
    }

    override fun getDescription(): String {
        return "Example Description"
    }

}