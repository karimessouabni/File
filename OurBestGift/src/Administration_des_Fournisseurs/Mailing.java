package Administration_des_Fournisseurs;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @author karim
 *
 */
public class  Mailing {
	
	public static void sendMail(String _to,String _from,String _subject, String text ) {
		   Properties props = new Properties();
		   props.put("mail.smtp.com" , "smtp.gmail.com");
		   Session session  = Session.getDefaultInstance( props , null);
		   String to = _to ;
		   String from = _from ;
		   String subject = _subject ;
		   Message msg = new MimeMessage(session);
		    try {
		      msg.setFrom(new InternetAddress(from));
		      msg.setRecipient(Message.RecipientType.TO , new InternetAddress(to));
		      msg.setSubject(subject);
		      msg.setText(text);
		    }  catch(Exception exc) {
		       }
		 }
}
