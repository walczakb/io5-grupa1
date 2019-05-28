package io.db;

import io.domain.Item;
import io.domain.ItemAssert;
import io.domain.Store;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreProxyTest {
    @Test
    public void testCreateItem() {
        DbEngine engine = mock(DbEngine.class);
        Store store = Database.loadStore(engine);
        Item item = store.addItem("item1", 100, 1);
        verify(engine).insertItem("item1", 100, 1);
    }

    @Test
    public void testLoad() {
        DbEngine engine = mock(DbEngine.class);
        doAnswer(invocation -> {
            DbEngine.ItemListBuilder builder = invocation.getArgument(0);
            builder.add(101, "item1", 100, 1);
            return null;
        }).when(engine).readItems(any(DbEngine.ItemListBuilder.class));
        Item item = Database.loadStore(engine).items().get(0);
        ItemAssert.assertThat(item).hasData("item1", 100, 1);
        verify(engine, never()).insertItem( "item1", 100, 1);
    }
}