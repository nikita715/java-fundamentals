package stationery;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BeginnerSet {
    private Set<StationeryItem> items = new HashSet<>();

    public BeginnerSet() {
        items.add(new Pen("Schneider", 100, "Blue"));
        items.add(new Eraser("Erich Krauze", 20, "Grey"));
        items.add(new Notepad("Moleskine", 150, 100));
        items.add(new Pencil("Tomsk", 30, "Grey"));
        items.add(new Ruler("Smth", 93, 20));
    }

    public Set<StationeryItem> getItems() {
        return items;
    }

    public List<StationeryItem> getItemsSortedByCost() {
        return items.stream().sorted(Comparator.comparing(StationeryItem::getCost)).collect(Collectors.toList());
    }

    public List<StationeryItem> getItemsSortedByName() {
        return items.stream().sorted(Comparator.comparing(StationeryItem::getName)).collect(Collectors.toList());
    }

    public List<StationeryItem> getItemsSortedByCostAndName() {
        return items.stream().sorted(Comparator.comparing(StationeryItem::getCost)
                .thenComparing(StationeryItem::getName)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        BeginnerSet bs = new BeginnerSet();
        bs.getItemsSortedByCost().stream().map(StationeryItem::toString).forEach(System.out::println);
        System.out.println();
        bs.getItemsSortedByCostAndName().stream().map(StationeryItem::toString).forEach(System.out::println);
        System.out.println();
        bs.getItemsSortedByName().stream().map(StationeryItem::toString).forEach(System.out::println);
    }
}
