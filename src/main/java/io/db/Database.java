package io.db;

import io.domain.Store;

public class Database implements DbGateway {
    static public Store loadStore(DbEngine dbEngine) {
        return new StoreProxy(dbEngine);
    }
}
