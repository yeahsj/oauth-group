package net.suntec.oauth.check.oauth2;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedlyCheckToken extends Oauth2CheckToken implements CheckToken {
	String FDLY_CK_URL = "https://feedly.com/v3/profile?feedlyToken=%s";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(FeedlyCheckToken.class);
		//https://www.baidu.com/?accessToken=AhSekvJ7ImEiOiJTdW50ZWMiLCJlIjoxNDI2ODM3OTUwMjA1LCJpIjoiNTc2MTk1NTgtMTBiNC00ZDY4LTlhMWYtMzhlMTY4OTg3NDg2IiwicCI6NCwidCI6MSwidiI6InByb2R1Y3Rpb24iLCJ3IjoiMjAxNC4yNiIsIngiOiJzdGFuZGFyZCJ9:suntec&refreshToken=AiKdNhx7ImkiOiI1NzYxOTU1OC0xMGI0LTRkNjgtOWExZi0zOGUxNjg5ODc0ODYiLCJ1IjoiMjQ4Njk1Mjc2MyIsImEiOiJTdW50ZWMiLCJwIjo0LCJjIjoxNDI2MjMzMTUwMjA1LCJ2IjoicHJvZHVjdGlvbiIsIm4iOiJlVmI5ZzkyZkQ1YUhIdXA2In0:suntec&uid=57619558-10b4-4d68-9a1f-38e168987486&clientId=suntec
		TokenInfo info = new TokenInfo();
		info.setAccessToken("AhSekvJ7ImEiOiJTdW50ZWMiLCJlIjoxNDI2ODM3OTUwMjA1LCJpIjoiNTc2MTk1NTgtMTBiNC00ZDY4LTlhMWYtMzhlMTY4OTg3NDg2IiwicCI6NCwidCI6MSwidiI6InByb2R1Y3Rpb24iLCJ3IjoiMjAxNC4yNiIsIngiOiJzdGFuZGFyZCJ9:suntec");
		// info.setRefreshToken("AiKdNhx7ImkiOiI1NzYxOTU1OC0xMGI0LTRkNjgtOWExZi0zOGUxNjg5ODc0ODYiLCJ1IjoiMjQ4Njk1Mjc2MyIsImEiOiJTdW50ZWMiLCJwIjo0LCJjIjoxNDI2MjMzMTUwMjA1LCJ2IjoicHJvZHVjdGlvbiIsIm4iOiJlVmI5ZzkyZkQ1YUhIdXA2In0:suntec");
		info.setApiKey("suntec");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("57619558-10b4-4d68-9a1f-38e168987486");
		CheckToken ct = new FeedlyCheckToken();
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
		return String.format(FDLY_CK_URL, info.getAccessToken());
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

}
