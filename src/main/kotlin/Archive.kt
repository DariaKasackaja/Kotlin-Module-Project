class Archive(name: String) : Element( name ){
    val elements: MutableList<Note> = mutableListOf()
    val container = Container("Архив $name", elements)

    fun open() {
        container.start ( add = {
            elements.add(Note(getUserData("Введите название заметки."),
                getUserData("Введите текст заметки.")))
        },
            select = { index->
                println("Заметка ${elements[index - 1].name}")
                println(" ${elements[index - 1].text}")
                println("Для продолжения работы нажмите любой символ.")
                readlnOrNull()
            },
            "заметку")
    }
}