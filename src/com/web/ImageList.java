package com.web;

//게시물 목록을 출력하는 서블릿 정의
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ImageList extends HttpServlet{

	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String id ="c##java";
	String pass = "1234";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	//오라클 연동을 위한 드라이버 로드!!
	public ImageList(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드성공");
			con = DriverManager.getConnection(url,id,pass);
			if(con==null){
				System.out.println("접속실패");
			}else{
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로드 실패"); //톰켓 콘솔에 출력
		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}
	}
	//스트림을 얻기 전에 다국어 인코딩 처리를 먼저하자
	//response.setCharacterEncoding("UTF-8"); //응답시, 인코딩
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		PrintWriter out = response.getWriter();


	try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드성공");
			con = DriverManager.getConnection(url,id,pass);
			if(con==null){
				System.out.println("접속실패");
			}else{
				System.out.println("접속성공");
			}
		}catch(ClassNotFoundException e){
			System.out.println("드라이버 로드 실패"); //톰켓 콘솔에 출력
		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}
	}

		//쿼리실행
		String sql = "select * from gallery";
		try{
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery(); // rs 생성!!

		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}

		//태그의 양이 너무 많으므로, 메모리에 String이 너무 많이 생성된다
		//따라서 변경가능한 문자열을 처리하는 객체인 StringBuilder를 이용
		StringBuilder sb = new StringBuilder();
		sb.append("<table width=\"600px\" border=\"1px\">");
		sb.append("<tr>");
		sb.append("<td width='100px'> Image</td>");
		sb.append("<td width='400px'> Title</td>");
		sb.append("<td width='100px'> Author</td>");
		sb.append("</tr>");
	try{
		while(rs.next()){
			sb.append("<tr>");
			sb.append("<td><img src='/images/"+rs.getString("filename")+"' width='70px'></td>");
			sb.append("<td>"+rs.getString("title")+"</td>");
			sb.append("<td>"+rs.getString("author")+"</td>");
			sb.append("</tr>");
		}
	}catch(java.sql.SQLException e){
	}finally{
		if(rs!=null){try{rs.close();}catch(SQLException e){}}
		if(pstmt!=null){try{pstmt.close();}catch(SQLException e){}}
		if(con!=null){try{con.close();}catch(SQLException e){}}
	}
		sb.append("</table>");

		out.print(sb.toString()); //브라우저에 출력
	}
}
