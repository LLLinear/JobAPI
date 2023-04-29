package com.github.lllinear.jobapi.jobs

import com.github.lllinear.jobapi.abilities.ExampleAbility
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Example: Job() {
    init {
        addAbility(ExampleAbility())
    }

    override fun getId(): String {
        return "jobapi:example"
    }

    override fun getIcon(): ItemStack {
        return ItemStack(Material.IRON_SWORD)
    }

    override fun getName(): String {
        return "Example"
    }

    override fun getDescription(): String {
        return "Fight with sword."
    }
}