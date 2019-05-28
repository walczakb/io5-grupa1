package io.db;

import io.domain.Item;
import io.domain.Store;

import java.util.HashMap;
import java.util.Map;

public class Database implements DbGateway {
    private final DbEngine engine;

    public Database(DbEngine engine) {
        this.engine = engine;
    }

    public Store loadStore() {
        ItemProxy.engine = engine;
        Store store = new StoreProxy(engine);
        engine.readItems((id, name, count, price) -> {
            store.addItem(name, count, price);
        });
        return store;
    }
}