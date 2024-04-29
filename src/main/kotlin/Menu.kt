import java.util.Scanner

class Menu(val title: String) {
    val optionStrings = mutableListOf<String>()
    val funList = mutableListOf<() -> Unit>()

    fun addOption(option: String, funListFun: () -> Unit) {
        optionStrings.add(option)
        funList.add(funListFun)
    }

    fun selectOption(scanner: Scanner): Int {
        while (true) {
            println(title)
            for (index in optionStrings.indices) {
                val option = optionStrings[index]
                println("${index + 1}. $option")
            }
            print("Выберите пункт меню: ")
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()
            if (choice != null && choice in 1..optionStrings.size) {
                funList[choice - 1].invoke()
                return choice
            } else {
                println("Введите корректный номер пункта меню")
            }
        }
    }
}
