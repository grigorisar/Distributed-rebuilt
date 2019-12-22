package gr.hua.dit.ds.team52.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servlet implementation class StudentListFetch
 */
//@WebServlet("/StudentListFetch")
public class StudentListFetch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
//    private UserDaoImpl helper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentListFetch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Student> list = helper.getStudentList();
//		request.setAttribute("list", list);
		request.getRequestDispatcher("staffform.jsp").forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
