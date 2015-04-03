package net.suntec.oauth.check;

public class TestString {
	public static void main(String[] args) {
		String str = "OAuth oauth_signature=\"EhpYVEvXPJZzACVihm5ZgWm0%2BGo%3D\", oauth_version=\"1.0\", oauth_nonce=\"2482096433\", oauth_signature_method=\"HMAC-SHA1\", oauth_consumer_key=\"cd1da91bf7d493363b6bfe6defb24e19\", oauth_token=\"72157648978120174-d90e8b401aeae461\", oauth_timestamp=\"1426254133\"";
		String tmp = str.replaceAll("\\\\", "");
		System.out.println(tmp);
	}
}
