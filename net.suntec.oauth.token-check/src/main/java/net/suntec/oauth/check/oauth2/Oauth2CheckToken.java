package net.suntec.oauth.check.oauth2;

import java.io.IOException;

import net.suntec.oauth.check.AbstractCheckToken;
import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.TokenInfo;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Oauth2CheckToken extends AbstractCheckToken implements
		CheckToken {
	Logger logger = LoggerFactory.getLogger(Oauth2CheckToken.class);

	public String connect(TokenInfo info, Verb verb, String url)
			throws IOException {
		if (verb == Verb.GET) {
			return this.get(info, verb, url);
		} else {
			return this.post(info, verb, url);
		}
	}

	public String get(TokenInfo info, Verb verb, String url) throws IOException {
		String resBody = null;
		HttpGet httpGet = new HttpGet(url);
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.addHeader("Accept", "application/json");
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
			}else{
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

	public String post(TokenInfo info, Verb verb, String url)
			throws IOException {
		String resBody = null;
		HttpPost httpGet = new HttpPost(url);
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.addHeader("Accept", "application/json");
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
	}

}
