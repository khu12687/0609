<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="java.sql.DriverManager"%>
<%@ page import ="java.sql.Connection"%>
<%@ page import ="java.sql.PreparedStatement"%>
<%@ page import ="java.sql.ResultSet"%>
<%
String url="jdbc:oracle:thin:@localhost:1521:XE";
String user="c##java";
String pass="1234";
Class.forName("oracle.jdbc.driver.OracleDriver");
out.print("드라이버 로드성공");

Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
con = DriverManager.getConnection(url,user,pass);
if(con!=null){
	out.print("접속 성공");
}

String sql="select * from emp";
pstmt = con.prepareStatement(sql);
rs=pstmt.executeQuery(); //쿼리실행~!
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
</head>
<body>

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>EMPNO</th>
    <th>ENAME</th>
    <th>SAL</th>
	<th>COMM</th>
	<th>MGR</th>
	<th>HIREDATE</th>
	<th>DEPTNO</th>
  </tr>
  <%while(rs.next()){%>
	  <tr>
		<td><%=rs.getInt("empno")%></td>
		<td><%=rs.getString("ename")%></td>
		<td><%=rs.getInt("sal")%></td>
		<td><%=rs.getInt("comm")%></td>
		<td><%=rs.getInt("mgr")%></td>
		<td><%=rs.getString("hiredate")%></td>
		<td><%=rs.getInt("deptno")%></td>
	  </tr>
<%}%>
</table>

</body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close();
%>
