package Utils;

import java.util.Date;

public class CommonUtils {
	
	public  static String generteBrandNewEmail()//method chaining mechanism
	{
		
		return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "")+"@email.com";	
	}


}
