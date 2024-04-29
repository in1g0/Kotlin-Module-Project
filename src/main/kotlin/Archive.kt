class Archive(val name: String) {
    val notes = mutableListOf<Note>()

    fun add(note: Note) {
        notes.add(note)
    }
}