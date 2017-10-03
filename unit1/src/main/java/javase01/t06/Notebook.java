package javase01.t06;

/**
 * Represents a list of notes {@link javase01.t06.Note}
 */
public class Notebook {
    private Note[] notes;
    private int fillness;

    /**
     * Adds the note to the notebook
     *
     * @param note
     */
    public void addNote(Note note) {
        ensureCapacity(fillness + 1);
        notes[fillness + 1] = note;
        fillness++;
    }

    /**
     * Removes the note from the notebook
     *
     * @param note
     */
    public void removeNote(Note note) {
        for (int i = 0; i < fillness; i++) {
            if (notes[i] == note) {
                removeNote(i);
                break;
            }
        }
    }

    /**
     * Removes the note from the notebook
     *
     * @param noteIndex
     */
    public void removeNote(int noteIndex) {
        for (int i = noteIndex; i < fillness - 2; i++) {
            notes[i] = notes[i + 1];
        }
        fillness--;
    }

    /**
     * Changes the note by its reference
     *
     * @param note
     * @param newText
     */
    public void edit(Note note, String newText) {
        note.setText(newText);
    }

    /**
     * Changes the note at the index
     *
     * @param noteIndex
     * @param newText
     */
    public void edit(int noteIndex, String newText) {
        notes[noteIndex].setText(newText);
    }

    /**
     * Prints all notes from the notebook
     */
    public void printAllNotes() {
        for (Note note : notes) {
            System.out.println(note.getText());
        }
    }

    private int getCapacity() {
        return notes.length;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity > getCapacity()) {
            int newCapacity = (int) (getCapacity() * 1.5);
            Note[] newNotes = new Note[newCapacity];
            System.arraycopy(notes, 0, newNotes, 0, fillness);
            notes = newNotes;
        }
    }
}
