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

//클라이언트가 웹브라우저인 경우엔, 테그를 출력하면 되지만 그외에 디바이스라면 보다 중립적인 형태의 데이터를 제공하는게 좋다!!
//따라서 현재 가장 많이 사용되는 포멧은 json, xml 이다!
//공공데이터 포털의 측면에서 개발하는 느낌..
public class JsonList extends HttpServlet{

	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String id ="c##java";
	String pass = "1234";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	//오라클 연동을 위한 드라이버 로드!!
	public JsonList(){
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		//스트림을 얻기 전에 다국어 인코딩 처리를 먼저하자
		response.setCharacterEncoding("UTF-8"); //응답시, 인코딩
		response.setContentType("text/html;charset=utf-8");
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
			}catch(SQLException e){
				e.printStackTrace();
			}
		int total=0;
		//쿼리실행
		String sql = "select * from gallery";
		
		try{
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery(); // rs 생성!!
			rs.last(); //마지막 로우로 보낸 후 건수를 가져와보자
			total = rs.getRow();	
		}catch(SQLException e){
			e.printStackTrace();
		}

		//태그의 양이 너무 많으므로, 메모리에 String이 너무 많이 생성된다
		//따라서 변경가능한 문자열을 처리하는 객체인 StringBuilder를 이용
		StringBuilder sb = new StringBuilder();
		try{
			sb.append("{");
			sb.append("\"dataList\":[");
			//rs사용전에 다시 위치를 원상복귀!!
			rs.beforeFirst();
			for(int i=1;i<=total;i++){
				rs.next();
				sb.append("{");
				sb.append("\"title\":\""+rs.getString("title")+"\",");
				sb.append("\"author\":\""+rs.getString("author")+"\",");
				sb.append("\"filename\":\""+rs.getString("filename")+"\"");
				if(i<total){ //총 갯수가보다 작을때까지만 쉼표찍자!!
					sb.append("},");
				}else{
					sb.append("}");
				}
			}
			sb.append("]");
			sb.append("}");
	}catch(java.sql.SQLException e){
	}finally{
		if(rs!=null){try{rs.close();}catch(SQLException e){}}
		if(pstmt!=null){try{pstmt.close();}catch(SQLException e){}}
		if(con!=null){try{con.close();}catch(SQLException e){}}
	}
		out.print(sb.toString()); //브라우저에 출력
	}
}
