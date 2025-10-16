package temp;

import java.util.Date;

public class GenerateEmailDemo {

	public static void main(String[] args) {
		
		Date date = new Date();
		String dateString = date.toString();
		String noSPaceDateString = dateString.replaceAll("\\s", "");
		System.out.println(noSPaceDateString);
		String noSpaceAndNoColonsDateString  = noSPaceDateString.replaceAll("\\:", "");
		System.out.println(noSpaceAndNoColonsDateString);
		String emailWithStamp = noSpaceAndNoColonsDateString+"@gmail.com";
		System.out.println(emailWithStamp);

	}
	
	// or this one for creating brand new email
	public  String generteBrandNewEmail()//method chaining mechanism
	{
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+"@email.com";	
	}

}
