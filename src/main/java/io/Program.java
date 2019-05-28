package io;

import io.db.Database;
import io.sqldb.SqlDbFactory;
import io.swingui.SwingFacade;

public class Program {
    public static void main(String[] args) {
        SwingFacade.start(Database.loadStore(new SqlDbFactory().engine()));
    }
}
