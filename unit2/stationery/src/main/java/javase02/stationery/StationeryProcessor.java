package javase02.stationery;

import java.util.ArrayList;

public class StationeryProcessor {
    private ArrayList<StationeryItem> items = new ArrayList<>();

    public void addItem(StationeryItem item) {
        items.add(item);
    }

    public boolean contains(StationeryItem item) {
        return items.contains(item);
    }

    public void removeItem(StationeryItem item) {
        items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    public int getItemsCount() {
        return items.size();
    }

    public int getItemsCost() {
        return items.stream().mapToInt(StationeryItem::getCost).sum();
    }
}
