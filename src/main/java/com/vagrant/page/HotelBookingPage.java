//Creating a HotelBookingPage with different Web Elements

package com.vagrant.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelBookingPage {
	
	@FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    //Adding WebElements for check-in and check-out day using xpath
    
    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[1]/td[7]/a")
    private WebElement checkInDateSelection;
    
    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[1]/a")
    private WebElement checkOutDateSelection;
    
    //Adding getters for the different WebElements of the hotel booking page
    
    public WebElement getHotelLink() {
		return hotelLink;	
    }
    public WebElement getLocalityText() {
		return localityTextBox;	
    }
    
    public WebElement getSearchButton() {
		return searchButton;
    }
    
    public WebElement getTravellerSelection() {
		return travellerSelection;
    }
    
    public WebElement getCheckInDateSelection() {
		return checkInDateSelection;
    }
    
    public WebElement getCheckOutDateSelection() {
		return checkOutDateSelection;
    }
}
