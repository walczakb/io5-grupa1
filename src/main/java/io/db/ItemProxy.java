package io.db;

import io.domain.Item;

import java.util.HashMap;
import java.util.Map;

class ItemProxy extends Item{

    static DbEngine engine;
    private int id;
    public ItemProxy(String name, int count, int price, int id) {
        super(name, count, price);
        this.id = id;
    }

    @Override
    public void update(String name, int count, int price) {
        engine.updateItem(id, name, count, price);
        super.update(name, count, price);
    }
}
