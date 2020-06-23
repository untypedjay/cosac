package swe4.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CosaCServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<h1> Hello from the Servlet </h1>");
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public void destroy() {
    // do nothing
  }

  @Override
  public void init() throws ServletException {
    super.init();
  }
}
