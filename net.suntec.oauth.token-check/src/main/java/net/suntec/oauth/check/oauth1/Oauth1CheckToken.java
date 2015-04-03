package net.suntec.oauth.check.oauth1;

import java.io.IOException;

import net.suntec.oauth.check.AbstractCheckToken;
import net.suntec.oauth.check.CheckToken;
import net.suntec.oauth.check.Oauth1SignUtil;
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
import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Oauth1CheckToken extends AbstractCheckToken implements
		CheckToken {
	Logger logger = LoggerFactory.getLogger(Oauth1CheckToken.class);

	public String get(TokenInfo info, Verb verb, String url) throws IOException {
		String resBody = null;
		OAuthRequest request = new OAuthRequest(verb, url);
		String headerStr = Oauth1SignUtil.getHeader(request, info);
		logger.info("headerStr: " + headerStr);
		HttpGet httpPost = new HttpGet(url);
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Authorization", headerStr);
		httpPost.addHeader("Accept", "*/*");
		httpPost.setHeader("proxy-connection", "keep-alive");
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

	public String post(TokenInfo info, Verb verb, String url)
			throws IOException {
		String resBody = null;
		OAuthRequest request = new OAuthRequest(verb, url);
		String headerStr = Oauth1SignUtil.getHeader(request, info);
		logger.info("headerStr: " + headerStr);
		HttpPost httpPost = new HttpPost(url);
		// httpPost.setHeader("Content-type", "application/json");
		// httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Authorization", headerStr);
		httpPost.addHeader("Accept", "*/*");
		httpPost.setHeader("proxy-connection", "keep-alive");
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

	public String connect(TokenInfo info, Verb verb, String url)
			throws IOException {
		if (verb.equals(Verb.GET)) {
			return get(info, verb, url);
		} else {
			return post(info, verb, url);
		}
	}

}
