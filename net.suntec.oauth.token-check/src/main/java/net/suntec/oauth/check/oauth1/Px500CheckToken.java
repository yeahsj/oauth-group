package net.suntec.oauth.check.oauth1;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Px500CheckToken extends Oauth1CheckToken implements CheckToken {
	static Logger logger = LoggerFactory.getLogger(Px500CheckToken.class);

	String PX_CHECK_URL = "https://%s/v1/users";
	String PX_API_URL = "api.500px.com";

	@Override
	public boolean valid(TokenInfo info, String body) {
		if (body.contains("user") && body.contains("username")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		return String.format(PX_CHECK_URL, PX_API_URL);
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

	public static void main(String[] args) {
		// https://www.baidu.com/?accessToken=GqS9VN2huuL69U3I23CJNbyNF3NUdf3bD99EYuD9&refreshToken=sX9tqONDxhvcnYar9XTsYNbtAkvhzVTfkUD2vVVG&uid=8690335&clientId=qqM8miBWEezUTqohpx9KaVkwazPwzziqPnOJmhrS
		TokenInfo info = new TokenInfo();
		info.setAccessToken("GqS9VN2huuL69U3I23CJNbyNF3NUdf3bD99EYuD9");
		info.setRefreshToken("sX9tqONDxhvcnYar9XTsYNbtAkvhzVTfkUD2vVVG");
		info.setApiKey("qqM8miBWEezUTqohpx9KaVkwazPwzziqPnOJmhrS");
		info.setSecret("4o2QueEyXjCr9Gni1Yg0wcslyqNgpXvA3FtNqRsr");
		info.setUid("8690335");
		CheckToken ct = new Px500CheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	protected String getCheckNetWorkURL() {
		return PX_API_URL;
	}

}
