package com.github.lllinear.jobapi.abilities

import org.bukkit.ChatColor

enum class AbilityType(val displayName: String) {
    PASSIVE("${ChatColor.GREEN}PASSIVE"), ACTIVE("${ChatColor.RED}ACTIVE")
}

class DescriptionHelper(var type: AbilityType = AbilityType.PASSIVE, var description: List<String>? = null) {
    private val customTags = HashMap<String, String>()

    /***
     * @param key Key (color code : &)
     * @param value Value (color code : &)
     */
    fun addTag(key: String, value: String) {
        customTags[key] = value
    }

    /***
     * @return Item lore
     */
    fun toDescription(): List<String> {
        val result = ArrayList<String>()
        if (customTags.size > 0) {
            customTags.forEach { (key, value) ->
                result.add(ChatColor.translateAlternateColorCodes('&', "&f$key &r&f$value"))
            }
        }

        result.add("")

        if (description != null && description!!.isNotEmpty()) {
            for (str in description!!) {
                result.add(ChatColor.translateAlternateColorCodes('&', "&f$str"))
            }

            result.add("")
        }

        result.add(type.displayName)
        return result
    }
}