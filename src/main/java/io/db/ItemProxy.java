package io.db;

import io.domain.Item;

class ItemProxy extends Item {
    private final DbEngine dbEngine;
    private final int id;

    ItemProxy(String name, int count, int price, DbEngine dbEngine, int id) {
        super(name, count, price);
        this.dbEngine = dbEngine;
        this.id = id;
    }

    @Override
    public void update(String name, int count, int price) {
        dbEngine.updateItem(id, name, count, price);
        super.update(name, count, price);
    }
}
