import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;

public class CartTestingSuite {
    Cart testingCart;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void initialize() {
        testingCart = new Cart();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testAddItem() {
        VendingItem testItem = new VendingItem(3, "🥑", "avocado", 2.50);
        testingCart.addItem(testItem);
        assertEquals(testingCart.getItems().containsKey(testItem), true);
    }

    @Test
    public void testRemoveItem() {
        VendingItem testItem = new VendingItem(3, "🥑", "avocado", 2.50);
        testingCart.addItem(testItem);
        testingCart.removeItem(testItem);
        assertEquals(testingCart.getItems().containsKey(testItem), true);
    }

    @Test
    public void testCalculateSubtotal(){
        VendingItem testItemOne = new VendingItem(3, "🥑", "avocado", 2.50);
        VendingItem testItemTwo = new VendingItem(5, "🍏", "apple", 2.00); 
        VendingItem testItemThree = new VendingItem(2, "🍌", "banana", 0.75);
        for(int i = 0; i < 3; ++i){
            testingCart.addItem(testItemOne);
        }
        testingCart.addItem(testItemTwo);
        testingCart.addItem(testItemThree);
        assertEquals(testingCart.calculateSubtotal(), 10.25, 0.1);
    }

    @Test
    public void testViewCart(){
        VendingItem testItemOne = new VendingItem(3, "🥑", "avocado", 2.50);
        VendingItem testItemTwo = new VendingItem(5, "🍏", "apple", 2.00); 
        VendingItem testItemThree = new VendingItem(2, "🍌", "banana", 0.75);
        for(int i = 0; i < 3; ++i){
            testingCart.addItem(testItemOne);
        }
        testingCart.addItem(testItemTwo);
        testingCart.addItem(testItemThree);
        testingCart.viewCart();
        assertNotNull(outputStreamCaptor);
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }
}
