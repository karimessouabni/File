package Administration_des_Fournisseurs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author karim
 *
 */
public  class Utilitaire  {

	public static boolean emailValidator (String email){
		 
		 String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
		 boolean toreturn ;
		 
		 Pattern p = Pattern.compile(regEx);
		 Matcher m = p.matcher(email);
		 
		toreturn = (m.find()) ? true : false ;
		return toreturn ;
		
		}
	
}
