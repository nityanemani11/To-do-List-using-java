import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jdi.request.InvalidRequestStateException;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/add")
public class AddItem extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		
		PrintWriter out = res.getWriter();
		try{
			String item = req.getParameter("item");
	
			String url = "jdbc:mysql://localhost:3306/engida";
			String uname = "root";
			String pass = "";
			String query = "insert into items values(?)";
			Class.forName("com.mysql.jdbc.Driver");
			
		    Connection con = DriverManager.getConnection(url,uname,pass);
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, item);
		    int count = ps.executeUpdate();
		    
	        if(count != 0){
	        	RequestDispatcher requestDispatcher = req.getRequestDispatcher("/allList.jsp");
	            requestDispatcher.forward(req, res);
			}else{
				out.println("Try again");
			}
			
			ps.close();
			con.close();
			
	}catch(Exception e){
		out.println("error  : "+ e);
	}
}
}
