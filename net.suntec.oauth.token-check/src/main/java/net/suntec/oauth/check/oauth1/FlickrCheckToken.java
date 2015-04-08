package net.suntec.oauth.check.oauth1;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlickrCheckToken extends Oauth1CheckToken implements CheckToken {
	static Logger logger = LoggerFactory.getLogger(TwitterCheckToken.class);

	String FL_API_URL = "api.flickr.com";
	String FL_CHECK_URL = "%s://%s/services/rest/?format=json&method=flickr.auth.oauth.checkToken&api_key=%s&auth_token=%s";

	public String getCheckURL(TokenInfo info) {
		return String.format(FL_CHECK_URL, CHECK_PROTOCOL, FL_API_URL,
				info.getApiKey(), info.getAccessToken());
	}

	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

	@Override
	public boolean valid(TokenInfo info, String body) {
		if (body.contains("nsid") && body.contains(info.getAccessToken())) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// https://www.baidu.com/?accessToken=72157650708326368-ff1789b39b4b5446&refreshToken=62cd7e3e8ede194d&uid=129313163@N03&clientId=cd1da91bf7d493363b6bfe6defb24e19
		// https://www.baidu.com/?accessToken=2486952763-oxXDZPvyQTnj4AkdtPj6f0Vr7XqgxA4LBDti9fW&refreshToken=LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O&uid=2486952763&clientId=hEAHLthjzf82UkiZeQRA06PGd
		TokenInfo info = new TokenInfo();
		info.setAccessToken("72157650708326368-ff1789b39b4b5446");
		info.setRefreshToken("62cd7e3e8ede194d");
		info.setApiKey("cd1da91bf7d493363b6bfe6defb24e19");
		info.setSecret("28646c0cc1f01625");
		info.setUid("129313163@N03");
		CheckToken ct = new FlickrCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	protected String getCheckNetWorkURL() {
		return FL_API_URL;
	}

}
