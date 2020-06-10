package com.web;

/*
로그인 폼 입력양식이 순수한 디자인이라면 확장자를, html로 저장하여
문서를 제공하면 되지만, 조금이라도 프로그래밍 언어가 필요한 로직을
수행할 경우엔,  자바클래스로 개발해야한다.. 이때 작성되는 자바 클래스를
가리켜, 서블릿이라 한다
왜 서블릿이라 했을까? 서버에서만 수행될 수 있다.
참고로 서블릿은 main 메서드가 없으므로 독립실행형으로는 실행불가하다..
오직 웹브라우저와 같은 웹클라이언트에서 접근 할 수 있다'

서블릿의 단점)
디자인 처리에 있어서 효율성이 없다!!
why? out.print()로 태그를 넣어야 하기 때문에
asp,php에 비해 경쟁이 될수없다..
JSP : 서블릿을 보다 쉡게 사용하기 위한 스크립트 언어가 개발됨!!
		서블릿으로 디자인 처리를 보다 쉽게 제공하기 위한 방법임!!
Java Server Page == JSP
서버에서만 실행되는 페이지!!
*/
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.PrintWriter;
import javax.servlet.ServletException;

public class LoginForm extends HttpServlet{
	//클라이언트의 요청을 받고 그 요청에 대한 응답을 처리하는 메서드 중에 하나인
	//doGet() 재정의!! override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		//문자기반 스트림 얻기!!(클라이언트에게 출력하기 위해)	
		PrintWriter out = response.getWriter();
		//html 문서의 테그를 일일이 문자열로 붙여넣기 하지말고, 자동으로 스트림으로 읽어서 가져와서 출력하자!!
		FileReader reader=null;
		BufferedReader buffr;
		reader = new FileReader("C:/web_app_DB/javaEE/WebSite/member/login.html");
		buffr = new BufferedReader(reader);

		String data =null;
		while(true){
			data = buffr.readLine();
			if(data==null) break;
			if(data.equals("&")){
				System.out.println("여기에 2단넣을거야");
				data=printDan();
			}
			out.print(data); //클라이언트에게 문자열 출력!!
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
