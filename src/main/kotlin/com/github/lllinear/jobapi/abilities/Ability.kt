package com.github.lllinear.jobapi.abilities

import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

abstract class Ability {
    /**
     * @param event PlayerInteractEvent
     */
    open fun onClick(event: PlayerInteractEvent) {}

    /**
     * @param event BlockPlaceEvent
     */
    open fun onPlace(event: BlockPlaceEvent) {}

    /**
     * @param event BlockBreakEvent
     */
    open fun onBreak(event: BlockBreakEvent) {}

    /**
     * @param event BlockFishEvent
     */
    open fun onFishing(event: PlayerFishEvent) {}

    /**
     * @param event EntityDamageEvent
     */
    open fun onAttack(event: EntityDamageByEntityEvent) {}

    /**
     * @param event ProjectileLaunchEvent
     */
    open fun onLaunch(event: ProjectileLaunchEvent) {}

    /**
     * @param event ProjectileHitEvent
     */
    open fun onHit(event: ProjectileHitEvent) {}

    /**
     * @param event EntityDamageEvent
     */
    open fun onDamage(event: EntityDamageEvent) {}

    /**
     * @param event EntityDeathEvent
     */
    open fun onKill(event: EntityDeathEvent) {}

    /**
     * @param event EntityDeathEvent
     */
    open fun onDeath(event: EntityDeathEvent) {}

    /**
     * @return ability icon
     */
    abstract fun getIcon(): ItemStack

    /**
     * @return ability name
     */
    abstract fun getName(): String

    /**
     * @return ability description
     */
    abstract fun getDescription(): String
}