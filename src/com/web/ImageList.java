package com.web;

//�Խù� ����� ����ϴ� ���� ����
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
	//����Ŭ ������ ���� ����̹� �ε�!!
	public ImageList(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε强��");
			con = DriverManager.getConnection(url,id,pass);
			if(con==null){
				System.out.println("���ӽ���");
			}else{
				System.out.println("���Ӽ���");
			}
		}catch(ClassNotFoundException e){
			System.out.println("����̹� �ε� ����"); //���� �ֿܼ� ���
		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}
	}
	//��Ʈ���� ��� ���� �ٱ��� ���ڵ� ó���� ��������
	//response.setCharacterEncoding("UTF-8"); //�����, ���ڵ�
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		PrintWriter out = response.getWriter();


	try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε强��");
			con = DriverManager.getConnection(url,id,pass);
			if(con==null){
				System.out.println("���ӽ���");
			}else{
				System.out.println("���Ӽ���");
			}
		}catch(ClassNotFoundException e){
			System.out.println("����̹� �ε� ����"); //���� �ֿܼ� ���
		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}
	}

		//��������
		String sql = "select * from gallery";
		try{
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery(); // rs ����!!

		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}

		//�±��� ���� �ʹ� �����Ƿ�, �޸𸮿� String�� �ʹ� ���� �����ȴ�
		//���� ���氡���� ���ڿ��� ó���ϴ� ��ü�� StringBuilder�� �̿�
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

		out.print(sb.toString()); //�������� ���
	}
}
