package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store {
    private final DbEngine dbEngine;

    StoreProxy(DbEngine dbEngine) {
        this.dbEngine = dbEngine;
        this.dbEngine.readItems((id, name, count, price) -> {
            items.add(new ItemProxy(name, count, price, dbEngine, id));
        });
    }

    @Override
    protected Item createItem(String name, int count, int price) {
        int id = dbEngine.insertItem(name, count, price);
        return new ItemProxy(name, count, price, dbEngine, id);
    }
}
