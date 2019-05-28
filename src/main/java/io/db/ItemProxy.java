package io.db;

import io.domain.Item;

import java.util.HashMap;

class ItemProxy extends Item {
    private static DbEngine engine;
    private static HashMap<Item, Integer> id = new HashMap<>();
    ItemProxy(String name, int count, int price, DbEngine e) {
        super(name, count, price);
        engine = e;
    }

    static void addId(Item item, int id){
        ItemProxy.id.put(item, id);
    }

    public void update(String name, int count, int price) {
        // add to database
        engine.updateItem(id.get(this), this.name, this.count, this.price);
        super.update(name, count, price);
    }


}
