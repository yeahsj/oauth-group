package net.suntec.oauth.check.oauth2;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstagramCheckToken extends Oauth2CheckToken implements CheckToken {
	String INS_CK_URL = "https://%s/v1/users/%s/?count=1&access_token=%s";
	String INS_API_URL = "api.instagram.com";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(InstagramCheckToken.class);
		// https://www.baidu.com/?accessToken=1329925903.e0dd542.3bae2959f9454ffe92dac691b2f4b12d&refreshToken=&uid=1329925903&clientId=e0dd5429269d48288092c802c2c3c50f
		TokenInfo info = new TokenInfo();
		info.setAccessToken("1329925903.e0dd542.3bae2959f9454ffe92dac691b2f4b12d");
		// info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("e0dd5429269d48288092c802c2c3c50f");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("1329925903");
		CheckToken ct = new InstagramCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	public boolean valid(TokenInfo info, String body) {
		JSONObject res = new JSONObject(body);
		if (res.has("error")) {
			return false;
		} else if (res.has("data")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		return String.format(INS_CK_URL, INS_API_URL, info.getUid(),
				info.getAccessToken());
		// return INS_CK_URL + info.getUid() + "/?count=1&access_token=" +
		// info.getAccessToken();
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

	@Override
	protected String getCheckNetWorkURL() {
		return INS_API_URL;
	}

}
