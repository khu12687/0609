package com.web;

/*
�α��� �� �Է¾���� ������ �������̶�� Ȯ���ڸ�, html�� �����Ͽ�
������ �����ϸ� ������, �����̶� ���α׷��� �� �ʿ��� ������
������ ��쿣,  �ڹ�Ŭ������ �����ؾ��Ѵ�.. �̶� �ۼ��Ǵ� �ڹ� Ŭ������
������, �����̶� �Ѵ�
�� �����̶� ������? ���������� ����� �� �ִ�.
����� ������ main �޼��尡 �����Ƿ� �������������δ� ����Ұ��ϴ�..
���� ���������� ���� ��Ŭ���̾�Ʈ���� ���� �� �� �ִ�'

������ ����)
������ ó���� �־ ȿ������ ����!!
why? out.print()�� �±׸� �־�� �ϱ� ������
asp,php�� ���� ������ �ɼ�����..
JSP : ������ ���� �v�� ����ϱ� ���� ��ũ��Ʈ �� ���ߵ�!!
		�������� ������ ó���� ���� ���� �����ϱ� ���� �����!!
Java Server Page == JSP
���������� ����Ǵ� ������!!
*/
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;

public class LoginForm extends HttpServlet{
	//Ŭ���̾�Ʈ�� ��û�� �ް� �� ��û�� ���� ������ ó���ϴ� �޼��� �߿� �ϳ���
	//doGet() ������!! override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		//���ڱ�� ��Ʈ�� ���!!(Ŭ���̾�Ʈ���� ����ϱ� ����)	
		PrintWriter out = response.getWriter();
		//html ������ �ױ׸� ������ ���ڿ��� �ٿ��ֱ� ��������, �ڵ����� ��Ʈ������ �о �����ͼ� �������!!
		FileReader reader=null;
		BufferedReader buffr;
		reader = new FileReader("C:/web_app_DB/javaEE/WebSite/member/login.html");
		buffr = new BufferedReader(reader);

		String data =null;
		while(true){
			data = buffr.readLine();
			if(data==null) break;
			if(data.equals("&")){
				System.out.println("���⿡ 2�ܳ����ž�");
				data=printDan();
			}
			out.print(data); //Ŭ���̾�Ʈ���� ���ڿ� ���!!
		}
		if(buffr!=null){
			buffr.close();
		}
	}

	public String printDan(){
			StringBuilder sb = new StringBuilder();
			for(int i=1; i<=9;i++){
				sb.append("2*"+i+"="+2*i+"\n");
			}
			return sb.toString();
	}
}
