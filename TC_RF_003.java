package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.CommonUtils;

public class TC_RF_003 {
	
	    @Test
        public void verifyRegisterAccountWithAllFields() {
        	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("aruna");
		driver.findElement(By.id("input-lastname")).sendKeys("vanka");
		
		driver.findElement(By.name("email")).sendKeys(CommonUtils.generteBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("1234509876");
		driver.findElement(By.id("input-password")).sendKeys("34512");
		driver.findElement(By.id("input-confirm")).sendKeys("34512");
		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		//assertion is for verifications	
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		
		String expectedProperDetailsOne = "Your Account Has Been Created!";
		String expectedPropertDetailsTwo = "Congratulations! Your new account has been successfully created!";
		String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String expectedProperDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
		String expectedProperDetailsSix = "contact us";
		
		String actualProperDeatils = driver.findElement(By.id("content")).getText();
		
		Assert.assertTrue(actualProperDeatils.contains(expectedProperDetailsOne));
		Assert.assertTrue(actualProperDeatils.contains(expectedPropertDetailsTwo));
		Assert.assertTrue(actualProperDeatils.contains(expectedProperDetailsThree));
		Assert.assertTrue(actualProperDeatils.contains(expectedProperDetailsFive));
		Assert.assertTrue(actualProperDeatils.contains(expectedProperDetailsSix));
		
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		driver.quit();
		
	}


}
