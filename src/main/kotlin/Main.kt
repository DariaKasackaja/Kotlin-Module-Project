import java.util.Scanner

fun main(args: Array<String>) {

    println("Добро пожаловать в \"Заметки\"!")

    var elements: MutableList<Archive> = mutableListOf()
    val container = Сontainer("Архивы", elements)
    container.start(add = { ->
        println("Введите название архива.")
        val name = Scanner(System.`in`).nextLine()
        elements.add(Archive(name)) },
        select = {index ->
            println("Выбран архив ${elements[index - 1].name}")
            elements[index - 1].open()
        })
}

class Сontainer<T : Element>(val name: String, var elements: MutableList<T>) {

    var menu: MutableList<String> = mutableListOf()
    companion object {
        val input = Scanner(System.`in`)
    }
    fun start( add: () -> Unit,
               select:(index:Int) -> Unit ){
        while(true) {
            createMenu()
            printMenu()
            println("Выберете пункт меню. Для выбора используйте числа от 0 до ${menu.size - 1}.")
            val menuItem = input.nextLine().toIntOrNull()

            when ( menuItem ) {
                null -> { println("Введенное значение не является числом.")}
                0 -> add()
                in 1 .. menu.size - 2 -> select( menuItem )
                menu.size - 1 -> return
                else -> println("Нет пункта меню с номером $menuItem. Введите корректный номер.")
            }
        }
    }

    fun createMenu( ){
        menu.clear()
        menu.add("Создать")
        elements.forEach{ element ->  menu.add(element.name)}
        menu.add("Выход")
    }

    fun printMenu( ){
        println( name )
        menu.forEachIndexed(){ index, element -> println(" $index. $element") }
    }

}


abstract class Element(val name: String)

class Archive( name: String) : Element( name ){
    var elements: MutableList<Note> = mutableListOf()
    var container = Сontainer("Архив $name", elements)

    fun open() {
        container.start ( add = {
            println("Введите название заметки.")
            val name = Scanner(System.`in`).nextLine()
            println("Введите текст заметки.")
            val text = Scanner(System.`in`).nextLine()
            elements.add(Note(name, text))
        },
            select = { index->
                println("Заметка ${elements[index - 1].name}")
                println(" ${elements[index - 1].text}")
            })
    }
}

class Note(name: String,  val text: String ) : Element( name )