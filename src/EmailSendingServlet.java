
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 * 
 * @author www.codejava.net
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// reads form fields
		String email1 = "guskasreesailam@gmail.com";
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String phone = request.getParameter("phone");
		String textarea = request.getParameter("textarea");
		String subject1 = "Thank You  ";

		String contentwq = "Name :  " + name + "\n" + "Email : " + email + "\n" + " Subject : " + subject + "\n"
				+ "Phone:" + phone + "\n" + "Text:" + textarea;

		String content1 = "Will Contact You Within 24 Hours";

		String resultMessage = "";

		try {
			EmailUtility.sendEmail(host, port, user, pass, email1, subject, contentwq);
			EmailUtility.sendEmail(host, port, user, pass, email, subject1, content1);

			/* resultMessage = "The e-mail was sent successfully"; */

		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			request.setAttribute("Message", resultMessage);
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
	}
}