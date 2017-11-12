package javase07.multithreadapp;

import java.util.ArrayList;
import java.util.List;

public class SharedResource {
    private List<Integer> list;

    public SharedResource() {
        list = new ArrayList<>();
    }

    public void setElement(Integer element) {
        list.add(element);
    }

    public Integer getElement() {
        if (!list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }
}