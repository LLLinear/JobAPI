package com.github.lllinear.jobapi.managers

import com.github.lllinear.jobapi.jobs.Job
import com.github.lllinear.jobapi.jobs.None

class JobManager {
    companion object {
        private val jobMap = HashMap<String, Job>()

        /**
         * @return JobMap (id => job)
         */
        fun getJobMap(): Map<String, Job> {
            return jobMap
        }

        /**
         * Use this function in onLoad not in onEnable
         *
         * @param job Job to register
         * @return True if register successful (if already registered, return False)
         */
        fun registerJob(job: Job): Boolean {
            val id = job.id
            if (jobMap.containsKey(id)) {
                return false
            }

            jobMap[id] = job
            return true
        }

        /**
         * @param id Job id
         * @return True if unRegister successful (if isn't registered, return False)
         */
        fun unRegisterJob(id: String): Boolean {
            if (!jobMap.containsKey(id)) {
                return false
            }

            jobMap.remove(id)
            return true
        }

        /**
         * @param id Job id
         * @return Job (if isn't registered id, return None)
         */
        fun getJob(id: String): Job {
            if (!jobMap.containsKey(id)) {
                return None()
            }

            return jobMap[id]!!
        }
    }
}