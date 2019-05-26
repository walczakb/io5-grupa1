package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store {
    private final DbEngine engine;

    public StoreProxy(DbEngine engine) {
        this.engine = engine;
    }

    @Override
    protected Item createItem(String name, int count, int price) {
        int id = engine.insertItem(name, count, price);
        return new ItemProxy(engine, id, name, count, price);
    }
}
