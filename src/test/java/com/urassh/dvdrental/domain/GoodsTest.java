package com.urassh.dvdrental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class GoodsTest {
    private Goods recentGoods;
    private Goods oldGoods;

    @BeforeEach
    void setUp() {
        Calendar recentDate = Calendar.getInstance();
        recentDate.add(Calendar.MONTH, -3); // 3 months ago

        Calendar oldDate = Calendar.getInstance();
        oldDate.add(Calendar.YEAR, -1); // 1 year ago

        recentGoods = new Goods(UUID.randomUUID(), "Recent Movie", recentDate.getTime(), "Action", "Store A", 10, true);
        oldGoods = new Goods(UUID.randomUUID(), "Old Movie", oldDate.getTime(), "Drama", "Store B", 5, true);
    }

    @Test
    void testGetFee_ForRecentGoods() {
        Money fee = recentGoods.getFee();
        assertEquals(new Money(300), fee, "Recent goods should have a fee of 300");
    }

    @Test
    void testGetFee_ForOldGoods() {
        Money fee = oldGoods.getFee();
        assertEquals(new Money(100), fee, "Old goods should have a fee of 100");
    }

    @Test
    void testIsNew_ForRecentGoods() {
        assertTrue(recentGoods.isNew(), "Goods released within 6 months should be new");
    }

    @Test
    void testIsNew_ForOldGoods() {
        assertFalse(oldGoods.isNew(), "Goods released more than 6 months ago should not be new");
    }

    @Test
    void testEquals_SameId() {
        UUID id = UUID.randomUUID();
        Goods goods1 = new Goods(id, "Title1", new Date(), "Genre1", "Store1", 0, true);
        Goods goods2 = new Goods(id, "Title2", new Date(), "Genre2", "Store2", 1, false);

        assertEquals(goods1, goods2, "Goods with the same ID should be equal");
    }

    @Test
    void testEquals_DifferentId() {
        Goods goods1 = new Goods(UUID.randomUUID(), "Title1", new Date(), "Genre1", "Store1", 0, true);
        Goods goods2 = new Goods(UUID.randomUUID(), "Title2", new Date(), "Genre2", "Store2", 1, false);

        assertNotEquals(goods1, goods2, "Goods with different IDs should not be equal");
    }

    @Test
    void testSetTitle() {
        Goods updatedGoods = recentGoods.setTitle("Updated Title");

        assertEquals("Updated Title", updatedGoods.getTitle(), "Title should be updated");
        assertNotEquals(recentGoods.getTitle(), updatedGoods.getTitle(), "Original goods instance should remain unchanged");
    }

    @Test
    void testSetGenre() {
        Goods updatedGoods = oldGoods.setGenre("Updated Genre");

        assertEquals("Updated Genre", updatedGoods.getGenre(), "Genre should be updated");
        assertNotEquals(oldGoods.getGenre(), updatedGoods.getGenre(), "Original goods instance should remain unchanged");
    }
}
