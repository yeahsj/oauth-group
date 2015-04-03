package net.suntec.oauth.check;

import org.scribe.model.Verb;

public interface CheckToken {
	public boolean check(TokenInfo info);

	public boolean valid(TokenInfo info, String body);

	public String getCheckURL(TokenInfo info);

	public Verb getCheckUrlVerb();
}
