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

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
 
import java.util.concurrent.TimeUnit;

public class TestNG {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
       /* String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
          String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;*/
        
        /*
        Steps to run Smart UI project (https://beta-smartui.lambdatest.com/)
        Step - 1 : Change the hub URL to @beta-smartui-hub.lambdatest.com/wd/hub
        Step - 2 : Add "smartUI.project": "<Project Name>" as a capability above
        Step - 3 : Add "((JavascriptExecutor) driver).executeScript("smartui.takeScreenshot");" code wherever you need to take a screenshot
        Note: for additional capabilities navigate to https://www.lambdatest.com/support/docs/test-settings-options/
        */

        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        /*
        Enable Smart UI Project
        caps.setCapability("smartUI.project", "<Project Name>");
        */

        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        //driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
        driver = new RemoteWebDriver(new URL("https://" + "sanu34200" + ":" + "OOKPpsA1BLvCGi8BxNaIliyhIjYk1QJoVukRWj6yYgObDV9wxQ" + hub), caps);
        
    }

    @Test
    public void basicTest() throws InterruptedException {
       // String spanText;
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
                
                /*driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
                //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;        
                
                driver.manage().window().maximize();*/
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
                /*driver.close();
                driver.quit();*/
            
        

       /* System.out.println("Checking Box");
        driver.findElement(By.name("li1")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li2")).click();

        System.out.println("Checking Box");
        driver.findElement(By.name("li3")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li4")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
        driver.findElement(By.id("addbutton")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li1")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li3")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li7")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li8")).click();

        System.out.println("Entering Text");
        driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");

        driver.findElement(By.id("addbutton")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li9")).click();

        // Let's also assert that the todo we added is present in the list.

        spanText = driver.findElementByXPath("/html/body/div/div/div/ul/li[9]/span").getText();
        Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);*/
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