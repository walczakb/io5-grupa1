package io;

import io.db.DbEngine;
import io.db.Database;
import io.swingui.SwingFacade;
import io.sqldb.SqlDbFactory;

import javax.xml.crypto.Data;

public class Program {
    public static void main(String[] args) {
        DbEngine engine = new SqlDbFactory().engine();
        SwingFacade.start(Database.loadStore(engine));
    }
}
