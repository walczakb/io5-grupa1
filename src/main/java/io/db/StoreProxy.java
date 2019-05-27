package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store {
    private final DbEngine engine;

    StoreProxy(DbEngine engine) {
        this.engine = engine;
        engine.readItems((id, name, count, price) -> addItem(new ItemProxy(engine, id, name, count, price)));
    }

    protected Item createItem(String name, int count, int price) {
        int id = engine.insertItem(name, count, price);
        return new ItemProxy(engine, id, name, count, price);
    }
}
