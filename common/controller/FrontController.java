package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = {
				@WebInitParam(name = "config", 
						value = "C:\\myjava\\javaworkspace\\MYWeb\\src\\main\\webapp\\Command.properties")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String,Object> cmdMap=new HashMap<>();
	
	@Override	
	public void init (ServletConfig conf) throws ServletException{
		System.out.println("init()...");
		String props=conf.getInitParameter("config");
		System.out.println("props==="+props);
		Properties pr= new  Properties();
		try {
			FileInputStream fis=new FileInputStream(props);
		
		pr.load(fis);
		fis.close();
		//String val=pr.getProperty("/index.do);
		//System.out.println("val: "+val);

		Set<Object> set=pr.keySet();
		if(set==null) return;
		for(Object key:set) {
			String cmd =key.toString();//"/index.do"
			String className=pr.getProperty(cmd);//"common.controller.IndexAction"
			if(className!=null)
				className= className.trim();
			System.out.println(cmd+":"+className);
			
			Class<?> cls=Class.forName(className);
			Object cmdInstance=cls.getDeclaredConstructor().newInstance();
			cmdMap.put(cmd,  cmdInstance);
		}//for///////
		System.out.println("cmdMap.size(): "+cmdMap.size());
			
		}catch(Exception e) {
			
			e.printStackTrace();
			throw new ServletException(e);
			
		}
	}//init()-------------------
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			process(req,res);
	}

	
	private void process(HttpServletRequest req, HttpServletResponse res, Object instance) throws ServletException, IOException{
		//System.out.println("process()");
		//http://
		//1. 클리이먼트 의 요청 uri분ㅅㄱ
		 
		
		String cmd= req.getContextPath();
		System.out.println("cmd: "+cmd);
		
		
		Object instances=cmdMap.get(cmd);
		
		
		if(instance==null) {
			System.out.println("Action이 null ");
			throw  new ServletException("Action이 null입니다");
			
		
	} 
		
		System.out.println("instance==>"+instance);
		/////////////////////////////////////////////
		AbstractAction action=(AbstractAction) instance;
		try {
			/////////////////////////
			action.execute(req,res);
			//////////////////////
			String viewPage=action.getViewPage();//뷰페이지 얻기
			boolean isRedirect=action.isRedirect();//이동방식 얻기
			if(isRedirect) {
				//redirect방식 이동
				res.sendRedirect(viewPage);
			}else {
				//fowrard이동
				RequestDispatcher disp=req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}//----------------------------------------------
			
			
		
		
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req,res);
	}

	private void process(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @return the cmdMap
	 */
	public HashMap<String,Object> getCmdMap() {
		return cmdMap;
	}

	/**
	 * @param cmdMap the cmdMap to set
	 */
	public void setCmdMap(HashMap<String,Object> cmdMap) {
		this.cmdMap = cmdMap;
	}
	}
		

		
		
		
		
		