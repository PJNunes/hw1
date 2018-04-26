package ua.tqs.hw1;

import org.easymock.EasyMock;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Pedro Nunes, nmec 59542
 */
public class ConverterMockTest {
    
    private Bean bean;
    private Converter converter;
    
    public ConverterMockTest(){}
    
    @Before
    public void setUp() {
        bean = new Bean();
        converter= EasyMock.createMock(Converter.class);
        bean.setConverter(converter);
    }
    
    @After
    public void tearDown() {
        bean=null;
        converter=null;
    }
    
    /**
     * Test of getTotalValue method, of class StocksPortofolio.
     */
    @Test
    public void testGetTotalValue() {
        EasyMock.expect(converter.convert("1", "USD", "EUR")).andReturn("1.22");
        EasyMock.replay(converter);
        
        bean.setEntryValue("1");
        bean.setEntryCurrency("USD");
        bean.setOutCurrency("EUR");
        bean.convert();
        assertEquals("1.22",bean.getOutValue());
    }
}
