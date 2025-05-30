import java.sql.*;
import javax.servlet.http.*;

public class VulnerableExample extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "user", "pass");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                response.getWriter().println("Welcome " + rs.getString("name"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String comment = request.getParameter("comment");

        try {
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("Your comment: " + comment);
            response.getWriter().println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
