import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jdi.request.InvalidRequestStateException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/login")
public class Login extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		
		PrintWriter out = res.getWriter();
		try{
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String url = "jdbc:mysql://localhost:3306/engida";
			String uname = "root";
			String pass = "";
			String query = "SELECT * FROM engida.users where email=? and password=?";
			Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection(url,uname,pass);
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, email);
	        ps.setString(2, password);
		    ResultSet rs = ps.executeQuery();
			if(rs.next()){
				
				res.sendRedirect("allList.jsp");
			}else{
				res.sendRedirect("login.html");
			}
			
			ps.close();
			con.close();
			
	}catch(Exception e){
		out.println("error  : "+ e.getMessage());
	}
}
}
