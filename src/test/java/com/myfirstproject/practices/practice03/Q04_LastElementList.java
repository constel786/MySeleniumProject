package com.myfirstproject.practices.practice03;

import com.myfirstproject.utilities.TestBase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Q04_LastElementList extends TestBase {
    @Test
    public void test04(){
        /*
 Given
   Go to  https://www.saucedemo.com/
 When
   Enter the username  as "standard_user"
 And
   Enter the password as "secret_sauce"
 And
   Click on login button
 And
   Order products by "Price (low to high)"
 Then
   Assert that last product costs $49.99, first product costs $7.99
  */
       driver.get("https://www.saucedemo.com/");
       driver.findElement(By.id("user-name")).sendKeys("standard_user");
       driver.findElement(By.id("password")).sendKeys("secret_sauce");
       driver.findElement(By.id("login-button")).click();
        new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']"))).selectByValue("lohi");
        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        assertEquals("$49.99", prices.get(prices.size()-1).getText());
        assertEquals("$7.99", prices.get(0).getText());




    }
}
