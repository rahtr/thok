/**
 *
 */
package com.thok.servlets;

/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
import java.io.IOException;
import java.util.List;

import com.thok.dao.SwaggerDAO;
import com.thok.model.ImportSwagger;

@WebServlet("/deleteSwagger")
public class DeleteSwaggerServlet extends HttpServlet {

	private static final long serialVersionUID = 6798036766148281767L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for delete operation");
		}
		MongoClient mongo = (MongoClient) request.getServletContext()
				.getAttribute("MONGO_CLIENT");
		SwaggerDAO swaggerDAO = new SwaggerDAO(mongo);
		ImportSwagger i = new ImportSwagger();
		i.setId(id);
		swaggerDAO.deleteSwagger(i);
		System.out.println("Swagger deleted successfully with id=" + id);
		request.setAttribute("success", "Swagger deleted successfully");
		List<ImportSwagger> swaggers = swaggerDAO.readAllSwaggers();
		request.setAttribute("swaggers", swaggers);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/JSP/index.jsp");
		rd.forward(request, response);
	}

}
