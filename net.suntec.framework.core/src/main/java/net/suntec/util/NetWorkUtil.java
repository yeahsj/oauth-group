package net.suntec.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NetWorkUtil {
	static Logger logger = LoggerFactory.getLogger(NetWorkUtil.class);

	// 判断网络状态
	public static boolean checkByPing(String url) {
		boolean isValid = false;
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("ping -c 1 " + url);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
				// System.out.println("返回值为:"+line);
			}
			is.close();
			isr.close();
			br.close();

			logger.info(sb.toString());
			if (null != sb && !sb.toString().equals("")) {
				if (sb.toString().toUpperCase().indexOf("TTL") > 0) {
					isValid = true;
				} else {
					isValid = false;
				}
			}
		} catch (Exception e) {
			isValid = false;
			logger.error(e.getMessage());
		}
		return isValid;
	}

	public static boolean checkByConnect(String url) {
		String retIP = null;
		int port = 443;
		try {
			InetAddress remoteAddr = InetAddress.getByName(url);
			Enumeration<NetworkInterface> netInterfaces;
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> localAddrs = ni.getInetAddresses();
				while (localAddrs.hasMoreElements()) {
					InetAddress localAddr = localAddrs.nextElement();
					if (isReachable(localAddr, remoteAddr, port, 5000)) {
						retIP = localAddr.getHostAddress();
						break;
					}
				}
			}
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		if (retIP == null) {
			logger.info("NULL reachable local IP is found!");
			return false;
		} else {
			logger.info("Reachable local IP is found, it is " + retIP);
			return true;
		}
	}

	static boolean isReachable(InetAddress localInetAddr,
			InetAddress remoteInetAddr, int port, int timeout) {

		boolean isReachable = false;
		Socket socket = null;
		try {
			socket = new Socket();
			// 端口号设置为 0 表示在本地挑选一个可用端口进行连接
			SocketAddress localSocketAddr = new InetSocketAddress(
					localInetAddr, 0);
			socket.bind(localSocketAddr);
			InetSocketAddress endpointSocketAddr = new InetSocketAddress(
					remoteInetAddr, port);
			socket.connect(endpointSocketAddr, timeout);
			logger.info("SUCCESS - connection established! Local: "
					+ localInetAddr.getHostAddress() + " remote: "
					+ remoteInetAddr.getHostAddress() + " port" + port);
			isReachable = true;
		} catch (IOException e) {
			logger.error("FAILRE - CAN not connect! Local: "
					+ localInetAddr.getHostAddress() + " remote: "
					+ remoteInetAddr.getHostAddress() + " port" + port);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Error occurred while closing socket..");
				}
			}
		}
		return isReachable;
	}

	public static void main(String[] args) {
		boolean isValid = NetWorkUtil.checkByPing("www.baidu.com");
		logger.info("isValid : " + isValid);
	}
}
