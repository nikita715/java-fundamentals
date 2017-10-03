package stationery;

import lombok.Getter;
import lombok.Setter;

public class Ruler extends StationeryItem {

    @Getter
    @Setter
    private int length;

    public Ruler(String name, int cost, int length) {
        super(name, cost);
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
