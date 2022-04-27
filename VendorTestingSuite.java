

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
        //avocado at items[0][0] in Vendor
        VendingItem testItem = testVendor.getItemAt(0, 0);
        for(int i = 0; i < 3; ++i) testVendor.getCart().addItem(testItem);
        testVendor.handleRemove("remove avocado");
        assertTrue(testVendor.getCart().getItems().keySet().contains(testItem));
        assertTrue(testVendor.getCart().getItems().get(testItem) == 2);
        testVendor.handleRemove("remove avcado");
        testVendor.handleRemove("remove banana");
        assertTrue(testVendor.getCart().getItems().get(testItem) == 2);
        testVendor.handleRemove("remove avocado");
        testVendor.handleRemove("remove avocado");
        assertFalse(testVendor.getCart().getItems().keySet().contains(testItem));
    }

    @Test
    public void testHandleAdd() {
        VendingItem testItem = testVendor.getItemAt(0, 0);
        testVendor.handleAdd("add a1");
        testVendor.handleAdd("add a1");
        assertTrue(testVendor.getCart().getItems().get(testItem) == 2);
    }

    @Test
    public void testMagicNumber() {
        double m1 = Vendor.generateMagicNumber();
        double m2 = Vendor.generateMagicNumber();
        double m3 = Vendor.generateMagicNumber();
        assertTrue(5 < m1 && m1 < 15);
        assertTrue(5 < m2 && m2 < 15);
        assertTrue(5 < m3 && m3 < 15);
        assertFalse(m1 == m2 | m2 == m3 | m1 == m3);
    }

    @Test
    public void testWithinRange() {
        assertTrue(testVendor.withinMagicRange(16.00, 16.00));
        assertTrue(testVendor.withinMagicRange(6.00, 6.19));
        assertTrue(testVendor.withinMagicRange(8.00, 7.89));
        assertFalse(testVendor.withinMagicRange(10, 10.21));
    }

    /*reassign system output to stdout after testing
    @AfterClass  annotation runs after all @Test methods have completed
    */
    @After
    public void tearDown() {
        System.setOut(standardOut);
    }
}
