package com.github.lllinear.jobapi.jobs.example

import com.github.lllinear.jobapi.jobs.Job
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Example: Job() {
    init {
        id = "jobapi:example"
        icon = ItemStack(Material.BOOK)
        name = "Example"
        description = listOf("Example Job Class", "이렇게 적으면 되나?")

        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
        addAbility(ExampleAbility())
    }
}