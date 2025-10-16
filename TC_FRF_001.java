package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.CommonUtils;

public class TC_FRF_001 {
	
    @Test
	public void verifyRegisterWithMandatoryFields() {
		
		WebDriver driver = new ChromeDriver();//ctrl+shift=O = for imports
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("arun");
		driver.findElement(By.id("input-lastname")).sendKeys("jannu");
		
		driver.findElement(By.name("email")).sendKeys(CommonUtils.generteBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12543");
		driver.findElement(By.id("input-confirm")).sendKeys("12543");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		//assertion is for verifications	
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(),expectedHeading);
		String actualProperDetailsOne = "Congratulations! Your new account has been successfully created!";
        String properDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String expexctedProperDetails = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(expexctedProperDetails.contains(actualProperDetailsOne));
        Assert.assertTrue(expexctedProperDetails.contains(properDetailsTwo));
        
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        driver.quit();
	}
	
	public  String generteNewEmail()//method chaining mechanism
	{
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+"@gmail.com";	
	}

}
