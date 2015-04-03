package net.suntec.oauth.check;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthRequest;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuth10aServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @项目名称: token-check
 * @功能描述: oauth 1 spec header sign
 * @当前版本：1.0
 * @创建时间: 2015年3月24日 上午9:55:52
 * @author: yeahsj
 * @修改历史:
 */
public class Oauth1SignUtil {
	static Logger logger = LoggerFactory.getLogger(Oauth1SignUtil.class);

	public static String getHeader(OAuthRequest request, TokenInfo tokenInfo) {
		// 2423503237-TJdjmtLi9pHr3VMKZ28QBp4uJOJdkMV3lUthlgv
		String accessToken = tokenInfo.getAccessToken();
		// "2423503237-TJdjmtLi9pHr3VMKZ28QBp4uJOJdkMV3lUthlgv";
		String refreshToken = tokenInfo.getRefreshToken();
		// "6NzaZgqGN4PCqcHeWGt3ysx3hhbdsCWJTJcOIqInNyqNd";
		OAuthConfig config = new OAuthConfig(tokenInfo.getApiKey(),
				tokenInfo.getSecret(), null, SignatureType.Header, null,
				System.out);
		DefaultApi10a api = new TwitterApi();
		OAuth10aServiceImpl service = new OAuth10aServiceImpl(api, config);
		Token token = new Token(accessToken, refreshToken);
		// String url = "https://stream.twitter.com/1.1/statuses/filter.json";
		// OAuthRequest request = new OAuthRequest(Verb.GET, url);
		// request.addQuerystringParameter("delimited", "lenght");
		// request.addQuerystringParameter("track", "twitterapi");
		String header = service.getOauthHeader(token, request);
		return header;
	}

	public static void main(String[] args) {
		String accessToken = "2423503237-TJdjmtLi9pHr3VMKZ28QBp4uJOJdkMV3lUthlgv";
		String refreshToken = "6NzaZgqGN4PCqcHeWGt3ysx3hhbdsCWJTJcOIqInNyqNd";
		OAuthConfig config = new OAuthConfig("hEAHLthjzf82UkiZeQRA06PGd",
				"QEVTsW2ym7D0uq9lig71MzxZCPmtESBgktDgn0ajL2bCIWsN0l", null,
				SignatureType.Header, null, System.out);
		DefaultApi10a api = new TwitterApi();
		OAuth10aServiceImpl service = new OAuth10aServiceImpl(api, config);
		Token token = new Token(accessToken, refreshToken);
		String url = "https://stream.twitter.com/1.1/statuses/filter.json";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		request.addQuerystringParameter("delimited", "lenght");
		request.addQuerystringParameter("track", "twitterapi");
		String header = service.getOauthHeader(token, request);
		logger.info(header);
	}
}
