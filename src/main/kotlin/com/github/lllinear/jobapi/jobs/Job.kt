package com.github.lllinear.jobapi.jobs

import com.github.lllinear.jobapi.abilities.Ability
import org.bukkit.inventory.ItemStack

abstract class Job: Cloneable {
    lateinit var id: String
    lateinit var icon: ItemStack
    lateinit var name: String
    var displayName: String? = null
        get() {
            if (field == null) {
                return name
            }
            return field
        }
    lateinit var description: List<String>

    private val abilityList = ArrayList<Ability>()

    /**
     * @return AbilityList
     */
    fun getAbilityList(): List<Ability> {
        return abilityList
    }

    /**
     * @param ability Job to register
     */
    fun addAbility(ability: Ability) {
        abilityList.add(ability.clone())
    }

    public override fun clone(): Job {
        return super.clone() as Job
    }
}