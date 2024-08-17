package hr.foi.rampu.mementojc.helpers

import hr.foi.rampu.mementojc.entities.Task
import hr.foi.rampu.mementojc.entities.TaskCategory
import java.util.Date

object MockDataLoader {

    fun getDemoData() : List<Task> = listOf(
        Task("Submit seminar paper", Date(), TaskCategory("RAMPU", "#000080"), false),
        Task("Prepare for exercises", Date(), TaskCategory("RPP", "#FF0000"), false),
        Task("Rally a project team", Date(), TaskCategory("RAMPU", "#000080"), false),
        Task("Connect to server (SSH)", Date(), TaskCategory("RWA", "#CCCCCC"), false)
    )
}
