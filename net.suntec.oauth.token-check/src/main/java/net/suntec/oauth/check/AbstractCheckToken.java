package net.suntec.oauth.check;

import java.io.IOException;

import net.suntec.oauth.check.oauth1.Oauth1CheckToken;
import net.suntec.util.NetWorkUtil;

import org.apache.http.client.ClientProtocolException;
import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openjava.core.util.StrUtil;

public abstract class AbstractCheckToken implements CheckToken {
	Logger logger = LoggerFactory.getLogger(Oauth1CheckToken.class);

	// protocol
	public static String CHECK_PROTOCOL = "https";

	public abstract String connect(TokenInfo info, Verb verb, String url)
			throws IOException;

	public boolean checkNetwork() {
		String url = getCheckNetWorkURL();
		if (NetWorkUtil.checkByPing(url)) {
			return true;
		} else {
			return NetWorkUtil.checkByConnect(url);
		}
	}

	@Override
	public boolean check(TokenInfo info) {
		logger.info("start check ....... ");
		boolean valid = false;
		try {
			String body = connect(info, getCheckUrlVerb(), getCheckURL(info));
			if (StrUtil.isEmpty(body)) {
				valid = false;
			} else {
				valid = valid(info, body);
			}
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return valid;
	}

	protected abstract boolean valid(TokenInfo info, String body);

	protected abstract String getCheckURL(TokenInfo info);

	protected abstract String getCheckNetWorkURL();

	protected abstract Verb getCheckUrlVerb();
}
