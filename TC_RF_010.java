package tutorialsninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_010 {
	
	@Test
	public void verifyUsingInvalidEmail() throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.name("firstname")).sendKeys("arun");
		driver.findElement(By.name("lastname")).sendKeys("jannu");
		driver.findElement(By.name("email")).sendKeys("arunjann");
		driver.findElement(By.name("telephone")).sendKeys("1234567890");
		driver.findElement(By.name("password")).sendKeys("12345");
		driver.findElement(By.name("confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();	
		
		Thread.sleep(3000);
		
		//Take screen shot 
		File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot1, new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png"));
		
		Thread.sleep(3000);
				
		//3 type
		Assert.assertFalse(compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png",System.getProperty("user.dir")+"\\Screenshots\\sc1Expected.png"));
		
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("arunjann@");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Thread.sleep(3000);
		
		File srcScreenshot2 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot2, new File(System.getProperty("user.dir")+"\\Screenshots\\sc2Actual.png"));	
		
		Thread.sleep(3000);
		
		Assert.assertTrue(compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\sc2Actual.png",System.getProperty("user.dir")+"\\Screenshots\\sc2Expected.png"));
				
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("arunjann@gmail");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Thread.sleep(3000);
		
		String expectedWarningMessage ="E-Mail Address does not appear to be valid!";
		Thread.sleep(3000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedWarningMessage);
		
		
		
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("arunjann@gmail.");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Thread.sleep(3000);
		
		File srcScreenshot3 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot3, new File(System.getProperty("user.dir")+"\\Screenshots\\sc3Expected.png"));
		
		driver.quit();
		
	}
	
	public boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
		
		//Compare images
				BufferedImage actualBImg = ImageIO.read(new File(actualImagePath));
				BufferedImage expectedBImg = ImageIO.read(new File(expectedImagePath));
				
				//Create Object
				ImageDiffer imgDiffer = new ImageDiffer();
				ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, actualBImg);
				
				return imgDifference.hasDiff();
		
	}
	

}
