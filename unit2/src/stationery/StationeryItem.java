package stationery;

public abstract class StationeryItem {
    private int cost;
    private String name;

    public StationeryItem(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        StationeryItem si = (StationeryItem) o;
        if (getCost() != si.cost) {
            return false;
        }
        if (null == name) {
            return (null == si.name);
        } else {
            if (!getName().equals(si.name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 * getCost() + ((null == name) ? 0 : name.hashCode());
    }

    @Override
    public String toString() {
        return String.format("%s@name: %s, cost: %d", getClass().getName(), name, cost);
    }
}
