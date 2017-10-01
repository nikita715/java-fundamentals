package stationery;

public class Ruler extends StationeryItem {
    private int length;

    public Ruler(String name, int cost, int length) {
        super(name, cost);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Ruler ruler = (Ruler) o;
        if (length != ruler.length) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * length;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", length: %d", length);
    }
}
