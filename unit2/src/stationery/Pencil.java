package stationery;

public class Pencil extends StationeryItem {
    private String color;

    public Pencil(String name, int cost, String color) {
        super(name, cost);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Pencil pencil = (Pencil) o;
        if (null == color) {
            return (null == pencil.color);
        } else {
            if (!color.equals(pencil.color)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + ((null == color) ? 0 : color.hashCode());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", color: %s", color);
    }
}
