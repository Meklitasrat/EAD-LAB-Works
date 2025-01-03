//Meklit Asrat Tefera


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import com.todoapp.utils.DBConnectionManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final String DELETE_QUERY = "DELETE FROM bookstoredb WHERE id = ?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get the task ID from the request
        int bookId = Integer.parseInt(req.getParameter("bookId"));

//        DBConnectionManager dbManager = new DBConnectionManager();
//        Connection connection = null;
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        Connection conn;

        try {
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "Pass10a100%");
            PreparedStatement ps = conn.prepareStatement(DELETE_QUERY);

            // Set query parameter
            ps.setInt(1, bookId);

            // Execute query
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                out.println("<h3>Book deleted successfully!</h3>");
            } else {
                out.println("<h3>Error: Book not found or could not be deleted.</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>Database error: " + e.getMessage() + "</h3>");
        } 
    }
}