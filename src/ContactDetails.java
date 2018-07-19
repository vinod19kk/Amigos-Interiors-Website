
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ContactDetails
 */
@WebServlet("/ContactDetails")
public class ContactDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		long phone = Long.parseLong(request.getParameter("phone"));
		String textarea = request.getParameter("textarea");

		Connection con = Dbconnection.getMySqlConnection();
		String sql = "insert into interior.exterior(Name,Email,Subject,Phone,Textarea) values(?,?,?,?,?)";

		String id = "SELECT LAST_INSERT_ID()";

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, subject);

			ps.setLong(4, phone);
			ps.setString(5, textarea);

			ps.execute();

			PreparedStatement ps1 = con.prepareStatement(id);
			ResultSet rs = ps1.executeQuery();
			rs.next();
			HttpSession hs = request.getSession();
			hs.setAttribute("id", rs.getString(1));

			response.sendRedirect("contact.html");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
