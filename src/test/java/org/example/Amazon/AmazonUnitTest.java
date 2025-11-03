package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AmazonUnitTest {

    ShoppingCart mockCart;
    PriceRule mockRule;
    Amazon amazon;

    @BeforeEach
    void setup() {
        mockCart = mock(ShoppingCart.class);
        mockRule = mock(PriceRule.class);
        amazon = new Amazon(mockCart, List.of(mockRule));
    }

    @Test
    @DisplayName("specification-based: calculate() returns correct price using rules")
    void testCalculate_specificationBased() {
        Item book = new Item(null, "Book", 1, 10.0);
        Item pen = new Item(null, "Pen", 2, 2.0);
        List<Item> items = List.of(book, pen);
        when(mockCart.getItems()).thenReturn(items);
        when(mockRule.priceToAggregate(items)).thenReturn(14.0);

        double price = amazon.calculate();
        assertEquals(14.0, price);
    }

    @Test
    @DisplayName("structural-based: addToCart calls ShoppingCart.add")
    void testAddToCart_structuralBased() {
        Item notebook = new Item(null, "Notebook", 3, 5.0);
        amazon.addToCart(notebook);
        verify(mockCart, times(1)).add(notebook);
    }
}