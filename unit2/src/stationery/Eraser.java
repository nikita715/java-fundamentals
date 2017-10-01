package stationery;

public class Eraser extends StationeryItem {
    private String color;

    public Eraser(String name, int cost, String color) {
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
        Eraser eraser = (Eraser) o;
        if (null == color) {
            return (null == eraser.color);
        } else {
            if (!getName().equals(eraser.color)) {
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
