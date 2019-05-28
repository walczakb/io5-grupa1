package io.db;

import io.domain.Item;

class ItemProxy extends Item {
    private DbEngine dbEngine;
    int id;

    ItemProxy(String name, int count, int price, int id, DbEngine dbEngine) {
        super(name, count, price);
        this.id = id;
        this.dbEngine = dbEngine;
    }

    public void update(String name, int count, int price) {
        dbEngine.updateItem(id, name, count, price);
        super.update(name, count, price);
    }
}
