package hr.foi.rampu.mementojc.entities

import java.util.Date

data class Task(
    val name : String,
    val dueDate : Date,
    val category : TaskCategory,
    val completed : Boolean
)
