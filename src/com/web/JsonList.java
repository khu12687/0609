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

//Ŭ���̾�Ʈ�� ���������� ��쿣, �ױ׸� ����ϸ� ������ �׿ܿ� ����̽���� ���� �߸����� ������ �����͸� �����ϴ°� ����!!
//���� ���� ���� ���� ���Ǵ� ������ json, xml �̴�!
//���������� ������ ���鿡�� �����ϴ� ����..
public class JsonList extends HttpServlet{

	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String id ="c##java";
	String pass = "1234";
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	//����Ŭ ������ ���� ����̹� �ε�!!
	public JsonList(){
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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		//��Ʈ���� ��� ���� �ٱ��� ���ڵ� ó���� ��������
		response.setCharacterEncoding("UTF-8"); //�����, ���ڵ�
		response.setContentType("text/html;charset=utf-8");
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
			}catch(SQLException e){
				e.printStackTrace();
			}
		int total=0;
		//��������
		String sql = "select * from gallery";
		
		try{
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery(); // rs ����!!
			rs.last(); //������ �ο�� ���� �� �Ǽ��� �����ͺ���
			total = rs.getRow();	
		}catch(SQLException e){
			e.printStackTrace();
		}

		//�±��� ���� �ʹ� �����Ƿ�, �޸𸮿� String�� �ʹ� ���� �����ȴ�
		//���� ���氡���� ���ڿ��� ó���ϴ� ��ü�� StringBuilder�� �̿�
		StringBuilder sb = new StringBuilder();
		try{
			sb.append("{");
			sb.append("\"dataList\":[");
			//rs������� �ٽ� ��ġ�� ���󺹱�!!
			rs.beforeFirst();
			for(int i=1;i<=total;i++){
				rs.next();
				sb.append("{");
				sb.append("\"title\":\""+rs.getString("title")+"\",");
				sb.append("\"author\":\""+rs.getString("author")+"\",");
				sb.append("\"filename\":\""+rs.getString("filename")+"\"");
				if(i<total){ //�� ���������� ������������ ��ǥ����!!
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
		out.print(sb.toString()); //�������� ���
	}
}
