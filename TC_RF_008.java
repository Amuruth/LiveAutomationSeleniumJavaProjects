package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.CommonUtils;

public class TC_RF_008 {
	
	@Test
	public void verifyRegisteringAccountByProvidingMismatchingPasswords() {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.get("https://tutorialsninja.com/demo/");
	
	
	driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md'][text()='My Account']")).click();
	driver.findElement(By.linkText("Register")).click();
	driver.findElement(By.id("input-firstname")).sendKeys("Arun");
	driver.findElement(By.id("input-lastname")).sendKeys("Jannu");
	driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generteBrandNewEmail());
	driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
	driver.findElement(By.id("input-password")).sendKeys("1234");
	driver.findElement(By.id("input-confirm")).sendKeys("@123");
	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	driver.findElement(By.xpath("//input[@name='agree'][@value='1']")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	
	String expectedWarningMessgae = "Password confirmation does not match password!";
	
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-confirm']/following-sibling::div")).getText(), expectedWarningMessgae);
	driver.quit();
	
	}
	
	
}
