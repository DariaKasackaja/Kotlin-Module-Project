import java.util.Scanner

fun main(args: Array<String>) {

    println("Добро пожаловать в \"Заметки\"!")
    var elements: MutableList<Archive> = mutableListOf()
    val container = Container("Архивы", elements)
    container.start(add = { -> elements
        .add( Archive( getUserData("Введите название архива."))) },
        select = { index ->
             elements[index - 1].open()
        },
        "архив")
}

fun getUserData( question :String ) : String {
    var data = ""
    do {
        println(question)
        var data = Scanner(System.`in`).nextLine()
        val isRead = when(data.length) {
            0->{
                println("Ведены некорректные данные. Значение не должно быть пустым.")
                true
            }
            else -> false

        }
    } while( isRead )

    return data
}
