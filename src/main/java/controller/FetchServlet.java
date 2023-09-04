package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FetchServlet extends HttpServlet {

	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		String eid = null, ename = null , esalary = null, edept = null;
		
		PreparedStatement ps=null; 
		ResultSet rs=null;
		Connection conn=null;
		String SELECT_EMP_BY_ID=null;
		boolean flag=false;
		
		//see data
		
		if(response!=null) {	
		response.setContentType("text/html");
		
		//make printWriter method
		pw=response.getWriter();
		}
		
		if(request!=null) {
			eid=request.getParameter("id");
			
		}
		
		if(pw!=null) {
			
		}
		
		pw.println("<a href='FormPage.html'>Back to Home</a>");
		//-------------------------------------------
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
			
			SELECT_EMP_BY_ID="select * from employee where id=?";
			
			ps=conn.prepareStatement(SELECT_EMP_BY_ID);
			
			ps.setInt(1,Integer.parseInt(eid));
			
			rs=ps.executeQuery();
			
			
			
			if(rs.next()) {
				flag=true;
				pw.println("<h1><center> Your Id Nu - "+rs.getString(1)+"</center></h1>");
				pw.println("<h1><center> Your Name - "+rs.getString(2)+"</center></h1>");
				pw.println("<h1><center> Your Sallary - "+rs.getString(3)+"</center></h1>");
				pw.println("<h1><center> Your Post - "+rs.getString(4)+"</center></h1>");
			}
			
			if(flag==false) {
				pw.println("<h2 style='color:green'> Record Not Found for "+eid+"</h2>");
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pw.close();
			
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
	
	
	
	
	
	
}
