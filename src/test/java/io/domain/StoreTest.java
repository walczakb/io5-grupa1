package io.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StoreTest {
    @Test
    public void testCreate() {
        Store store = new Store();
        assertThat(store.items()).isEmpty();
    }

    @Test
    public void testAdd() {
        Store store = new Store();
        store.addItem("item1", 100, 1);
        store.addItem("item2", 200, 2);
        assertThat(store.items()).containsExactly(store.items().get(0), store.items().get(1));
    }

    @Test
    public void testObserver() {
        Store store = new Store();
        Store.Observer observer = mock(Store.Observer.class);
        store.addObserver(observer);
        store.addItem("item1", 100, 1);
        store.removeObserver(observer);
        store.addItem("item2", 200, 2);
        verify(observer, times(1)).notifyAdd(store.items().get(0));
        verifyNoMoreInteractions(observer);
    }
}
