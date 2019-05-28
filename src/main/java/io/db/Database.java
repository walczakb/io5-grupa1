package io.db;

import io.domain.Store;

public final class Database {
    private Database() {}

    public static Store loadStore(DbEngine engine) {
        return new StoreProxy(engine);
    }
}
