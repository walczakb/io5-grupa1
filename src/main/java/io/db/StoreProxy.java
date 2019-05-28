package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store {
    private DbEngine dbEngine;

    StoreProxy(DbEngine dbEngine) {
        this.dbEngine = dbEngine;
    }

    protected Item createItem(String name, int count, int price) {
        int id = dbEngine.insertItem(name, count, price);
        return new ItemProxy(name, count, price, id, dbEngine);
    }

    protected Item getItemToLoad(int id, String name, int count, int price) {
        return new ItemProxy(name, count, price, id, dbEngine);
    }
}