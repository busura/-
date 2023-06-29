package board.model;

import java.sql.*;
import java.util.*;

import common.util.DBUtil;

public class BoardDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public int insertBoard(BoardVO vo) throws SQLException {
		try {
			con = DBUtil.getCon();
			StringBuilder buf = new StringBuilder("insert into board(bno,writer,title")
					.append(" ,content, wdate, viewcnt)").append(" values(board_seq.nextval,?,?,?,systimestamp,0,?,?)");
			String sql = buf.toString();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getWriter());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContent());

			return ps.executeUpdate();
		} finally {
			close();
		}
	}// ------------------------------------------------

	public List<BoardVO> listBoard() throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = "select * from board order by num desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// ------------------------------------------------

	public List<BoardVO> listBoard(int start, int end) throws SQLException {
		try {
			con = DBUtil.getCon();
			StringBuilder buf = new StringBuilder("select * from ( ").append(" select rownum rn, A.* from")
					.append(" (select * from board order by bno desc) A )").append(" where rn between ? and ?");
			String sql = buf.toString();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// ------------------------------------------------

	public List<BoardVO> findBoard(int start, int end, String findType, String findKeyword) throws SQLException {
		try {
			con = DBUtil.getCon();
			String colName = getColumnName(findType);// 검색유형 관련한 컬럼명 얻어오기
			StringBuilder buf = new StringBuilder("select * from ( ").append(" select rownum rn, A.* from")
					.append(" (select * from board where " + colName + " like ? ").append(" order by bno desc) A ) ")
					.append(" where rn between ? and ?");
			String sql = buf.toString();
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + findKeyword + "%");
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			return makeList(rs);
		} finally {
			close();
		}
	}// ------------------------------------------------

	public List<BoardVO> makeList(ResultSet rs) throws SQLException {
		List<BoardVO> arr = new ArrayList<>();
		while (rs.next()) {
			int bno = rs.getInt("bno");
			String writer = rs.getString("writer");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Timestamp wdate = rs.getTimestamp("wdate");
			int viewcnt = rs.getInt("viewcnt");

			BoardVO vo = new BoardVO(bno, writer, title, content, wdate, viewcnt);
			arr.add(vo);
		}
		return arr;
	}// ------------------------------------------------

	public String getColumnName(String type) {
		String colName = "";
		switch (type) {
		case "1":
			colName = "title";
			break;
		case "2":
			colName = "writer";
			break;
		case "3":
			colName = "content";
			break;
		}
		return colName;
	}// -------------------------------------------------

	public int getTotalCount(String type, String keyword) throws SQLException {
		try {
			con = DBUtil.getCon();
			StringBuilder buf = new StringBuilder("select count(bno) cnt from board ");
			if (!type.trim().isEmpty() && !keyword.trim().isEmpty()) {
				// 검색유형과 검색어가 들어왔다면
				String colName = getColumnName(type);
				buf.append(" where " + colName + " like ?");
			}
			String sql = buf.toString();
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			if (!type.trim().isEmpty() && !keyword.trim().isEmpty()) {
				ps.setString(1, "%" + keyword + "%");
			}
			rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			return cnt;
		} finally {
			close();
		}
	}// ------------------------------------------------

	/** 글번호(num -pk)로 글내용 보기 */
	public BoardVO viewBoard(int bno) throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = "select * from board where bno=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			List<BoardVO> arr = makeList(rs);
			if (arr == null || arr.size() == 0)
				return null;

			return arr.get(0);
		} finally {
			close();
		}
	}// ------------------------------------------------

	/** 글 삭제 */
	public int deleteBoard(int bno) throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = "delete from board where bno=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bno);
			return ps.executeUpdate();
		} finally {
			close();
		}
	}// ------------------------------------------------
	/*
	 * public int updateBoard(BoardVO vo) throws SQLException { try {
	 * con=DBUtil.getCon(); StringBuilder buf=new
	 * StringBuilder("update board set title=?,")
	 * .append(" content=?, wdate=systimestamp"); //첨부파일이 있다면
	 * 
	 * if(vo.getFilename()!=null && !vo.getFilename().trim().isEmpty()) {
	 * buf.append(" , filename=?, filesize=? "); } buf.append(" where num=?");
	 * 
	 * String sql=buf.toString(); System.out.println(sql);
	 * ps=con.prepareStatement(sql); ps.setString(1, vo.getTitle()); ps.setString(2,
	 * vo.getContent());
	 * 
	 * if(vo.getFilename()!=null && !vo.getFilename().trim().isEmpty()) {
	 * ps.setString(3, vo.getFilename()); ps.setLong(4, vo.getFilesize());
	 * 
	 * ps.setInt(3, vo.getBno()); }else { ps.setInt(4, vo.getBno()); } return
	 * ps.executeUpdate(); }finally { close(); }
	 * }//------------------------------------------------
	 */

	public boolean updateReadnum(int bno) throws SQLException {
		try {
			con = DBUtil.getCon();
			String sql = "update board set viewcnt=viewcnt+1 where bno=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bno);
			int n = ps.executeUpdate();
			return (n > 0) ? true : false;
		} finally {
			close();
		}
	}// ------------------------------------------------

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// ------------------------------

}/////////////////////////////////////////////////
