package com.thok.servlets;
/**
 * @author Rahul Tripathi
 *
 * thok(Two Hundred OK)
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thok.dao.SwaggerDAO;
import com.thok.model.ImportSwagger;


@WebServlet("/addswagger")
public class AddSwaggerServlet extends HttpServlet {

	private static final long serialVersionUID = -7060758261496829905L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String swaggername = request.getParameter("swaggername");
	    //Import Swagger
		String swaggerurl = request.getParameter("swaggerurl");
		// read a swagger description from the url
		  Swagger swaggerJSON = new SwaggerParser().read(swaggerurl);
		  //String jsonString = swagger.toString();
		  String swagger = Json.pretty(swaggerJSON);
		  ArrayList list = new ArrayList();



		if ((swaggername == null || swaggername.equals(""))
				|| (swagger == null || swagger.equals(""))) {
			request.setAttribute("error", "Mandatory Parameters Missing");
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/JSP/index.jsp");
			rd.forward(request, response);
		} else {
			ImportSwagger i = new ImportSwagger();
			i.setSwaggerName(swaggername);
			i.setSwagger(swagger);

			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			SwaggerDAO swaggerd = new SwaggerDAO(mongo);
			swaggerd.createSwagger(i);
			System.out.println("Swagger Added Successfully with id="+i.getId());
			request.setAttribute("success", "Swagger Added Successfully");
			List<ImportSwagger> swaggers = swaggerd.readAllSwaggers();
			request.setAttribute("swaggers", swaggers);




			RequestDispatcher rd = getServletContext().getRequestDispatcher("/JSP/index.jsp");
			rd.forward(request, response);
		}
	}
}
