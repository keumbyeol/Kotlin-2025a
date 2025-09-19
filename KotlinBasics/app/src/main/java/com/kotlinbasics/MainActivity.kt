package com.kotlinbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kotlinbasics.ui.theme.KotlinBasicsTheme
import kotlinx.serialization.descriptors.PrimitiveKind

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBasicsTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        //week02Variables()
        //week02Functions()
        //week03Classes()
        week03Collections()
    }
}

private fun week02Variables(){
//    println("week 02 : Variables")
//
//    val courseName = "Mobile Programming"
//    //courseName="IoT Programming"은 error
//
//    var week=2
//    println("Course : $courseName")
//    println("week : $week")

    println("Kotlin Variables ==")
    val name ="Android"
    var version=8

    println("Hello $name $version")

    val age: Int = 21
    val height: Double=162.1
    val isStudent: Boolean=true

    println("Age : $age height : $height Student : $isStudent")

    var nickname: String?=null
    nickname = "mirae"

    println("Nickname : $nickname ${nickname?.length}")

}

private fun week02Functions(){
//    println("Week 02: Functions")
//
//    fun greet(name:String)="Hello, $name"
//
//    println(greet("Android developer")
    println("== Kotlin Functions ==")

    fun greet(name: String): String{
        return "Hello, $name!"
    }

    fun add(a: Int, b: Int) = a + b

    fun introduce(name: String, age: Int=19){
        println("My name is $name and I'm $age years old")
    }

    println(greet("Kotlin"))
    println("Sum :${add(5, -71)}")
    introduce("kim", 7)
    introduce("Park")

}

private fun week03Classes(){
    println("==Kotlin Classes ==")

    class Student{
        var name: String = ""
        var age: Int = 0

        fun introduce(){
            println("Hi, I'm  $name and I'm $age years old ")
        }
    }

    val student = Student()
    student.name = "Mirae"
    student.age = 21
    student.introduce()

    data class Person(val name: String, val age: Int)

    val person1 = Person("Kim", 23)
    val person2 = Person("Kim", 23)

    println("Person 1 : $person1")
    println("Equal? ${person1 == person2}")

}

private fun week03Collections(){
    println("==Kotlin Collections ==")

    val fruits = listOf("apple","banana","orange")
    val mutableFruits = mutableListOf("kiwi","watermelon")

    //fruits.add("kiwi") 수정 불가능
    println("Fruits : $fruits")

    mutableFruits.add("banana")
    println("Mutable Fruits : $mutableFruits")

    val scores = mapOf("kim" to 100, "Park" to 97,"Lee" to 99)
    println("Scores : $scores")

    for(fruit in fruits) {
        println("I like $fruit")
    }

    scores.forEach{(name, score) -> println("$name scored $score")}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinBasicsTheme {
        Greeting("Android")
    }
}
