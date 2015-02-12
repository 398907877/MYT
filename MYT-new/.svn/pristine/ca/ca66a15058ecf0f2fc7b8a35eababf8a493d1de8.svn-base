package com.yihu.myt;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.coreframework.remoting.Url;
import com.yihu.config.ConfigReader;

/**
 * @author Administrator
 * 
 */
public class ConfigUtil {

	private static ConfigUtil instance = null;
	private static final String cfg = "sys.xml";

	public Url getCenterServerUrl() {
		return centerServerUrl;
	}

	private Url centerServerUrl;
	private Url logUrl;
	private Url sessionUrl;

	public Url getSessionUrl() {
		return sessionUrl;
	}

	public Url getLogUrl() {
		return logUrl;
	}

	private int port;

	public int getPort() {
		return port;
	}

	private ConfigUtil() throws Exception {
		init();
	}

	private void init() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(cfg);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(inputStream);
		String centerServerUrl = doc.getRootElement().elementTextTrim(
				"CenterServerUrl");
		if (centerServerUrl != null && !centerServerUrl.equals("")) {
			String[] tmp = centerServerUrl.split(":");
			this.centerServerUrl = new Url(tmp[0], Integer.parseInt(tmp[1]));
		}
		String logUrl = doc.getRootElement().elementTextTrim("LogUrl");
		if (logUrl != null && !logUrl.equals("")) {
			String[] tmp = logUrl.split(":");
			this.logUrl = new Url(tmp[0], Integer.parseInt(tmp[1]));
		}
		String sessionUrl = doc.getRootElement().elementTextTrim("SessionUrl");
		if (sessionUrl != null && !sessionUrl.equals("")) {
			String[] tmp = sessionUrl.split(":");
			this.sessionUrl = new Url(tmp[0], Integer.parseInt(tmp[1]));
		}
		String portStr = doc.getRootElement().elementTextTrim("Port");
		if (portStr != null && !portStr.equals("")) {
			this.port = Integer.parseInt(portStr);
		}
		inputStream.close();
	}

	public static ConfigUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new ConfigUtil();
		}
		return instance;
	}

	public static void create() throws Exception {
		instance = new ConfigUtil();
	}

	/**
	 * 获取Url
	 * 
	 * @param namepair
	 *            配置中心的映射名称
	 * @return
	 */
	public  Url getUrl(String namepair) {
		String ipport = null;
		try {
			ipport = ConfigReader.me(
					this.centerServerUrl.toString())
					.getNamePair(namepair);
			String[] tmp=ipport.split(":");
			//if (!StringUtil.isEmpty(ipport))
				return new Url(tmp[0], Integer.parseInt(tmp[1]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
