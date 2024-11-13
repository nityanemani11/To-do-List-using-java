import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.sun.jdi.request.InvalidRequestStateException;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")

public class Register extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
try{
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			String url = "jdbc:mysql://localhost:3306/engida";
	        String uname = "root";
	        String pass = "";
	        String query = "insert into users values(?,?,?)";
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        Connection con = DriverManager.getConnection(url, uname, pass);
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, password);
	        
	        int count = ps.executeUpdate();
	        HttpSession session = req.getSession();
	        session.setAttribute("name", name);
			session.setAttribute("username", email);
			
			String data = (String)session.getAttribute("username");
			
	        if(count != 0){
	        	if(data.equals(email)) {
	        		res.sendRedirect("allList.jsp");
	        	}else{
	        		out.println("not working");
	        	}
	        }else{
	        	res.sendRedirect("register.html");
	        }
	        
		    ps.close();
			con.close();
		
			
					

}catch(Exception e){
			out.println("error " + e.getMessage());
		}
   }
}
