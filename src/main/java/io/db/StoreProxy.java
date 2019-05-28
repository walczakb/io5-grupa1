package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store {
    DbEngine engine;

    StoreProxy(DbEngine engine) {
        this.engine = engine;
        engine.readItems((id, name, count, price) -> {
            ItemProxy item = new ItemProxy(id, name, count, price, engine);
            addItem(item);
        });
    }

    @Override
    protected Item createItem(String name, int count, int price) {
        int id = engine.insertItem(name, count, price);
        return new ItemProxy(id, name, count, price, engine);
    }
}
