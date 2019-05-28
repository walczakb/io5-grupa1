package io.db;

import io.domain.Store;

public class Database {
    private final DbEngine engine;

    public Database(DbEngine engine) { this.engine = engine; }

    public Store loadStore() {
        Store store = new Store();
        engine.readItems((id, name, count, price) -> {
            store.addItem(name, count, price);
        });
        return store;
    }


}
