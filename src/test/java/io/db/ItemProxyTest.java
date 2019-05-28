package io.db;

import io.domain.ItemTest;
import org.junit.jupiter.api.Test;

import static io.domain.ItemAssert.assertThat;
import static org.mockito.Mockito.*;

public class ItemProxyTest extends ItemTest {
    @Override
    @Test
    public void testUpdate() {
        DbEngine dbEngine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, dbEngine, 1);
        item.update("item2", 200, 2);
        assertThat(item).hasData("item2", 200, 2);
        verify(dbEngine).updateItem(1, item.name(), item.count(), item.price());
    }

    @Override
    @Test
    public void testCreate() {
        DbEngine dbEngine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, dbEngine, 1);
        assertThat(item).hasData("item1", 100, 1);
    }

    @Override
    @Test
    public void testObserver() {
        DbEngine dbEngine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, dbEngine, 2);
        ItemProxy.Observer observer = mock(ItemProxy.Observer.class);
        item.addObserver(observer);
        item.update("item2", 200, 2);
        item.removeObserver(observer);
        item.update("item3", 300, 3);
        verify(observer, times(1)).notifyUpdate(item);
        verifyNoMoreInteractions(observer);
    }
}
