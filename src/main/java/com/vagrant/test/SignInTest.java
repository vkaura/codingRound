package com.vagrant.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();
    Util util = new Util();
    
    @BeforeTest
    public void beforeTest() {
    	util.setDriverPath();
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get("https://www.cleartrip.com/");
        util.waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        
        //Adding sleep such that the frame loads properly
        util.waitFor(4000);
        
        //Switching to the frame that loads after the SignIn click
        driver.switchTo().frame("modal_window");
        
        //Adding sleep such that the frame loads properly
        util.waitFor(2000);
        
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
       
    }
    
    @AfterTest
    public void afterTest() {
    	driver.quit();
    }   
}
