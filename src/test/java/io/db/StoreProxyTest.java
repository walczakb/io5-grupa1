package io.db;

import io.domain.Item;
import io.domain.Store;
import io.domain.StoreTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class StoreProxyTest extends StoreTest {
    @Test
    public void testCreate() {
        DbEngine dbEngine = mock(DbEngine.class);
        StoreProxy store = new StoreProxy(dbEngine);
        assertThat(store.items()).isEmpty();
    }

    @Test
    public void testAdd() {
        DbEngine dbEngine = mock(DbEngine.class);
        StoreProxy store = new StoreProxy(dbEngine);
        Item item1 = store.addItem("item1", 100, 1);
        Item item2 = store.addItem("item2", 200, 2);
        assertThat(store.items()).containsExactly(item1, item2);
    }

    @Test
    public void testObserver() {
        DbEngine dbEngine = mock(DbEngine.class);
        StoreProxy store = new StoreProxy(dbEngine);
        Store.Observer observer = mock(Store.Observer.class);
        store.addObserver(observer);
        Item item1 = store.addItem("item1", 100, 1);
        store.removeObserver(observer);
        store.addItem("item2", 200, 2);
        verify(observer, times(1)).notifyAdd(item1);
        verifyNoMoreInteractions(observer);
    }
}
