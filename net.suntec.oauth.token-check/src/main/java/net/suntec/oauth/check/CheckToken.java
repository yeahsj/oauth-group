package net.suntec.oauth.check;

public interface CheckToken {
	public boolean checkNetwork();
	public boolean check(TokenInfo info);
}
