package com.web;
/*
javaEE 기반의 서버들은, 자바언어를 해석 및 실행하기 때문에 반드시 자바실행환경이 준비되어 있어야한다.
자바기반의 서버의 특징은 기존에 사용하던 javaSE의 기술을 그대로 사용할 수 있다는 점이다... 따라서 개발자는 상속,
기타 자바의 특징을 그대로 적용하여 기업용 어플리케이션 제작이 가능하다...

아래의 클래스를, 서버에서 실행가능한 클래스로 정의하기 위해서는 반드시 서블릿이라 불리는 클래스를 상속 받아야 한다
*/
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import java.io.IOException;

public class MyServlet extends HttpServlet{
	//클라이언트의 웹브라우저에서 요청이 있을때 동작하는 메서드를 재정의하자!!
	//첫번째 인수인 HttpServletRequest request : 클라이언트의 요청 정보 객체
	//두번째 인수인 HttpServletResponse : 클라이언트에 대한  응답요청정보객체
	
	//throws는 try~catch를 여기서 처리하기 싫을때, 즉 이 메서드를 호출한 자에게 예외처리를 미뤄버리는 것임!! 나중에 알게됨...
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		//문자기반의 출력스트림!!(클라이언트의 브라우저와 연결..)
		PrintWriter out = response.getWriter();
		for(int i=1; i<=9; i++){
			System.out.println(i); //웹브라우저 출력 X
			//톰켓이 실행창(콘솔영역)에 출력!! 까만창...
			//출력스트림으로 브라우저에 출력한다!!
			out.print("<h1>"+i+"</h1>");
		}
	}
}
