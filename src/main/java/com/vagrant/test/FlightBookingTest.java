package com.vagrant.test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.vagrant.util.Util;

public class FlightBookingTest {

    WebDriver driver = new ChromeDriver();
    Util util = new Util();
    
    @BeforeTest
    public void beforeTest() {
    	util.setDriverPath();
    }
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        driver.get("https://www.cleartrip.com/");
        util.waitFor(2000);
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        util.waitFor(10000); //increased the wait time from 2 seconds to 6 seconds such that the city name populates properly
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();
        
        util.waitFor(6000); //adding wait

        driver.findElement(By.id("ToTag")).clear();                 //toTag to ToTag
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");       //toTag to ToTag

        //wait for the auto complete options to appear for the destination

        util.waitFor(6000); //increased the wait time from 2 seconds to 4 seconds such that the city name populates properly
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        
        util.waitFor(6000); //adding wait

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[7]/a")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        util.waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

    }
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    } 

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
