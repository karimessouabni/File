import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserDAO {

	public static String getHash(String password)
			throws UnsupportedEncodingException {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.reset();
		String output = md.digest(password.getBytes("UTF-8")).toString();
		return output;
	}

	
}
