package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;

class BarnesAndNobleTest {

    @Test
    @DisplayName("specification-based")
    void getPriceForCartReturnsCorrectTotal() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Book book1 = new Book("isbn1", 10, 5); // price 10, quantity 5
        Book book2 = new Book("isbn2", 20, 3); // price 20, quantity 3

        when(db.findByISBN("isbn1")).thenReturn(book1);
        when(db.findByISBN("isbn2")).thenReturn(book2);

        BarnesAndNoble store = new BarnesAndNoble(db, process);

        Map<String, Integer> order = new HashMap<>();
        order.put("isbn1", 2); // buy 2 of isbn1
        order.put("isbn2", 1); // buy 1 of isbn2

        PurchaseSummary summary = store.getPriceForCart(order);

        assertEquals(40, summary.getTotalPrice()); // (2*10) + (1*20) = 40
        assertTrue(summary.getUnavailable().isEmpty()); // All items available

        verify(process).buyBook(book1, 2);
        verify(process).buyBook(book2, 1);
    }

    @Test
    @DisplayName("structural-based")
    void getPriceForCartHandlesUnavailableQuantity() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Book book = new Book("isbn3", 15, 1); // Only 1 in stock
        when(db.findByISBN("isbn3")).thenReturn(book);

        BarnesAndNoble store = new BarnesAndNoble(db, process);

        Map<String, Integer> order = new HashMap<>();
        order.put("isbn3", 3); // Try to buy 3 but only 1 available

        PurchaseSummary summary = store.getPriceForCart(order);

        assertEquals(15, summary.getTotalPrice()); // Only charged for 1
        assertTrue(summary.getUnavailable().containsKey(book));
        assertEquals(2, summary.getUnavailable().get(book)); // 2 unavailable

        verify(process).buyBook(book, 1);
    }

    @Test
    @DisplayName("structural-based")
    void getPriceForCartWithNullOrderReturnsNull() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        BarnesAndNoble store = new BarnesAndNoble(db, process);

        assertNull(store.getPriceForCart(null));
    }
}