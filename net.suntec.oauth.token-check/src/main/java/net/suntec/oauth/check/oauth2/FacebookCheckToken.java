package net.suntec.oauth.check.oauth2;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacebookCheckToken extends Oauth2CheckToken implements CheckToken {
	static String FB_CK_URL = "https://%s/v2.2/me?access_token=%s";
	String FB_API_URL = "graph.facebook.com";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(FacebookCheckToken.class);
		// https://www.baidu.com/?accessToken=CAAXcHA2VErgBAEyVijCerhITNOX4z7wYil22tgrXmR0C30eHnW9unAlIbdxMIFRIni2JbTNA3R91HEWSr21dvhbJ3WK1DtHEOKFyRg7lwbvqgjugyXsz3KLxDq6cT4YzTp64kCH7iFfuTRFesd7RZCjWGvZCxooHVSbrHL3GSoZCC9MXvUzGZBYeoYx9s1aHcrHyHLzbRsa0qXS6ZBEB0&refreshToken=&uid=100008110475808&clientId=1649387928621752#_=_
		TokenInfo info = new TokenInfo();
		info.setAccessToken("CAAXcHA2VErgBAEyVijCerhITNOX4z7wYil22tgrXmR0C30eHnW9unAlIbdxMIFRIni2JbTNA3R91HEWSr21dvhbJ3WK1DtHEOKFyRg7lwbvqgjugyXsz3KLxDq6cT4YzTp64kCH7iFfuTRFesd7RZCjWGvZCxooHVSbrHL3GSoZCC9MXvUzGZBYeoYx9s1aHcrHyHLzbRsa0qXS6ZBEB0");
		// info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("1649387928621752");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("100008110475808");
		CheckToken ct = new FacebookCheckToken();
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
		return String.format(FB_CK_URL, FB_API_URL, info.getAccessToken());
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

	@Override
	protected String getCheckNetWorkURL() {
		return FB_API_URL;
	}

}
