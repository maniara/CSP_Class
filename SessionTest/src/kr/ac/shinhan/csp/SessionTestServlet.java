package kr.ac.shinhan.csp;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SessionTestServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession();
		if(session.isNew())
			session.setMaxInactiveInterval(1000);
		resp.setContentType("text/plain");
		resp.getWriter().println("Session ID : "+session.getId());
	}
}
