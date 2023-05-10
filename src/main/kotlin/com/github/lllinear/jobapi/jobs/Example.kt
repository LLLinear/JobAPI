package com.github.lllinear.jobapi.jobs

import com.github.lllinear.jobapi.abilities.ExampleAbility
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Example: Job() {
    init {
        id = "jobapi:example"
        icon = ItemStack(Material.BOOK)
        name = "Example"
        description = "Example Job Class"

        addAbility(ExampleAbility())
    }
}