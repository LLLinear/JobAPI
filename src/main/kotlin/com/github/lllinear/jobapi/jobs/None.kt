package com.github.lllinear.jobapi.jobs

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class None : Job() {
    init {
        id = "jobapi:none"
        icon = ItemStack(Material.BARRIER)
        name = "None"
        description = listOf()
    }
}