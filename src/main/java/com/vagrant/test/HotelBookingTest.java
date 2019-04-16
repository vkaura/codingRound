package com.vagrant.test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {
	
	WebDriver driver = new ChromeDriver();
	Util util = new Util();
	
	//Creating the object for the hotel booking page
	HotelBookingPage hotelBookingPage = new HotelBookingPage();
	
	@BeforeTest
	public void beforeTest() {
	    	util.setDriverPath();
	    }
    
    @Test
    public void shouldBeAbleToSearchForHotels() {
    	
    	//initiating the PageFactory to find the various WebElements
    	PageFactory.initElements(driver, hotelBookingPage);
        
    	// hit the Cleartrip URL
        driver.get("https://www.cleartrip.com/");
        
        //click on the "Hotels" tab on the left, below the default selection "Flights"
        hotelBookingPage.getHotelLink().click();
        
        util.waitFor(4000);
        
        //enter the locality to book the hotels
        hotelBookingPage.getLocalityText().sendKeys("Indiranagar, Bangalore");
        util.waitFor(10000);
        
        //Pressing the Enter key to choose the locality
        WebElement textbox = driver.findElement(By.id("Tags"));
        textbox.sendKeys(Keys.ENTER);
        
        util.waitFor(6000);
        
        //pick the check-in date
        hotelBookingPage.getCheckInDateSelection().click();
        util.waitFor(2000);
        
        //pick the check-out date
        hotelBookingPage.getCheckOutDateSelection().click();
        util.waitFor(2000);
        
        //enter the selection of the travelers
        new Select(hotelBookingPage.getTravellerSelection()).selectByVisibleText("1 room, 2 adults");
        hotelBookingPage.getSearchButton().click();
        
        util.waitFor(10000);
        
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
