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

@WebServlet("/editSwagger")
public class EditSwaggerServlet extends HttpServlet {

	private static final long serialVersionUID = -6554920927964049383L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for edit operation");
		}
		System.out.println("Swagger edit requested with id=" + id);
		MongoClient mongo = (MongoClient) request.getServletContext()
				.getAttribute("MONGO_CLIENT");
		SwaggerDAO swaggerDAO = new SwaggerDAO(mongo);
		ImportSwagger s = new ImportSwagger();
		s.setId(id);
		s=swaggerDAO.readSwagger(s);
		request.setAttribute("swagger", s);
		List<ImportSwagger> swaggers = swaggerDAO.readAllSwaggers();
		request.setAttribute("swaggers", swaggers);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/JSP/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id"); // keep it non-editable in UI
		if (id == null || "".equals(id)) {
			throw new ServletException("id missing for edit operation");
		}

		String swaggername = request.getParameter("swaggername");
	    //Import Swagger
		String swaggerurl = request.getParameter("swaggerurl");
		// read a swagger description from the url
		  Swagger swaggerJSON = new SwaggerParser().read(swaggerurl);
		  //String jsonString = swagger.toString();
		  String swagger = Json.pretty(swaggerJSON);


		if ((swaggername == null || swaggername.equals(""))
				|| (swagger == null || swagger.equals(""))) {
			request.setAttribute("error", "Swagger Name and Swagger ImportURL Can't be empty");
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			ImportSwagger i = new ImportSwagger();
			i.setSwaggerName(swaggername);
			i.setSwagger(swagger);
			SwaggerDAO swaggerDAO = new SwaggerDAO(mongo);
			swaggerDAO.createSwagger(i);
			i.setId(id);
			i.setSwaggerName(swaggername);
			i.setSwagger(swagger);
			request.setAttribute("swagger", i);
			List<ImportSwagger> swaggers = swaggerDAO.readAllSwaggers();
			request.setAttribute("swaggers", swaggers);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/JSP/index.jsp");
			rd.forward(request, response);
		} else {
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			ImportSwagger i = new ImportSwagger();
			i.setSwaggerName(swaggername);
			i.setSwagger(swagger);
			SwaggerDAO swaggerDAO = new SwaggerDAO(mongo);
			swaggerDAO.createSwagger(i);
			i.setId(id);
			i.setSwaggerName(swaggername);
			i.setSwagger(swagger);
			swaggerDAO.updateSwagger(i);
			System.out.println("Swagger edited successfully with id=" + id);
			request.setAttribute("success", "Swagger edited successfully");
			List<ImportSwagger> swaggers = swaggerDAO.readAllSwaggers();
			request.setAttribute("swaggers", swaggers);

			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/JSP/index.jsp");
			rd.forward(request, response);
		}
	}

}
