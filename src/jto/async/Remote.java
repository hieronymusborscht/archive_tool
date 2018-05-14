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
		boolean is_okay = false;
		 int last_index = 0;
		String uri= null;
		String d = null;
		String a = null;
		String sf= null;
		 jto.ent.ArticleSaver article_saver = (jto.ent.ArticleSaver)request.getSession().getAttribute("article_saver");
         if(article_saver==null){
                 article_saver = new jto.ent.ArticleSaver();  
         }

		
		
		if( (request.getParameter("uri_t")!=null) && (request.getParameter("uri_t").length()>0 )){
			
			
			is_okay = article_saver.checkIfSavedAlready(request.getParameter("uri_t"));
			
		}else {
			if( (request.getParameter("uri")!=null) && (request.getParameter("uri").length()>0 )){
				 uri =request.getParameter("uri");
				 
			}
			if( (request.getParameter("d")!=null) && (request.getParameter("d").length()>0 )){
				d = request.getParameter("d");
			}
			if( (request.getParameter("a")!=null) && (request.getParameter("a").length()>0 )){
				a= request.getParameter("a");
			}
			if( (request.getParameter("sf")!=null) && (request.getParameter("sf").length()>0 )){
				sf =request.getParameter("sf");
			}
			if(uri!=null && uri.length()>0 && d!=null && d.length()>0 && a!=null && a.length()>0 && sf!=null && sf.length()>0) {
				last_index=article_saver.saveArticle(uri, a, d, sf);
				//System.out.println(last_index);
				if(last_index>0) {
					is_okay =true;
				}
			}
		}
	
       request.getSession().setAttribute("article_saver", article_saver);	
		
		if(is_okay) {
			response.getWriter().append(""+last_index);
		}else {
			response.getWriter().append("NO");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
