package ua.tqs.hw1;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Pedro Nunes, nmec 59542
 */
public class InterfaceTest {
  private WebDriver driver;
  private final boolean acceptNextAlert = true;
  private final StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCalc() throws Exception {
    driver.get("http://localhost:8080/hw1/");
    driver.findElement(By.id("j_idt2:entry_currency")).click();
    new Select(driver.findElement(By.id("j_idt2:entry_currency"))).selectByVisibleText("EUR");
    driver.findElement(By.id("j_idt2:entry_currency")).click();
    driver.findElement(By.id("j_idt2:out_currency")).click();
    new Select(driver.findElement(By.id("j_idt2:out_currency"))).selectByVisibleText("USD");
    driver.findElement(By.id("j_idt2:out_currency")).click();
    driver.findElement(By.id("j_idt2:entry_value")).click();
    driver.findElement(By.id("j_idt2:entry_value")).clear();
    driver.findElement(By.id("j_idt2:entry_value")).sendKeys("1");
    driver.findElement(By.id("j_idt2:Convert")).click();
    String result=driver.findElement(By.id("out_value")).getAttribute("value");
    assertTrue(result.matches("1.22"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
