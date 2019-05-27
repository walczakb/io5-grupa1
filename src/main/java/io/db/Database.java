package io.db;

import io.domain.Item;
import io.domain.Store;

import java.util.HashMap;
import java.util.Map;

public class Database implements DbGateway {
    final private DbEngine dbEngine;

    public Database(DbEngine dbEngine){
        this.dbEngine = dbEngine;
    }

    public Store loadStore() {
        Store store = new StoreProxy(dbEngine);
        dbEngine.readItems((id, name, count, price) -> {
            Item item = store.addItem(name, count, price);
        });
        return store;
    }
}
