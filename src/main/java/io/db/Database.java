package io.db;

import io.domain.Store;

public class Database {
    public static Store loadStore(DbEngine engine) {
        StoreProxy storeProxy = new StoreProxy(engine);
        engine.readItems(storeProxy::loadItem);
        return storeProxy;
    }
}
