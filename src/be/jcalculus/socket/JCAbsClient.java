package be.jcalculus.socket;

public abstract class JCAbsClient extends Thread {

	private String host;
	private int port;

	private String request;
	private String response;

	public String request(String request) {
		String ret = null;
		if (request != null || !"".equals(request)) {
			setResponse("");
			setRequest(request);
			while ("".equals(getResponse())) {
				JCUtils.sleep(100L);
			}
			ret = getResponse();
		}
		return ret;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
