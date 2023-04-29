package com.github.lllinear.jobapi.jobs

import com.github.lllinear.jobapi.abilities.Ability
import org.bukkit.inventory.ItemStack

abstract class Job {
    private val abilityList = ArrayList<Ability>()

    /**
     * @return AbilityList
     */
    fun getAbilityList(): List<Ability> {
        return abilityList
    }

    /**
     * @param ability Job to register
     * @return True if add successful (if already existed, return False)
     */
    fun addAbility(ability: Ability): Boolean {
        if (abilityList.contains(ability)) {
            return false
        }

        abilityList.add(ability)
        return true
    }

    /**
     * @return Job id
     */
    abstract fun getId(): String

    /**
     * @return Job icon
     */
    abstract fun getIcon(): ItemStack

    /**
     * @return Job name
     */
    abstract fun getName(): String

    /**
     * @return Job description
     */
    abstract fun getDescription(): String
}