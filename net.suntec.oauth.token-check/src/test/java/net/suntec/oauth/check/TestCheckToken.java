package net.suntec.oauth.check;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCheckToken extends TestCase {
	Logger logger = LoggerFactory.getLogger(TestCheckToken.class);
	static List<String> types = new ArrayList<String>();

	static {
		types.add(AppTypeConstant.APP_TYPE_TWITTER);
		types.add(AppTypeConstant.APP_TYPE_FACEBOOK);
		types.add(AppTypeConstant.APP_TYPE_FEEDLY);
		types.add(AppTypeConstant.APP_TYPE_FLICKR);
		types.add(AppTypeConstant.APP_TYPE_FOURSQUARE);
		types.add(AppTypeConstant.APP_TYPE_INSTAGRAM);
		types.add(AppTypeConstant.APP_TYPE_POCKET);
		types.add(AppTypeConstant.APP_TYPE_PX500);
		types.add(AppTypeConstant.APP_TYPE_SOUNDCLOUD);
		types.add(AppTypeConstant.APP_TYPE_VINE);
		types.add(AppTypeConstant.APP_TYPE_WEIBO);
	}

	public void testCheckNet() {
		CheckTokenFactory factory = new StandardCheckTokenFactory();
		for (String type : types) {
			CheckToken ct = factory.create(type);
			boolean isValid = ct.checkNetwork();
			logger.info(type + " is " + isValid);
		}
	}
}
