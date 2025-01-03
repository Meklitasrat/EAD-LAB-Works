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

@WebServlet("/registerBook")
public class BookRegistrationServlet extends HttpServlet {
    private static final String INSERT_QUERY = "INSERT INTO bookstoredb (title, author, price) VALUES (?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Fetch input data from request
        String title = req.getParameter("title");
        String auhtor = req.getParameter("author");
        int price = Integer.parseInt(req.getParameter("price"));

       

        try {
        	
        	 Class.forName("com.mysql.cj.jdbc.Driver");
             
             Connection dbManager = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredb", "root", "Pass10a100%");
            PreparedStatement ps = dbManager.prepareStatement(INSERT_QUERY);

           
            // Set query parameters
            
            ps.setString(1, title);
            ps.setString(2, auhtor);
            ps.setInt(3, price);

            // Execute query
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<h3> Book added successfully!</h3>");
            } else {
                out.println("<h3>Error adding Book!</h3>");
            }
        }catch (SQLException se) {
            out.println("<h1>Error: " + se.getMessage() + "</h1>");
            se.printStackTrace();
      } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
    }
}