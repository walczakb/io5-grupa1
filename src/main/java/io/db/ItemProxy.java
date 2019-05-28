package io.db;

import io.domain.Item;

class ItemProxy extends Item {
    private final DbEngine engine;
    private final int id;

    ItemProxy(DbEngine engine, int id, String name, int count, int price) {
        super(name, count, price);
        this.engine = engine;
        this.id = id;
    }

    public void update(String name, int count, int price) {
        engine.updateItem(id, name, count, price);
        super.update(name, count, price);
    }
}
