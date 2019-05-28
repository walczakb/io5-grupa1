package io.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    private List<Observer> observers = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    protected Item createItem(String name, int count, int price) {
        return new Item(name, count, price);
    }

    public Item addItem(String name, int count, int price) {
        Item item = createItem(name, count, price);
        items.add(item);
        for (Observer observer : observers) observer.notifyAdd(item);
        return item;
    }

    protected void addItem(Item item) {
        items.add(item);
        for (Observer observer : observers) observer.notifyAdd(item);
    }

    public interface Observer {
        void notifyAdd(Item item);
    }
}
