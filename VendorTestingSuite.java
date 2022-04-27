

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;

/*
TO RUN TESTS IN VS CODE: simply go to the testing tab (vial icon) on bottom of far left menu bar 
and click play button.
We may want to separate this into different testing classes in the future. Not sure
*/
public class VendorTestingSuite {
    Vendor testVendor;
    Cart testCart;

    //For testing Stdout output, taken from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /*@Before annotation: runs before each @Test method
    if we want to run a function once before all other tests, use @BeforeClass annotation
        *however, I tried to do this and @BeforeClass appears to need to be static, which caused
        problems for me -CB
    */
    @Before
    public void initialize() {
        testVendor = new Vendor(4, 5);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /*
    Simply makes sure testDisplay() prints an output to system.out
    */
    @Test
    public void testDisplay() {
        testVendor.displayVendor();
        assertNotNull(outputStreamCaptor);
    }

    @Test
    public void testAddItem() {
        VendingItem testItem = new VendingItem(3, "🥑", "avocado", 2.50);
        testVendor.addItem(testItem, 0, 0);
        assertEquals(testVendor.getNumItemsAt(0, 0), 3);
        assertEquals("avocado", testVendor.getItemAt(0, 0).getName());
        assertEquals("🥑", testVendor.getItemAt(0, 0).getEmoji());
        assertEquals(2.50, testVendor.getItemAt(0, 0).getPrice(), .01);
    }

    @Test
    public void testBuyItem() {
        assertFalse("input validation not working", testVendor.buyItem("szxxc"));
        assertFalse("input validation not working", testVendor.buyItem("f3"));
        assertFalse("input validation not working", testVendor.buyItem("56"));
        assertFalse("input validation not working", testVendor.buyItem("12348910234"));
        assertTrue("input validation not working", testVendor.buyItem("a4"));
        assertTrue("input validation not working", testVendor.buyItem("b1"));
        assertTrue("input validation not working", testVendor.buyItem("c3"));
        assertTrue("input validation not working", testVendor.buyItem("d5"));
    }

    @Test
    public void testHandleRemove() {
        VendingItem testItem = new VendingItem(2, "🥑", "avocado", 2.50);
        for(int i = 0; i < 2; ++i) testVendor.addItem(testItem, 0, 0);
        testVendor.handleRemove("remove avocado");
        assertEquals(true, testVendor.getCart().getItems().keySet().contains(testItem));
        assertEquals(true, testVendor.getCart().getItems().get(testItem) == 2);
        testVendor.handleRemove("remove avcado");
        testVendor.handleRemove("remve avocado");
        testVendor.handleRemove("remove banana");
        assertEquals(true, testVendor.getCart().getItems().get(testItem) == 2);
        testVendor.handleRemove("remove avocado");
        assertEquals(true, testVendor.getCart().getItems().get(testItem) == 1);
        testVendor.handleRemove("remove avocado");
        assertEquals(false, testVendor.getCart().getItems().keySet().contains(testItem));
    }

    /*reassign system output to stdout after testing
    @AfterClass  annotation runs after all @Test methods have completed
    */
    @After
    public void tearDown() {
        System.setOut(standardOut);
    }
}
