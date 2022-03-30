

import static org.junit.Assert.assertNotNull;

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
        testVendor = new Vendor();
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

    /*reassign system output to stdout after testing
    @AfterClass  annotation runs after all @Test methods have completed
    */
    @After
    public void tearDown() {
        System.setOut(standardOut);
    }
}