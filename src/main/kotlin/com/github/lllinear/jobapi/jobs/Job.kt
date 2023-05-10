package com.github.lllinear.jobapi.jobs

import com.github.lllinear.jobapi.abilities.Ability
import org.bukkit.inventory.ItemStack

abstract class Job {
    lateinit var id: String
    lateinit var icon: ItemStack
    lateinit var name: String
    lateinit var description: String

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
}