package domain.utils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Sha256 {

	public String encrypt(String raw, String salt) throws NoSuchAlgorithmException {

        // 비밀번호 평문
        String hex = "";

        // "SHA1PRNG"은 알고리즘 이름
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        // SALT 생성
        //String salt = new String(Base64.getEncoder().encode(bytes));
        String rawAndSalt = raw+salt;

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 평문+salt 암호화
        md.update(rawAndSalt.getBytes());
        hex = String.format("%064x", new BigInteger(1, md.digest()));

        return hex;
    }

	public String getSalt() throws NoSuchAlgorithmException{
	    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	    byte[] bytes = new byte[16];
	    random.nextBytes(bytes);
	
	    String salt = new String(Base64.getEncoder().encode(bytes));
	    return salt;
	}
}
