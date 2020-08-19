package com.example.retrofitdemo

import androidx.lifecycle.ViewModel

class Student(val course: SchoolCourse, val friend:Friend) {
    fun beSmart(){
        course.study()
        friend.hangout()
    }
}
class SchoolCourse{
    fun study(){
        println("I am studying")
    }
}
class Friend{
    fun hangout(){
        println("We are hanging out")
    }
}
class MainViewModel(val course:SchoolCourse,val friend: Friend):ViewModel(){
    fun performAction(){
        println("We are performing action")
    }

}
