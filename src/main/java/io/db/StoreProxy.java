package io.db;

import io.domain.Item;
import io.domain.Store;

class StoreProxy extends Store{
    private final DbEngine engine;
    StoreProxy(DbEngine engine){
        this.engine = engine;
    }

    @Override
    public Item createItem(String name, int count, int price) {
        int id = engine.insertItem(name, count, price);
        Item item = new ItemProxy(name, count, price, id);
        return item;
    }

}

