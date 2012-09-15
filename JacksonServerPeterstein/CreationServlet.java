

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreationServlet
 */
public class CreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CreationServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO ???
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get all parameters
		String newUserID = request.getParameter("newUID");
		String newPassword = request.getParameter("newPassword");
		//check if the username already exists
		User check = UserPersister.getUser(newUserID);
		if (check!=null)
		{
			usernameTaken(response);
		}
		else
		{
			//TODO - check to make sure that block string is well formed, maybe...
			User newUser = new User(newUserID, newPassword);
			UserPersister.putUser(newUser);
			userAdded(response);
		}
	}
	
	//Returns a message indicating the supplied username does not exist
	private void usernameTaken(HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		StringBuffer responseBody = new StringBuffer();
		//TODO - return an actual response, talk to feo
		responseBody.append("USERNAME TAKEN!!!!!");
		response.getWriter().write(responseBody.toString());
	}
	
	//Returns a page indicating the supplied username does not exist
	private void userAdded(HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		StringBuffer responseBody = new StringBuffer();
		//TODO - return an actual response, talk to feo
		responseBody.append("USER ADDED!!!");
		response.getWriter().write(responseBody.toString());
	}
	
	
}
