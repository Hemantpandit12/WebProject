package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {
	
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=null;
		
		response.setContentType("text/html");
		//make printWriter method
		pw=response.getWriter();
		
		
		
		
		String eid = null;
		String ename=null;
		String esalary = null;
		String edept=null;
		
		
		
		int a=0 ;
		
		Connection conn=null;
		String sql=null;
		PreparedStatement ps=null;
		int result=0;
			
		//see data
		
					
				
				
			
					eid=request.getParameter("id");
					ename=request.getParameter("name");
					esalary=request.getParameter("salary");
					edept=request.getParameter("dept");
					a=Integer.parseInt(eid);
				long	b=Long.parseLong(esalary);
		
				
				
					pw.println("<h1><center> Enter Email - "+eid+"</center></h1>");
					pw.println("<h1><center> This is your Name - "+ename+"</center></h1>");
					pw.println("<h1><center> This is your Salary - "+esalary+"</center></h1>");
					pw.println("<h1><center> This is your Department - "+edept+"</center></h1>");
			
		
		
		//Connect to database by jdbc technology
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					//load driver
					
					conn=DriverManager.getConnection("jdbc:oracle:thin:@localHost:1521:xe","Prashant","Prashant");
					
					sql="insert into employee values(?,?,?,?)";
					
					ps=conn.prepareStatement(sql);
					
					
					ps.setInt(1, a);
					ps.setString(2, ename);
					ps.setLong(3, b);
					ps.setString(4, edept);
					
					result=ps.executeUpdate();
					
					if(result==0) {
						pw.println("<h2 style='color:red'>Record Not Inserted</h2>");
						pw.println("<a href='FormPage.html'>Back to Home</a>");
						
					}
					else {
						pw.println("<h2 style='color:green'>Record Inserted Successfully for Employee"+eid +"</h2>");
						pw.println("<a href='FormPage.html'>Back to Home</a>");
						
					}
	
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		
		
				
				finally {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pw.close();
					
					
				}
		
		
	
	}

	/////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
