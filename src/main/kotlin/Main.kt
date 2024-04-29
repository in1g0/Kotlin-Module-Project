import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val notesApp = NotesApp()

    while (true) {
        val mainMenu = Menu("Меню:")
        mainMenu.addOption("Список архивов", notesApp::showArchiveMenu)
        mainMenu.addOption("Создать архив", notesApp::createArchive)
        mainMenu.addOption("Выйти", { System.exit(0) })

        val option = mainMenu.selectOption(scanner)
        if (option == 3) break
    }
}

class NotesApp {
    val archives = mutableListOf<Archive>()
    val scanner = Scanner(System.`in`)

    fun showArchiveMenu() {
        val archiveMenu = Menu("Список архивов:")
        for (index in archives.indices) {
            val archive = archives[index]
            archiveMenu.addOption("${index + 1}. ${archive.name}", { showNotesMenu(archive) })
        }
        archiveMenu.addOption("${archives.size + 1}. Создать архив", { createArchive() })
        archiveMenu.addOption("${archives.size + 2}. Назад", {})

        archiveMenu.selectOption(scanner)
    }

    fun showNotesMenu(archive: Archive) {
        val notesMenu = Menu("Архив: ${archive.name}")
        for (index in archive.notes.indices) {
            val note = archive.notes[index]
            notesMenu.addOption("${index + 1}. ${note.title}", { showNoteContent(note) })
        }
        notesMenu.addOption(
            "${archive.notes.size + 1}. Создать заметку",
            { createNoteMenu(archive) })
        notesMenu.addOption("${archive.notes.size + 2}. Назад", {})

        notesMenu.selectOption(scanner)
    }

    fun showNoteContent(note: Note) {
        println("Заметка: ${note.title}")
        println("Текст: ${note.content}")
    }

    fun createNoteMenu(archive: Archive) {
        println("Создание заметки:")
        print("Введите названик заметки: ")
        val title = scanner.nextLine().trim()
        if (title.isEmpty()) {
            println("Название заметки не может быть пустым")
            return
        }
        print("Введите текст заметки: ")
        val content = scanner.nextLine().trim()
        if (content.isEmpty()) {
            println("Текст заметки не может быть пустым")
            return
        }
        archive.add(Note(title, content))
        println("Заметка $title добавлена")
    }

    fun createArchive() {
        print("Введите название архива: ")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("Название архива не может быть пустым")
            return
        }
        archives.add(Archive(name))
        println("Архив $name создан")
    }
}




