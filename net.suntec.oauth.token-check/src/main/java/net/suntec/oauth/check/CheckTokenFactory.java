package net.suntec.oauth.check;

public interface CheckTokenFactory {
	public CheckToken create(String appType);
}
