package net.suntec.oauth.check;

import net.suntec.oauth.check.oauth1.FlickrCheckToken;
import net.suntec.oauth.check.oauth1.PocketCheckToken;
import net.suntec.oauth.check.oauth1.Px500CheckToken;
import net.suntec.oauth.check.oauth1.TwitterCheckToken;
import net.suntec.oauth.check.oauth2.FacebookCheckToken;
import net.suntec.oauth.check.oauth2.FeedlyCheckToken;
import net.suntec.oauth.check.oauth2.FoursquareCheckToken;
import net.suntec.oauth.check.oauth2.InstagramCheckToken;
import net.suntec.oauth.check.oauth2.SoundcloudCheckToken;
import net.suntec.oauth.check.oauth2.WeiboCheckToken;

public class StandardCheckTokenFactory implements CheckTokenFactory {

	@Override
	public CheckToken create(String appType) {
		if (appType.equals(AppTypeConstant.APP_TYPE_TWITTER)) {
			return new TwitterCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_FACEBOOK)) {
			return new FacebookCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_INSTAGRAM)) {
			return new InstagramCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_SOUNDCLOUD)) {
			return new SoundcloudCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_FOURSQUARE)) {
			return new FoursquareCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_VINE)) {
			return new VineCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_FLICKR)) {
			return new FlickrCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_POCKET)) {
			return new PocketCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_PX500)) {
			return new Px500CheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_FEEDLY)) {
			return new FeedlyCheckToken();
		} else if (appType.equals(AppTypeConstant.APP_TYPE_WEIBO)) {
			return new WeiboCheckToken();
		} else {
			return null;
		}
	}

}
