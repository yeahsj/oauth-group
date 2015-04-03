package net.suntec.oauth.check;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VineCheckToken extends AbstractCheckToken {
	// https://api.vineapp.com/users/authenticate
	static Logger logger = LoggerFactory.getLogger(VineCheckToken.class);

	String VINE_CHECK_URL = "https://api.vineapp.com/users/me";

	@Override
	public boolean valid(TokenInfo info, String body) {
		JSONObject res = new JSONObject(body);
		if (res.has("success")) {
			boolean sucStr = (boolean) res.get("success");
			return sucStr;
		} else {
			return false;
		}
	}

	@Override
	public String getCheckURL(TokenInfo info) {
		return VINE_CHECK_URL;
	}

	@Override
	public Verb getCheckUrlVerb() {
		return Verb.GET;
	}

	@Override
	public String connect(TokenInfo info, Verb verb, String url)
			throws IOException {
		String resBody = null;
		HttpGet httpGet = new HttpGet(url);
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.addHeader("Accept", "application/json");
		httpGet.addHeader("vine-session-id", info.getAccessToken());
		httpGet.addHeader("Accept", "*/*");
		httpGet.setHeader("proxy-connection", "keep-alive");
		CloseableHttpClient httpclient = HttpClients.custom().build();
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpGet);
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
		//

	}

	public static void main(String[] args) throws IOException {
		// login();
		check();
	}

	private static void check() {
		TokenInfo info = new TokenInfo();
		info.setAccessToken("1183382645728501760-34fcf3ed-29c2-4664-bd4d-7dba42f6305e");
		info.setRefreshToken("");
		info.setApiKey("vine");
		info.setSecret("vine not secret");
		info.setUid("1183382645728501760");
		CheckToken ct = new VineCheckToken();
		boolean valid = ct.check(info);
		logger.info("valid : " + valid);
	}

	public static String login() throws IOException {
		String resBody = "";
		String url = "https://api.vineapp.com/users/authenticate";
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "iauto.apps@gmail.com"));
		nvps.add(new BasicNameValuePair("password", "PsetSuntec"));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset
				.forName("UTF-8")));

		CloseableHttpClient httpclient = HttpClients.custom().build();
		CloseableHttpResponse response = null;

		try {
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
