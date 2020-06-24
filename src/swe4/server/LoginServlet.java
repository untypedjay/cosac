package swe4.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try {
      out.println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"/><title>Login | CosaC</title><link" +
        "rel=\"stylesheet\" href=\"css/style.css\"/></head><body><header><h1>CosaC Login</h1></header><main><form" +
        " action=\"/order\" method=\"get\" accept-charset=\"UTF-8\"><label for=\"username\">Username:</label><input" +
        " type=\"text\" name=\"username\"><label for=\"password\">Password:</label><input type=\"password\" name=\"password" +
        "\"><input type=\"submit\" value=\"Login\"></form></main></body></html>");
    } finally {
      if (out != null) {
        out.println("</main></body></html>");
      }
    }
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
