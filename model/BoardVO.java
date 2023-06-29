package board.model;

import java.sql.Timestamp;

public class BoardVO {

	
	private int bno;
	private String title;
	private String content;
	private String writer ;
	private java.sql.Timestamp wdate;
	private int viewcnt;
	


			
			
	public BoardVO(int bno,    String title, String content, String writer,Timestamp wdate, int viewcnt )
	{
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.wdate = wdate;
		this.viewcnt = viewcnt;
		
		
	}





	public int getBno() {
		return bno;
	}





	public void setBno(int bno) {
		this.bno = bno;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public String getWriter() {
		return writer;
	}





	public void setWriter(String writer) {
		this.writer = writer;
	}





	public java.sql.Timestamp getWdate() {
		return wdate;
	}





	public void setWdate(java.sql.Timestamp wdate) {
		this.wdate = wdate;
	}





	public int getViewcnt() {
		return viewcnt;
	}





	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	
	
	
	
}////////////////////////////////////
