package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Action;

abstract public class AbstractAction implements Action{
	//execute()추상메서드를 가짐
	private String  viewPage;//보여질 뷰페이지 이동
	
	private boolean isRedirect;
	

	//setter getter  

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}// 트루엔  리다이렉트 잉동  펄스엔  포워드 이동

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}/////////////////////////////

