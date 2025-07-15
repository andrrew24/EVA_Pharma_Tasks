package Models

class Subject (val name:String, val grade:Int) {
    override fun toString(): String = "$name: $grade"
}