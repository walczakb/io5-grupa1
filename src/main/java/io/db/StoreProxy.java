package io.db;

import io.domain.Item;
import io.domain.Store;

public class StoreProxy extends Store {
    private DbEngine engine;

    StoreProxy(DbEngine engine) {
        this.engine = engine;
    }

    @Override
    public Item createItem(String name, int count, int price) {
        int id = engine.insertItem(name, count, price);
        return new ItemProxy(name, count, price, id, engine);
    }

    void addItem(int id, String name, int count, int price) {
        ItemProxy item = new ItemProxy(name, count, price, id, engine);
        addItem(item);
    }
}
