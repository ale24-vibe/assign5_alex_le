package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.example.Amazon.Cost.ItemType;
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
    @DisplayName("specification-based: calculate() aggregates price using PriceRule mock")
    void testCalculate_specificationBased() {
        Item item1 = new Item(ItemType.ELECTRONIC, "Phone", 1, 500.0);
        Item item2 = new Item(ItemType.OTHER, "Book", 2, 20.0);
        List<Item> items = List.of(item1, item2);

        when(mockCart.getItems()).thenReturn(items);
        when(mockRule.priceToAggregate(items)).thenReturn(540.0);

        double price = amazon.calculate();
        assertEquals(540.0, price);
    }

    @Test
    @DisplayName("structural-based: addToCart delegates to ShoppingCart.add()")
    void testAddToCart_structuralBased() {
        Item item = new Item(ItemType.ELECTRONIC, "Phone", 1, 500.0);
        amazon.addToCart(item);
        verify(mockCart, times(1)).add(item);
    }
}