package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class MemberDDL {
	public boolean insert(MembersDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DBConnect().getConn();
			System.out.println(conn);
//			conn = DBConnect.initConnection(); // Connection 객체에서 conn 받아오기
			String query = "insert into members"
							+ "(userid, userpass, username, useremail, postcode, addr, detailaddr, tel, uip)"
							+ "values"
							+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getUserpass());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getUseremail());
			pstmt.setInt(5, dto.getPostcode());
			pstmt.setString(6, dto.getAddr());
			pstmt.setString(7, dto.getDetailaddr());
			pstmt.setString(8, dto.getTel());
			pstmt.setString(9, dto.getUip());
			flag = pstmt.executeUpdate();
			System.out.println(flag);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		if(flag > 0) {
			return true; // 성공
		}else {
			return false; // 실패
		}
	}
	
	//member 수정하는 매소드
	
	public int update(int level, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			
			conn = new DBConnect().getConn();
			String query = "";
				query = "update members set level = ? where num = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, level);
				pstmt.setInt(2, num);
				flag = pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
			return flag; 
		
	}
	public boolean update(MembersDTO dto, String user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			
			conn = new DBConnect().getConn();
			String query = "";
			String userpass = (String) dto.getUserpass();
			if(userpass == null || userpass.isEmpty()) {
				query = "update members set username=?, useremail=?, postcode=?, addr=?, detailaddr=?, tel=?, uip=?, wdate=?  where userid = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getUsername());
				pstmt.setString(2, dto.getUseremail());
				pstmt.setInt(3, dto.getPostcode());
				pstmt.setString(4, dto.getAddr());
				pstmt.setString(5, dto.getDetailaddr());
				pstmt.setString(6, dto.getTel());
				pstmt.setString(7, dto.getUip());
				pstmt.setString(8, dto.getWdate());
				pstmt.setString(9, user);
			}else {
				query = "update members set userpass=?, username=?, useremail=?, postcode=?, addr=?, detailaddr=?, tel=?, uip=?, wdate=? where userid = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.getUserpass());
				pstmt.setString(2, dto.getUsername());
				pstmt.setString(3, dto.getUseremail());
				pstmt.setInt(4, dto.getPostcode());
				pstmt.setString(5, dto.getAddr());
				pstmt.setString(6, dto.getDetailaddr());
				pstmt.setString(7, dto.getTel());
				pstmt.setString(8, dto.getUip());
				pstmt.setString(9, dto.getWdate());
				pstmt.setString(10, user);
			}
			flag = pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		if(flag > 0) {
			return true; // 성공
		}else {
			return false; // 실패
		}
	}
	//Allselect
	public static int getAllSelect() {
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		String sql = null;
		int allCount = 0;
		sql ="select count(*) from members";
		try {
			conn = new DBConnect().getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				allCount = rs.getInt(1);
			}
			allCount = rs.getInt(1);

		}catch(SQLException e) {
			
		}finally {
			try{
	            if(rs != null) rs.close();
	            if(st!=null) st.close();
	            if(conn!=null) conn.close();
	         }catch(SQLException e) {}
		}
		return allCount;
	}
	
	// select
	public static Vector<MembersDTO> getSelect(String str) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "select * from members where userid = ?";
	conn = new DBConnect().getConn();
//	conn = DBConnect.initConnection();
	Vector<MembersDTO> data = new Vector<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, str);
			rs = ps.executeQuery();
				while(rs.next()) {
					MembersDTO mb = new MembersDTO();
					mb.setNum(rs.getInt("num"));
					mb.setUserid(rs.getString("userid"));
					mb.setUserpass(rs.getString("userpass"));
					mb.setUsername(rs.getString("username"));
					mb.setUseremail(rs.getString("useremail"));
					mb.setPostcode(rs.getInt("postcode"));
					mb.setAddr(rs.getString("addr"));
					mb.setDetailaddr(rs.getString("detailaddr"));
					mb.setTel(rs.getString("tel"));
					mb.setLevel(rs.getInt("level"));
					mb.setUip(rs.getString("uip"));
					mb.setWdate(rs.getString("wdate"));
					data.add(mb);
				}
		}catch(SQLException e) {
		
		}finally {
			try{
	            if(rs != null) rs.close();
	            if(ps!=null) ps.close();
	            if(conn!=null) conn.close();
	         }catch(SQLException e) {}
		}
		return data;
	}
	//select overload
	public static Vector<MembersDTO> getSelect(String str1,String str2, int opt) {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql =null;
	if(opt == 1) {
			sql = "select userid,userpass from members where username=? and useremail=?";
	}else {
			sql="select userid,userpass from members where userid=? and useremail=?";
	}
	conn = new DBConnect().getConn();
//	conn = DBConnect.initConnection();
	Vector<MembersDTO> data = new Vector<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, str1);
			ps.setString(2, str2);
			rs = ps.executeQuery();
				while(rs.next()) {
					MembersDTO mb = new MembersDTO();
					mb.setUserid(rs.getString("userid"));
					mb.setUserpass(rs.getString("userpass"));
					data.add(mb);
				}
		}catch(SQLException e) {
		
		}finally {
			try{
	            if(rs != null) rs.close();
	            if(ps!=null) ps.close();
	            if(conn!=null) conn.close();
	         }catch(SQLException e) {}
		}
		return data;
	}
	//목록 select overload 
	public static Vector<MembersDTO> getSelect(int limitNum, int listNum) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql =null;
		sql="select * from members order by num desc limit ? , ?";
		
		conn = new DBConnect().getConn();
//		conn = DBConnect.initConnection();
		Vector<MembersDTO> data = new Vector<>();
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, limitNum);
				ps.setInt(2, listNum);
				rs = ps.executeQuery();
				
					while(rs.next()) {
						MembersDTO mb = new MembersDTO();
						mb.setNum(rs.getInt("num"));
						mb.setUserid(rs.getString("userid"));
						mb.setUserpass(rs.getString("userpass"));
						mb.setUsername(rs.getString("username"));
						mb.setUseremail(rs.getString("useremail"));
						mb.setPostcode(rs.getInt("postcode"));
						mb.setAddr(rs.getString("addr"));
						mb.setDetailaddr(rs.getString("detailaddr"));
						mb.setTel(rs.getString("tel"));
						mb.setLevel(rs.getInt("level"));
						mb.setUip(rs.getString("uip"));
						mb.setWdate(rs.getString("wdate"));
						data.add(mb);
					}
			}catch(SQLException e) {
			
			}finally {
				try{
		            if(rs != null) rs.close();
		            if(ps!=null) ps.close();
		            if(conn!=null) conn.close();
		         }catch(SQLException e) {}
			}
			return data;
		}
		
	
	//회원 로그인 성공 실패 판단
	   public int checkLogin(MembersDTO dto){
	      Connection conn = null;
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      int checkUser = 0;
	      String sql = "select * from members where userid=? and userpass=?";
	      try{
	    	 conn = new DBConnect().getConn();
//	         conn = DBConnect.initConnection();  //Connection 객체에서 conn 받아오기
	         
	         ps= conn.prepareStatement(sql);
	         ps.setString(1, dto.getUserid());
	         ps.setString(2, dto.getUserpass());
	         rs = ps.executeQuery();
	         
	         if(rs.next()){
	            checkUser=rs.getInt("level");
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try
	         {
	            if(rs != null) rs.close();
	            if(ps!=null) ps.close();
	            if(conn!=null) conn.close();
	         }
	         catch(Exception e)
	         {
	            
	         }
	      }
	      return checkUser;
	      
	   }
	
}
