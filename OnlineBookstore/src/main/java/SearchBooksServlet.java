//Meklit Asrat Tefera
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import com.todoapp.utils.DBConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchBook")
public class SearchBooksServlet extends HttpServlet {
    private static final String SEARCH_QUERY = "SELECT * FROM bookstoredb WHERE keyword LIKE ?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get the search keyword
        String keyword = req.getParameter("keyword");

       
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Connection conn;

        try {
        	
        	
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "Pass10a100%");
            PreparedStatement ps = conn.prepareStatement(SEARCH_QUERY);

            // Set query parameter with wildcard for LIKE
            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Description</th><th>Status</th><th>Due Date</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("title") + "</td>");
                out.println("<td>" + rs.getString("auhtor") + "</td>");
                out.println("<td>" + rs.getInt("price") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } 
    }
}