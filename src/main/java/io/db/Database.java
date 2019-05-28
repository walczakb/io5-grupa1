package io.db;

import io.domain.Store;

public class Database {
    public static Store loadStore(DbEngine engine) {
        StoreProxy store = new StoreProxy(engine);
        engine.readItems(store::addItem);
        return store;
    }
}
