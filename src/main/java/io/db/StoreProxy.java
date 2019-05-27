package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store {
    private DbEngine dbEngine;

    StoreProxy(DbEngine dbEngine){
        this.dbEngine = dbEngine;
    }

    @Override
    protected Item createItem(String name, int count, int price) {
        return new ItemProxy(name, count, price, this.dbEngine);
    }
}
