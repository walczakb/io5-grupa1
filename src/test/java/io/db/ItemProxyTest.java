package io.db;

import io.domain.Item;
import io.domain.ItemTest;
import org.junit.jupiter.api.Test;

import static io.domain.ItemAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ItemProxyTest extends ItemTest {
    @Test
    public void testCreate() {
        DbEngine dbEngine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, 0, dbEngine);
        assertThat(item).hasData("item1", 100, 1);
    }

    @Test
    public void testUpdate() {
        DbEngine dbEngine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, 0, dbEngine);
        item.update("item2", 200, 2);
        assertThat(item).hasData("item2", 200, 2);
    }

    @Test
    public void testObserver() {
        DbEngine dbEngine = mock(DbEngine.class);
        ItemProxy item = new ItemProxy("item1", 100, 1, 0, dbEngine);
        Item.Observer observer = mock(Item.Observer.class);
        item.addObserver(observer);
        item.update("item2", 200, 2);
        item.removeObserver(observer);
        item.update("item3", 300, 3);
        verify(observer, times(1)).notifyUpdate(item);
        verifyNoMoreInteractions(observer);
    }
}
