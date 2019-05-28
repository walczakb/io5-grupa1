package io.db;

import io.domain.Item;
import io.domain.Store;
import io.domain.StoreTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StoreProxyTest extends StoreTest {
    @Test
    public void testCreate() {
        DbEngine engine = mock(DbEngine.class);
        StoreProxy store = new StoreProxy(engine);
        assertThat(store.items()).isEmpty();
        verify(engine).readItems(any());
        verifyNoMoreInteractions(engine);
    }

    @Test
    public void testAdd() {
        DbEngine engine = mock(DbEngine.class);
        when(engine.insertItem("item1", 100, 1)).thenReturn(0);
        when(engine.insertItem("item2", 200, 2)).thenReturn(1);
        StoreProxy store = new StoreProxy(engine);
        Item item1 = store.addItem("item1", 100, 1);
        Item item2 = store.addItem("item2", 200, 2);
        assertThat(store.items()).containsExactly(item1, item2);
        verify(engine).insertItem("item1", 100, 1);
        verify(engine).insertItem("item2", 200, 2);
        verify(engine).readItems(any());
        verifyNoMoreInteractions(engine);
    }

    @Test
    public void testObserver() {
        DbEngine engine = mock(DbEngine.class);
        when(engine.insertItem("item1", 100, 1)).thenReturn(0);
        when(engine.insertItem("item2", 200, 2)).thenReturn(1);
        StoreProxy store = new StoreProxy(engine);
        Store.Observer observer = mock(Store.Observer.class);
        store.addObserver(observer);
        Item item1 = store.addItem("item1", 100, 1);
        store.removeObserver(observer);
        store.addItem("item2", 200, 2);
        verify(observer, times(1)).notifyAdd(item1);
        verifyNoMoreInteractions(observer);
    }
}