package net.suntec.oauth.check.oauth2;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundcloudCheckToken extends Oauth2CheckToken implements CheckToken {
	String SOUND_CK_URL = "https://api.soundcloud.com/me.json?oauth_token=%s";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(SoundcloudCheckToken.class);
		//http://www.baidu.com/?accessToken=1-108336-98992769-53338e9c46c3d2&refreshToken=&uid=98992769&clientId=c46167bc0cc3552e16c8aa445cc6b817#
		TokenInfo info = new TokenInfo();
		info.setAccessToken("1-108336-98992769-53338e9c46c3d2");
		// info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("c46167bc0cc3552e16c8aa445cc6b817");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("98992769");
		CheckToken ct = new SoundcloudCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	public boolean valid(TokenInfo info, String body) {
		JSONObject res = new JSONObject(body);
		if (res.has("error")) {
			return false;
		} else if (res.has("id")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		return String.format(SOUND_CK_URL, info.getAccessToken());
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

}
