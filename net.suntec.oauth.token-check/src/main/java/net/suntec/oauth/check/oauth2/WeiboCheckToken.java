package net.suntec.oauth.check.oauth2;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeiboCheckToken extends Oauth2CheckToken implements CheckToken {
	String WB_CK_URL = "https://api.weibo.com/2/users/show.json?access_token=%s&uid=%s";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(WeiboCheckToken.class);
		// https://www.baidu.com/?accessToken=2.007MBDZFLadIXC987bd6b61fgnjKxB&refreshToken=&uid=5098555518&clientId=2321943535
		TokenInfo info = new TokenInfo();
		info.setAccessToken("2.007MBDZFLadIXC987bd6b61fgnjKxB");
		// info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("2321943535");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("5098555518");
		CheckToken ct = new WeiboCheckToken();
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
		return String.format(WB_CK_URL, info.getAccessToken(), info.getUid());
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

}
