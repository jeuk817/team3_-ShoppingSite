package kr.or.bit.action;

public class ActionForward {
	private boolean isRedirect = false;
	private String path=null;
	private String prePath = "/WEB-INF/views/";
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return isRedirect ? path : prePath + path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
