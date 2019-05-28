package io.db;

import io.domain.Item;
import io.domain.ItemTest;
import org.junit.jupiter.api.Test;

import static io.domain.ItemAssert.assertThat;
import static org.mockito.Mockito.*;

class ItemProxyTest extends ItemTest {
    @Test
    @Override
    public void testCreate() {
        ItemProxy item = new ItemProxy("item1", 100, 1, 0, null);
        assertThat(item).hasData("item1", 100, 1);
    }

    @Test
    @Override
    public void testUpdate() {
        DbEngine engine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, 0, engine);
        item.update("item2", 200, 2);
        verify(engine).updateItem(0, "item2", 200, 2);
        verifyNoMoreInteractions(engine);
        assertThat(item).hasData("item2", 200, 2);
    }

    @Test
    @Override
    public void testObserver() {
        DbEngine engine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, 0, engine);
        Item.Observer observer = mock(Item.Observer.class);
        item.addObserver(observer);
        item.update("item2", 200, 2);
        item.removeObserver(observer);
        item.update("item3", 300, 3);
        verify(observer, times(1)).notifyUpdate(item);
        verifyNoMoreInteractions(observer);
    }
}