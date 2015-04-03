package net.suntec.oauth.check.oauth2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoursquareCheckToken extends Oauth2CheckToken implements
		CheckToken {
	private static final String FOURS_CK_URL = "https://api.foursquare.com/v2/users/self?oauth_token=%s&v=%s";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(FoursquareCheckToken.class);
		// https://www.baidu.com/?accessToken=L3UMDVENCLUBU5GVXRLN3BA24BFLIG2SCGZEJGBIX1RVL0JS&refreshToken=&uid=87351137&clientId=TO3TKLAZW151EGB3EEBCFKKOYGOTLRPHAZ5NXE1NCG11IGP1#_=_
		TokenInfo info = new TokenInfo();
		info.setAccessToken("L3UMDVENCLUBU5GVXRLN3BA24BFLIG2SCGZEJGBIX1RVL0JS1");
		// info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("TO3TKLAZW151EGB3EEBCFKKOYGOTLRPHAZ5NXE1NCG11IGP1");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("87351137");
		CheckToken ct = new FoursquareCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	public boolean valid(TokenInfo info, String body) {
		JSONObject res = new JSONObject(body);
		if (res.has("error")) {
			return false;
		} else if (res.has("meta")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return String.format(FOURS_CK_URL, info.getAccessToken(),
				df.format(new Date()));
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

}
