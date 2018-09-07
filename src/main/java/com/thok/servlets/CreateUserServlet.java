package com.thok.servlets;
/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
import java.io.IOException;
import java.util.List;

import com.thok.dao.UsersDAO;
import com.thok.model.Users;

@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = -7060758261496829905L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ldap = request.getParameter("ldap");
		String password = request.getParameter("password");
		if ((ldap == null || ldap.equals(""))
				|| (password == null || password.equals(""))) {
			request.setAttribute("error", "Mandatory Parameters Missing");
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/users.jsp");
			rd.forward(request, response);
		} else {
			Users u = new Users();
			u.setPassword(password);
			u.setLDAP(ldap);
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			UsersDAO usersDAO = new UsersDAO(mongo);
			usersDAO.createUser(u);
			System.out.println("User Added Successfully with id="+u.getId());
			request.setAttribute("success", "User Added Successfully");
			List<Users> users = usersDAO.readAllUsers();
			request.setAttribute("users", users);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/users.jsp");
			rd.forward(request, response);
		}
	}
}
