package javase02.stationery;

public class Notepad extends StationeryItem {
    private int size;

    public Notepad(String name, int cost, int size) {
        super(name, cost);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Notepad notepad = (Notepad) o;
        if (size != notepad.size) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * size;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", size: %d", size);
    }
}
