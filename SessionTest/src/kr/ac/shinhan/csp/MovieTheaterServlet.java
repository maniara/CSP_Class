package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MovieTheaterServlet  extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(false);
		
		if(session == null)
			resp.getWriter().println("No Session Available"); 
		
		else
		{
			resp.getWriter().println("<html>");
			resp.getWriter().println("<body>");
			resp.getWriter().println("title : " + session.getAttribute("title"));
			resp.getWriter().println("theater : " + req.getParameter("theater"));
			resp.getWriter().println("</body>");
			resp.getWriter().println("</html>");
		}
	}

}
