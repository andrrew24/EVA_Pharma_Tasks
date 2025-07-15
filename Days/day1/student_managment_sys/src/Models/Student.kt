package Models

class Student(val id :Int,val name:String, val subjects:List<Subject>) {
    val averageMark = (subjects.sumOf { it.grade } / subjects.size)

    val grade = when (averageMark){
        in 85..100 -> "A"
        in 70..84 -> "B"
        in 60..69 -> "C"
        else -> "F"
    }

    override fun toString(): String {
        return """
            ID: $id
            Name: $name
            Subjects : $subjects
            Average: $averageMark
            Grade: $grade
        """.trimIndent()
    }
}