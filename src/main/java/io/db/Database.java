package io.db;

import io.domain.Item;
import io.domain.Store;

import java.util.HashMap;
import java.util.Map;

public class Database {

    static public Store loadStore(DbEngine engine) {
        Store store = new StoreProxy(engine);
        return store;
    }
}
