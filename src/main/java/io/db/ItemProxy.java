package io.db;

import io.domain.Item;

public class ItemProxy extends Item {
    private DbEngine engine;
    private int id;

    ItemProxy(String name, int count, int price, int id, DbEngine engine) {
        super(name, count, price);
        this.engine = engine;
        this.id = id;
    }

    @Override
    public void update(String name, int count, int price) {
        engine.updateItem(id, name, count, price);
        super.update(name, count, price);
    }
}
