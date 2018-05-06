package jto.async;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Remote
 */
@WebServlet("/Remote")
public class Remote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//if( (request.getParameter("p")!=null) && (request.getParameter("p").length()>0 )){
		//	System.out.println("p:"+request.getParameter("p"));
		//}
		if( (request.getParameter("uri")!=null) && (request.getParameter("uri").length()>0 )){
		
			request.getParameter("uri");
		}
		if( (request.getParameter("d")!=null) && (request.getParameter("d").length()>0 )){
			request.getParameter("d");
		}
		if( (request.getParameter("a")!=null) && (request.getParameter("a").length()>0 )){
			request.getParameter("a");
		}
		if( (request.getParameter("sf")!=null) && (request.getParameter("f").length()>0 )){
			request.getParameter("sf");
		}
				
		
		
		
		response.getWriter().append("OK");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
