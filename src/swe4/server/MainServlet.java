package swe4.server;

import swe4.model.entities.Dish;
import swe4.model.entities.Order;
import swe4.model.entities.TimeSlot;
import swe4.model.entities.User;
import swe4.server.dal.*;
import swe4.util.PasswordUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class MainServlet extends HttpServlet {
  private DishDao dishDao = null;
  private OrderDao orderDao = null;
  private TimeSlotDao timeSlotDao = null;
  private UserDao userDao = null;
  private User currentUser = null;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String meal = request.getParameter("meal");
    String startTime = request.getParameter("starttime");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    try {
      if (meal != null && startTime != null) {
        orderDao.store(new Order(currentUser, dishDao.get(request.getParameter("meal")), timeSlotDao.get(request.getParameter("starttime"))));
      }

      User user = userDao.get(request.getParameter("username"));
      if (user != null && PasswordUtil.isValid(request.getParameter("password"), user.getPasswordHash())) {
        currentUser = user;
      }
      out.println("<!DOCTYPE html><html><head><meta charset=\"utf-8\"/><title>CosaC</title><link rel=\"stylesheet\" href=\"css/style.css\"/></head><body><header><h1>");
      out.println("Corona Safe Canteen");
      out.println("</h1>");
      out.println("<form action=\"/login\" method=\"get\"><input type=\"submit\"");
      if (currentUser != null) {
        out.println("value=\"Logout\">");
      } else {
        out.println("value=\"Login\">");
      }
      out.println("</form></header><main><section><table><thead><tr>");
      out.println("<th>Name</th><th>Section</th><th>Price</th>");
      out.println("</tr></thead><tbody>");
      for (Dish dish : dishDao.getAll()) {
        out.println("<tr>");
        out.println(String.format("<td>%d</td>", dish.getName()));
        out.println(String.format("<td>%d</td>", dish.getSection()));
        out.println(String.format("<td>%d</td>", dish.getPrice()));
        out.println("</tr>");
      }
      out.println("</tbody></table></section><section><table><thead><tr>");
      out.println("<th>Start</th><th>End</th><th>Available Orders</th>");
      out.println("</tr></thead><tbody>");
      for (TimeSlot timeSlot : timeSlotDao.getAll()) {
        out.println("<tr>");
        out.println(String.format("<td>%d</td>", timeSlot.getStartTime()));
        out.println(String.format("<td>%d</td>", timeSlot.getEndTime()));
        out.println(String.format("<td>%d</td>", timeSlot.getMaximumCustomers()));
        out.println("</tr>");
      }
      if (currentUser != null) {
        out.println("</tbody></table></section><form action=\"/order\" method=\"get\"><label for=\"meal\">Choose meal:");
        out.println("</label><input type\"text\" name=\"meal\"><label for\"starttime\">Choose start time:</label>");
        out.println("<input type=\"text\" name=\"starttime\"><input type=\"submit\" value=\"Send order!\"></form>");
      }
    } catch (SQLException e) {
      out.printf("<p> style=\"color=red;\">EXCEPTION raised: '%s'</p>%n", e);
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
    dishDao = new DishDaoJdbc();
    orderDao = new OrderDaoJdbc();
    timeSlotDao = new TimeSlotDaoJdbc();
    userDao = new UserDaoJdbc();
  }
}
