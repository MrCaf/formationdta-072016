package fr.pizzeria.admin.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginControlleur extends HttpServlet {

	/**
	 * GET : affiche un formulaire pour l'authentification
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("url", req.getParameter("url"));
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/loginJSTL.jsp?url=" + req.getParameter("url"));
		dispatcher.forward(req, resp);
		
	}

	/**
	 * POST : enregistre la connexion
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String user = req.getParameter("user");
		String password = req.getParameter("password");
		
		if(user.equals("admin@pizzeria.fr") && password.equals("admin")){
			req.getSession().setAttribute("userOk", true);
			resp.sendRedirect(req.getContextPath() + req.getParameter("url"));
		}
		else{
			req.getSession().setAttribute("userOk", null);
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		
	}

}
