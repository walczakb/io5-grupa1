package io.db;

import io.domain.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemProxyTest {
    @Test
    public void testUpdateItem() {
        DbEngine engine = mock(DbEngine.class);
        when(engine.insertItem("item1", 100, 1)).thenReturn(101);
        Item item = new ItemProxy(engine.insertItem("item1", 100, 1),
                "item1", 100, 1, engine);
        item.update("item2", 200, 2);
        verify(engine).updateItem(101, "item2", 200, 2);
    }

}