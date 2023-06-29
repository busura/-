package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class IndexAction extends AbstractAction {

	@Override
public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("indexAction의  execute()호출된...");
		req.setAttribute("msg", "from IndexAction");
		// 뷰페이지지정 
		this .setViewPage("/index.jsp");
		// 이동방식 지정 
		 this.setRedirect(false);
		
	}
	
}//////////////////////////
 
