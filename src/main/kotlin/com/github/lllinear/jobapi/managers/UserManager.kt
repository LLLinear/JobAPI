package com.github.lllinear.jobapi.managers

import com.github.lllinear.jobapi.JobAPI
import com.github.lllinear.jobapi.jobs.Job
import com.github.lllinear.jobapi.jobs.None
import org.bukkit.entity.Player

class UserManager {
    companion object {
        private val userJobMap = HashMap<String, Job>()

        /***
         * @param name Player name
         * @return Player job
         */
        fun getJob(name: String): Job {
            if (!userJobMap.containsKey(name)) {
                return None()
            }

            return userJobMap[name]!!
        }

        /***
         * @param player Player
         * @return Player job
         */
        fun getJob(player: Player): Job {
            return getJob(player.name)
        }

        /***
         * @param name Player name
         * @param job Job
         * @param isConfigChange If True, change config data
         */
        fun setJob(name: String, job: Job, isConfigChange: Boolean = false) {
            userJobMap[name] = job

            if (isConfigChange) {
                JobAPI.getPlugin().config.set("$name.job", job.id)
            }
        }

        /***
         * @param player Player
         * @param job Job
         * @param isConfigChange If True, change config data
         */
        fun setJob(player: Player, job: Job, isConfigChange: Boolean = false) {
            setJob(player.name, job, isConfigChange)
        }
    }
}