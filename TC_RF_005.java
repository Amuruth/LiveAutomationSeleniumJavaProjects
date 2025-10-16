package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.CommonUtils;

public class TC_RF_005 {
	
	@Test
	public void verifyRegisteringAccountBySubscribeToNewsLetter() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("chan");
		driver.findElement(By.id("input-lastname")).sendKeys("wang");
		
		driver.findElement(By.name("email")).sendKeys(CommonUtils.generteBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("5674839202");
		
		driver.findElement(By.id("input-password")).sendKeys("chan123");
		driver.findElement(By.id("input-confirm")).sendKeys("chan123");
		
		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click();
		//driver.findElement(By.xpath("//input[@name='agree'][@value=1]")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Newsletter']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).isSelected());
		driver.quit();
	}


}
