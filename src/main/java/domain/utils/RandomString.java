package domain.utils;

public class RandomString {

	public static String generateString() {
		String ids = "abcdefghijklmnopqrstuvwxyz!@#$%^&";

		int rdNum = 0;

		String randomStr = "";

		for (int i = 0; i < 10; i++) {
			rdNum = (int) (Math.random() * 33);

			char ch = ids.charAt(rdNum);
			randomStr = randomStr + ch;
		}
		return randomStr;
	}
}
