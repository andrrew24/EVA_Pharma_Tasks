package Data

import Models.Student

object Database {

    private val studentsList : MutableList<Student> = mutableListOf()

    private var lastStudentId : Int = 0
    fun getLastStudentId(): Int = lastStudentId



    fun addStudent(student: Student) {
        studentsList.add(student)
        lastStudentId++
    }

    fun getAllStudents(): List<Student> = studentsList

    fun getRankedStudents(): List<Student> = studentsList.sortedByDescending { it.averageMark }

    fun getStudentByName(name: String) :Student? = studentsList.find { it.name == name }


}