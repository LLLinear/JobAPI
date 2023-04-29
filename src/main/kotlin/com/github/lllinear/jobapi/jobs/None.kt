package com.github.lllinear.jobapi.jobs

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class None: Job() {
    override fun getId(): String {
        return "jobapi:none"
    }

    override fun getIcon(): ItemStack {
        return ItemStack(Material.BARRIER)
    }

    override fun getName(): String {
        return "None"
    }

    override fun getDescription(): String {
        return ""
    }

}