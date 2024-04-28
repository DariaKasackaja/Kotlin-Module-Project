import java.util.Scanner

class Container<T : Element>(val name: String, var elements: MutableList<T>) {

    var menu: MutableList<String> = mutableListOf()
    companion object {
        val input = Scanner(System.`in`)
    }
    fun start( add: () -> Unit,
               select:(index:Int) -> Unit,
               typeElement:String = ""){
        while(true) {
            createMenu(typeElement)
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

    fun createMenu( typeElement:String ){
        menu.clear()
        menu.add("Создать $typeElement")
        elements.forEach{ element ->  menu.add(element.name)}
        menu.add("Выход")
    }

    fun printMenu( ){
        println( name )
        menu.forEachIndexed(){ index, element -> println(" $index. $element") }
    }

}