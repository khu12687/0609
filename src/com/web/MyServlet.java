package com.web;
/*
javaEE ����� ��������, �ڹپ� �ؼ� �� �����ϱ� ������ �ݵ�� �ڹٽ���ȯ���� �غ�Ǿ� �־���Ѵ�.
�ڹٱ���� ������ Ư¡�� ������ ����ϴ� javaSE�� ����� �״�� ����� �� �ִٴ� ���̴�... ���� �����ڴ� ���,
��Ÿ �ڹ��� Ư¡�� �״�� �����Ͽ� ����� ���ø����̼� ������ �����ϴ�...

�Ʒ��� Ŭ������, �������� ���డ���� Ŭ������ �����ϱ� ���ؼ��� �ݵ�� �����̶� �Ҹ��� Ŭ������ ��� �޾ƾ� �Ѵ�
*/
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import java.io.IOException;

public class MyServlet extends HttpServlet{
	//Ŭ���̾�Ʈ�� ������������ ��û�� ������ �����ϴ� �޼��带 ����������!!
	//ù��° �μ��� HttpServletRequest request : Ŭ���̾�Ʈ�� ��û ���� ��ü
	//�ι�° �μ��� HttpServletResponse : Ŭ���̾�Ʈ�� ����  �����û������ü
	
	//throws�� try~catch�� ���⼭ ó���ϱ� ������, �� �� �޼��带 ȣ���� �ڿ��� ����ó���� �̷������ ����!! ���߿� �˰Ե�...
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		//���ڱ���� ��½�Ʈ��!!(Ŭ���̾�Ʈ�� �������� ����..)
		PrintWriter out = response.getWriter();
		for(int i=1; i<=9; i++){
			System.out.println(i); //�������� ��� X
			//������ ����â(�ֿܼ���)�� ���!! �â...
			//��½�Ʈ������ �������� ����Ѵ�!!
			out.print("<h1>"+i+"</h1>");
		}
	}
}
