//Meklit Asrat Tefera

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.todoapp.utils.DBConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/displayBooks")
public class DisplayBookServlet extends HttpServlet {
	

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

//        DBConnectionManager dbManager = new DBConnectionManager();
//        Connection connection = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Connection connection;

        try {
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "Pass10a100%");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookstoredb");

            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Description</th><th>Status</th><th>Due Date</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("author") + "</td>");
                out.println("<td>" + rs.getInt("price") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.println("<h1>" + e.getMessage() + "</h1>");
          }}}
          
         
