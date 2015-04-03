package net.suntec.oauth.check.oauth1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PocketCheckToken extends Oauth1CheckToken implements CheckToken {
	String POCKET_CK_URL = "https://getpocket.com/v3/get";

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(PocketCheckToken.class);
		// http://www.baidu.com/?accessToken=764c7028-499b-82d5-ec5b-27f055&refreshToken=&uid=&clientId=35470-c9c585a13325df779105e30d
		TokenInfo info = new TokenInfo();
		info.setAccessToken("764c7028-499b-82d5-ec5b-27f055");
		info.setSecret("pocket no secret");
		info.setRefreshToken("");
		// info.setRefreshToken("LzDVmRwq1jKbXGIAOtFh26wUIQlgATtPhlx646WzxxJ5O");
		info.setApiKey("35470-c9c585a13325df779105e30d");
		// info.setSecret("QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l");
		info.setUid("");
		CheckToken ct = new PocketCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	@Override
	public boolean valid(TokenInfo info, String body) {
		JSONObject res = new JSONObject(body);
		if (res.has("status")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		return POCKET_CK_URL;
		// + "?count=1&consumer_key=" + info.getApiKey() + "access_token=" +
		// info.getAccessToken();
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.POST;
	}

	@Override
	public String post(TokenInfo info, Verb verb, String url)
			throws IOException {
		String resBody = null;
		// OAuthRequest request = new OAuthRequest(verb, url);
		// String headerStr = Oauth1SignUtil.getHeader(request, info);
		// logger.info("headerStr: " + headerStr);
		HttpPost httpPost = new HttpPost(url);
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.addHeader("Accept", "application/json");
		// httpPost.addHeader("Authorization", headerStr);
		httpPost.addHeader("Accept", "*/*");
		httpPost.setHeader("proxy-connection", "keep-alive");
		httpPost.setHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("X-Accept", "application/x-www-form-urlencoded");

		CloseableHttpClient httpclient = HttpClients.custom().build();
		CloseableHttpResponse response = null;

		// count=1&consumer_key=" + info.getApiKey() + "access_token=" +
		// info.getAccessToken();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("count", "1"));
		nvps.add(new BasicNameValuePair("consumer_key", info.getApiKey()));
		nvps.add(new BasicNameValuePair("access_token", info.getAccessToken()));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpclient.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			logger.info("statusCode: " + statusLine.getStatusCode());
			if (statusLine.getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				resBody = EntityUtils.toString(entity);
				logger.info("message: " + resBody);
			}
			return resBody;
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			response.close();
		}
	}

}
