package gr.hua.dit.ds.team52.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "gr.hua.distributed.team52.resources.CreateUser",  urlPatterns = "/user/CreateUser")
public class CreateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String c = request.getParameter("user_details");

        System.out.println(c);


        //request.setAttribute("list", list);

//        HttpSession session = request.getSession();

//        session.setAttribute("list", list);

        response.sendRedirect("/user");

        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");

        //requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
