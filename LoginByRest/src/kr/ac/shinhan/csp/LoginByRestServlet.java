package kr.ac.shinhan.csp;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class LoginByRestServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
