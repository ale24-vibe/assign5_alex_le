package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AmazonIntegrationTest {

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
    @DisplayName("specification-based: addToCart and calculate should work with mocks")
    void testAddToCartAndCalculate_specificationBased() {
        // Mock ItemType if needed
        var mockType = mock(org.example.Amazon.Cost.ItemType.class);
        when(mockType.name()).thenReturn("GENERIC");

        Item book = new Item(mockType, "Book", 1, 10.0);
        Item pen = new Item(mockType, "Pen", 2, 3.0);
        List<Item> items = List.of(book, pen);

        // Simulate adding items
        amazon.addToCart(book);
        amazon.addToCart(pen);

        when(mockCart.getItems()).thenReturn(items);
        when(mockRule.priceToAggregate(items)).thenReturn(13.0);

        assertEquals(13.0, amazon.calculate());
    }

    @Test
    @DisplayName("structural-based: Ensure addToCart calls ShoppingCart.add")
    void testCartItemsAfterAdd_structuralBased() {
        var mockType = mock(org.example.Amazon.Cost.ItemType.class);
        when(mockType.name()).thenReturn("GENERIC");
        Item notebook = new Item(mockType, "Notebook", 1, 7.0);

        amazon.addToCart(notebook);
        verify(mockCart, times(1)).add(notebook);
    }
}