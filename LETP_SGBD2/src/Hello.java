import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet {
public void doGet(
HttpServletRequest request,
HttpServletResponse response)
throws IOException, ServletException {
PrintWriter out = response.getWriter();
out.println("<html>");
out.println("<head>");
out.println("<title>");
out.println("Test de servlet");
out.println("</title>");
out.println("</head>");
out.println("<body>");
out.println("<h1>Hello World!!!</h1>");
out.println("<p>This is a test servlet</p>");
out.println("</body>");
out.println("</html>");
}
}