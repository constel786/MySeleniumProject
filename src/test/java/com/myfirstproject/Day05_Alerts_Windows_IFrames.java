package com.myfirstproject;

import com.myfirstproject.utilities.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Set;

public class Day05_Alerts_Windows_IFrames extends TestBase {
    @Test
    public void alertWindowsIframeTest1() {
//        Given Go to https://testpages.herokuapp.com/styled/frames/frames-test.html
        driver.get("https://testpages.herokuapp.com/styled/frames/frames-test.html");
//        Then Assert that "Left List Item 29" is the last element in the "Left"
        /*
        Left List Item 29 element is inside an iframe.
        driver must be switched in this iframe first
        Otherwise driver can not find that element
        1. index --> driver.switchTo().frame(0)
        2. id or name -->  driver.switchTo().frame("left")
        3. webelement --> driver.switchTo().frame(  driver.findElement(By.xpath("//frame[@name='left']"))  );
         */
        driver.switchTo().frame("left");
        //getting all list items in this iframe
        List<WebElement> leftFrameListItems = driver.findElements(By.xpath("//li"));
        for (WebElement each : leftFrameListItems) {
            System.out.println(each.getText());
        }
        WebElement lastLeftItem = leftFrameListItems.get(leftFrameListItems.size() - 1);
        Assert.assertEquals("Expected and Actual Not Equal", "Left List Item 29", lastLeftItem.getText()); //message will only show up if test case fails

//        And Assert that "Middle List Item 39" is the last element in the "Middle"
        /*
        NOTE: at this point driver is in the left frame
        so it cannot see anything outside of that frame
        and we must switch to the middle frame to work the elements in the middle frame
        1. index -> driver.switchTo().frame( )
        2. id or name ->  driver.switchTo().frame("middle")
        3. webelement -> driver.switchTo().frame( driver.findElement(By.xpath("//frame[@name='left']")) );
         */

        driver.switchTo().defaultContent(); //or .parentFrame() to go to the parent frame before going somewhere else
        WebElement middleFrame = driver.findElement(By.xpath("//frame[@name='middle']"));
        driver.switchTo().frame(middleFrame); //switching by webelement
        // getting all middle frame elements
        List<WebElement> middleFrameListItems = driver.findElements(By.xpath("//li"));
        for (WebElement each : middleFrameListItems) {
            System.out.println(each.getText());
        }

        String lastItemText = middleFrameListItems.get(middleFrameListItems.size() - 1).getText();
        Assert.assertEquals("Expected and Actual Not Equal", "Middle List Item 39", lastItemText);
    }

    @Test
        public void alertWindowsIframeTest2(){
//        When Navigate to https://testpages.herokuapp.com/styled/windows-test.html
        driver.get("https://testpages.herokuapp.com/styled/windows-test.html");
//        When Click on "Alerts In A New Window From JavaScript"
        driver.findElement(By.id("goalerts")).click();
//        And Switch to new window
        /*
        NOTE:
        if a neew windows opens, then we must switch to that window
          1. get windows handles
            getWindowHandle()-> returns only current window handle as String
            getWindowHandles()-> returns ALL window handle as Set<String>
          2. driver.switchTo(windowHandle)
         */
        String window1Handle = driver.getWindowHandle();
        System.out.println(window1Handle);
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println(allWindowHandles);
        //we need to switch window2 handle
        for (String eachHandle : allWindowHandles){
            if (!eachHandle.equals(window1Handle)){
                System.out.println(eachHandle);
                driver.switchTo().window(eachHandle);
            }
        }
//        And Click on "Show alert box" button
        driver.findElement(By.id("alertexamples")).click(); //clicking the button that is on window 2
//        And Accept alert
        driver.switchTo().alert().accept();
        And Click on "Show confirm box" button
//        And Cancel alert
//        Then Assert that alert is canceled
//        When Click on "Show prompt box" button
//        And Send "Hello World!" to the alert
//        Then Assert that "Hello World!" is sent
    }
}
