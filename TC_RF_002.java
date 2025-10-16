package tutorialsninja.register;

import java.time.Duration;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.CommonUtils;

@Test
public class TC_RF_002 {

		public void  verifyConfirmationEmail() {
		WebDriver driver = new ChromeDriver();//Ctrl+Shift+ O for import web driver 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://www.amazon.in/");	
		
		driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
		driver.findElement(By.name("email")).sendKeys(CommonUtils.generteBrandNewEmail());
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		driver.findElement(By.xpath("//a[@id='auth-fpp-link-bottom']")).click();
		
		String email = "ajannu824@gmail.com";
		String appPasscode = "infs wynb lhez mwil";
		String link = null;
		String expectedSubject = "amazon.in: Password recovery";
		String expectedEmail = "\"amazon.in\" <account-update@amazon.in>";
		String expectedBodyContent = "Someone is attempting to reset the password of your account.";
		
		driver.findElement(By.name("email"));
		driver.findElement(By.id("continue")).click();
		try {
			Thread.sleep(10000);
			
			System.out.println("Halting the program  intentionally for 10 seconds");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Rani&faith1");
		//driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
		
		//Gmail IMAP configuration
		String host = "imap.gmail.com";
		String port = "993";
		//String mailStoreType = "imap";
		String username = email;
		String appPassword = appPasscode;
		
		try {
			//set mail properties
			Properties properties = new Properties();
			properties.put("mail.stroe.protocol", "imaps");
			properties.put("mail.imap.host", host);
			properties.put("mail.imap.port", port);
			properties.put("mail.imap.ssl.enable", "ture");
			
			//Get the session object
			Session emailSession = Session.getDefaultInstance(properties);
			
			//create IMAP store object and connect with the server
			Store store =emailSession.getStore("imaps");
			store.connect(host, username, appPassword);
			
			//open the inbox folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);//open file in read-only mode
			
			//search for u read emails
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			
			boolean found = false;
			for(int i = messages.length - 1; i >= 0; i--) { 
				Message message = messages[i];
				
				//Get email details
				if(message.getSubject().contains(expectedSubject)) {
				found = true;
				Assert.assertEquals(message.getSubject(), expectedSubject);
				Assert.assertEquals(message.getFrom()[0].toString(), expectedEmail);	
				String actualEmailBody = getTextFromMessage(message);
				Assert.assertTrue(actualEmailBody.contains(expectedBodyContent));
				
				String[] ar = actualEmailBody.split("600\">");			
				String linkPart = ar[1];
				String[] arr = linkPart.split("</a>");
				
				link = arr[0].trim();
				
				break;		
			}
		}
			
			//close the store and folder objects
			inbox.close();
			store.close();	

	}catch (Exception e) {
		e.printStackTrace();
	}
		
		driver.navigate().to(link);
		
		Assert.assertTrue(driver.findElement(By.name("customerResponseDenyButton")).isDisplayed());
		
		if(driver.findElement(By.name("customerResponseDenyButton")).isDisplayed()) {
			System.out.println("Link int he email has taken to the propert page");
		}else {
			System.out.println("Link int he email has not taken us to the propert page");
		}
		driver.quit();

}
	private static String getTextFromMessage(Message message) throws Exception{
		String result = "";
		if(message.isMimeType("text/plain")) {
			result = message.getContentType().toString();
		}else if(message.isMimeType("text/html")) {
			result = message.getContentType().toString();
			
		}else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}	
		return result;
	}
	
	//Recursively extract text from multipart
	private static  String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception{
		StringBuilder  result = new StringBuilder();
		int count = mimeMultipart.getCount();
		for(int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
			result.append(bodyPart.getContent());
		} else if (bodyPart.isMimeType("text/html")) {
			result.append(bodyPart.getContent());			
			
		} else if (bodyPart.getContent() instanceof MimeMultipart) {
			result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
		}
	}
	return result.toString();

   }
}
