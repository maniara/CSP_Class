package kr.ac.shinhan.csp;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MovieTitleServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String title = req.getParameter("title");
		
		//技记俊 康拳力格阑 历厘
		HttpSession session = req.getSession();
		session.setAttribute("title", title);
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<form method='post' action='theater'>");
		resp.getWriter().println("Movie Theater :");
		resp.getWriter().println("<input type='text' name='theater'>");
		resp.getWriter().println("<input type='submit'>");
		resp.getWriter().println("</form>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</html>");
	
	}
}
