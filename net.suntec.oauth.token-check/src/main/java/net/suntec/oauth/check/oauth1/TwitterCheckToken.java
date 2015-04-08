package net.suntec.oauth.check.oauth1;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterCheckToken extends Oauth1CheckToken implements CheckToken {
	static Logger logger = LoggerFactory.getLogger(TwitterCheckToken.class);

	String TW_CHECK_URL = "https://%s/1.1/account/verify_credentials.json";
	String TW_API_URL = "api.twitter.com";

	public static void main(String[] args) {
		// https://www.baidu.com/?accessToken=2486952763-oxXDZPvyQTnj4AkdtPj6f0Vr7XqgxA4LBDti9fW&refreshToken=LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O&uid=2486952763&clientId=hEAHLthjzf82UkiZeQRA06PGd
		TokenInfo info = new TokenInfo();
		info.setAccessToken("2486952763-oxXDZPvyQTnj4AkdtPj6f0Vr7XqgxA4LBDti9fW");
		info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("hEAHLthjzf82UkiZeQRA06PGd");
		info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("2486952763");
		CheckToken ct = new TwitterCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	public boolean valid(TokenInfo info, String body) {
		JSONObject res = new JSONObject(body);
		if (res.has("id")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		return String.format(TW_CHECK_URL, TW_API_URL);
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

	@Override
	protected String getCheckNetWorkURL() {
		return TW_API_URL;
	}

}
