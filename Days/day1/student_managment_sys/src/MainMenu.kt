import Data.Database
import Models.Student
import Models.Subject
import java.util.Scanner

open class Menu{
    val scanner = Scanner(System.`in`)
}


object SubjectMenu : Menu(){


    fun getStudentSubjects(noSubject: Int) : MutableList<Subject>{
        val subjects: MutableList<Subject> = mutableListOf()

        for (i in 1..noSubject){
            subjects.add(Subject(getSubjectName(subjectNumber = i),getSubjectMark(subjectNumber = i)))
        }

        return subjects
    }
    fun getNumberofSubjectsPerStudent(): Int {
        print("Enter Number of Subjects (min is 3) : ")
        val noSubjects = scanner.nextLine().toIntOrNull()
        return if (noSubjects == null || noSubjects <= 2) {
            println("Invalid Number of Subjects, Try Again")
            getNumberofSubjectsPerStudent()
        } else {
            noSubjects ?: 1
        }
    }

    fun getSubjectMark(subjectNumber:Int):Int{
        print("Enter the Subject $subjectNumber Mark: ")
        val subjectMark = scanner.nextLine().toIntOrNull()
       return if (subjectMark == null || subjectMark < 0 || subjectMark > 100){
            println("Invalid Input, Try Again")
            getSubjectMark(subjectNumber)
        }else{
         subjectMark
    }
    }
    fun getSubjectName (subjectNumber:Int):String{
        print("Enter the Subject $subjectNumber Name: ")
        val subjectName = scanner.nextLine()
        return if (subjectName.isNullOrBlank()){
            println("Invalid Input, Try Again")
            getSubjectName(subjectNumber)
        }
        else{ subjectName}
    }
}

object StudentMenu : Menu(){
    fun displayAllStudents(){
        val allStudents = Database.getAllStudents()
        if (allStudents.isEmpty()){
            println("No students Added Yet")
        }else{
            allStudents.forEach {
                println(it)
            }
        }
    }

    fun searchStudentByName(){
        print("Enter the name of the student: ")
        val studentName = scanner.nextLine()
        if (studentName.isNullOrBlank()){
            println("Invalid Name!")
            searchStudentByName()
        }else{
            if (Database.getStudentByName(studentName) != null) println("Student with name $studentName exists!")
            else println("Student with name $studentName does not exist!")
        }
    }

    fun displayStudentRanks(){
        val rankedStudents = Database.getRankedStudents()
        if (Database.getRankedStudents().isEmpty()){
            println("No students Added Yet")
        }else{
                rankedStudents.forEachIndexed { index, student ->  println("${index + 1}. ${student.name} ")  }
        }
    }

    fun addMultipleStudents(){
        for (i in 1..getNumberOfStudents()){
            Database.addStudent(addStudent(studentId =  Database.getLastStudentId(), studentNum = i))
        }
    }
    fun addStudent(studentId :Int,studentNum:Int):Student{
        println("Student#$studentNum")

        val name = getStudentName()

        val noSubject = SubjectMenu.getNumberofSubjectsPerStudent()

        val studentSubjects = SubjectMenu.getStudentSubjects(noSubject)

        return Student( id =  studentId, name = name, subjects =  studentSubjects)
    }

    fun getStudentName():String{
        print("Enter the Student Name: ")
        val name = scanner.nextLine()
        return  if (name.isNullOrBlank()){
            println("Invalid Input, Try Again")
            getStudentName()
        }else{
            name
        }

    }

    fun getNumberOfStudents(): Int{
        print("Enter Number of Students: ")
        val noStudents = scanner.nextLine().toIntOrNull()
       return if (noStudents == null || noStudents <= 0) {
            println("Invalid Number of Students, Try Again")
            getNumberOfStudents()
        }else{
            return noStudents
        }
    }
}



object MainMenu : Menu() {
    fun startMenu(){
        while (true) {
            println("\n==== Student Management System ====")
            println("1. Add Single/Multiple Student/s")
            println("2. View All Students")
            println("3. View Students' Rank")
            println("4. Search Student By Name")
            println("5. Exit")
            print("Enter your choice: ")

            when (scanner.nextLine().toIntOrNull()) {
                1 -> StudentMenu.addMultipleStudents()
                2 -> StudentMenu.displayAllStudents()
                3 -> StudentMenu.displayStudentRanks()
                4 -> StudentMenu.searchStudentByName()
                5 -> break
                else -> {
                    println("Invalid Choice, Try Again")
                    startMenu()
                }
            }
        }

    }
}