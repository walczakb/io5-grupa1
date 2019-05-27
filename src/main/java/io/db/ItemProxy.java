package io.db;

import io.domain.Item;

class ItemProxy extends Item {
    private int id;
    private DbEngine dbEngine;

    ItemProxy(String name, int count, int price, DbEngine dbEngine) {
        super(name, count, price);
        this.dbEngine = dbEngine;
        id = dbEngine.insertItem(name(), count(), price());
    }

    @Override
    public void update(String name, int count, int price){
        dbEngine.updateItem(id, name, count, price);
        super.update(name, count, price);
    }
}
