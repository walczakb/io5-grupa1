package io.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
    public interface Observer {
        void notifyAdd(Item item);
    }

    protected Item createItem(String name, int count, int price) {
        return new Item(name, count, price);
    }

    protected Item getItemToLoad(int id, String name, int count, int price) {
        return new Item(name, count, price);
    }

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) { observers.add(observer); }
    public void removeObserver(Observer observer) { observers.remove(observer); }

    private List<Item> items = new ArrayList<>();

    public List<Item> items() { return Collections.unmodifiableList(items); }

    public Item addItem(String name, int count, int price) {
        Item item = createItem(name, count, price);
        items.add(item);
        for (Observer observer : observers) observer.notifyAdd(item);
        return item;
    }

    public Item loadItem(int id, String name, int count, int price) {
        Item item = getItemToLoad(id, name, count, price);
        items.add(item);
        for (Observer observer : observers) observer.notifyAdd(item);
        return item;
    }
}
