package io.db;

import io.domain.Item;
import io.domain.Store;
import io.sqldb.SqlDbFactory;

class StoreProxy extends Store {
    DbEngine engine = new SqlDbFactory().engine();

    public ItemProxy createItem(String name, int count, int price){
        int id = engine.insertItem(name, count, price);
        //ItemProxy.engine = engine;
        ItemProxy ip = new ItemProxy(name, count, price, engine);
        ItemProxy.addId(ip, id);
        return ip;
    }

    public void addItem(Item item) {
        items.add(item);
        for (Observer observer : observers) observer.notifyAdd(item);
    }
}
