package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AmazonIntegrationTest {

    Database db;
    ShoppingCartAdaptor cart;
    Amazon amazon;

    @BeforeEach
    void setup() {
        db = new Database();
        db.resetDatabase();
        cart = new ShoppingCartAdaptor(db);
        // Use all rules if you want full coverage
        PriceRule regular = new org.example.Amazon.Cost.RegularCost();
        PriceRule delivery = new org.example.Amazon.Cost.DeliveryPrice();
        PriceRule electronics = new org.example.Amazon.Cost.ExtraCostForElectronics();
        amazon = new Amazon(cart, List.of(regular, delivery, electronics));
    }

    @Test
    @DisplayName("specification-based: calculate() returns correct total after adding multiple items")
    void testCalculate_specificationBased() {
        amazon.addToCart(new Item(ItemType.ELECTRONIC, "Laptop", 1, 500.0));
        amazon.addToCart(new Item(ItemType.OTHER, "Book", 2, 20.0));
        // Regular cost: 1*500 + 2*20 = 540
        // Delivery for 2 items: $5
        // Electronics surcharge: $7.5 (since an ELECTRONIC device is in the cart)
        // Total: 540 + 5 + 7.5 = 552.5
        assertEquals(552.5, amazon.calculate());
    }

    @Test
    @DisplayName("structural-based: ShoppingCart contains items after adding via Amazon")
    void testCartItemsAfterAdd_structuralBased() {
        Item item = new Item(ItemType.OTHER, "Notebook", 1, 7.0);
        amazon.addToCart(item);

        List<Item> items = cart.getItems();
        assertEquals(1, items.size());
        assertEquals("Notebook", items.get(0).getName());
        assertEquals(1, items.get(0).getQuantity());
        assertEquals(7.0, items.get(0).getPricePerUnit());
        assertEquals(ItemType.OTHER, items.get(0).getType());
    }
}