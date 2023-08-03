package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

public class TestNG {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
       

        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        
        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + "sanu34200" + ":" + "OOKPpsA1BLvCGi8BxNaIliyhIjYk1QJoVukRWj6yYgObDV9wxQ" + hub), caps);
        
    }

    @Test
    public void basicTest() throws InterruptedException {
      
        System.out.println("Loading Url");

      String url = "https://www.lambdatest.com/";
         
                driver.get(url);
                driver.manage().window().maximize();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
         
         
                WebElement login = driver.findElement(By.linkText("Login"));
                System.out.println("Clicking on the login element in the main page");
                login.click();
         
                driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
         
                WebElement email = driver.findElement(By.id("email"));
                WebElement password = driver.findElement(By.id("password"));
                WebElement loginButton = driver.findElement(By.id("login-button"));
         
                email.clear();
                System.out.println("Entering the email");
                email.sendKeys("sanu34200@gmail.com");
         
                password.clear();
                System.out.println("entering the password");
                password.sendKeys("Lambdatest@123");
         
                System.out.println("Clicking login button");
                loginButton.click();
                
               
                WebDriverWait wait = new WebDriverWait(driver, 100);
                System.out.println(wait.until(ExpectedConditions.titleContains("Welcome")));
                String actualTitle = driver.getTitle();
                System.out.println(actualTitle);
                
                driver.manage().window().maximize();
                
                String title = "Welcome - LambdaTest";
                System.out.println("Verifying the page title has started");
                Assert.assertEquals(actualTitle,title,"Page title doesnt match");
         
                System.out.println("The page title has been successfully verified");
         
                System.out.println("User logged in successfully");
           
        Status = "passed";
        Thread.sleep(150);
        System.out.println("TestFinished");
    }

    
     @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}
