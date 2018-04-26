package ua.tqs.hw1;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pedro Nunes, nmec 59542
 */
public class ConverterUnitTest {
    
    private Converter converter;
    
    public ConverterUnitTest() {
    }

    @Before
    public void setUp() {
        converter=new Converter();
    }
    
    @After
    public void tearDown() {
        converter=null;
    }
    
    /**
     * Test of convert method, of class Converter.
     */
    @Test
    public void testConvert1() {
        System.out.println("convert1");
        String expResult = "0.82";
        String result=converter.convert("1","USD","EUR");
        assertEquals(expResult, result);
    }
    
     /**
     * Test of convert method, of class Converter.
     */
    @Test
    public void testConvert2() {
        System.out.println("convert2");
        String expResult = "1.22";
        String result=converter.convert("1","EUR","USD");
        assertEquals(expResult, result);
    }

    /**
     * Test of cache of class Converter.
     */
    @Test
    public void testCache() {
        System.out.println("cache");
        JSONObject expResult = converter.getInfo();
        JSONObject result= new JSONObject(Converter.readFile());
        assertEquals(expResult.toString(), result.toString());
    }
}
