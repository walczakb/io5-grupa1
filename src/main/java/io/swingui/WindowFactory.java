package io.swingui;

import io.db.DbGateway;
import io.domain.Item;
import io.domain.Store;
import io.ui.ItemPresenter;
import io.ui.StorePresenter;
import io.ui.ViewFactory;

public class WindowFactory implements ViewFactory {
    private final DbGateway db;

    public WindowFactory(DbGateway db) { this.db = db; }

    public void openEditItemView(Item item) {
        ItemWindow view = new ItemWindow();
        ItemPresenter presenter = new ItemPresenter(view);
        view.initialize(presenter);
        presenter.initializeEdit(item, db);
    }

    public void openAddItemView(Store store) {
        ItemWindow view = new ItemWindow();
        ItemPresenter presenter = new ItemPresenter(view);
        view.initialize(presenter);
        presenter.initializeAdd(store, db);
    }

    public void openStoreView(Store store) {
        StoreWindow view = new StoreWindow();
        StorePresenter presenter = new StorePresenter(store, view, this);
        view.initialize(presenter);
        presenter.initialize();
    }
}
