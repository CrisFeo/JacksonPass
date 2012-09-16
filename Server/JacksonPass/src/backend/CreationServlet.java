package backend;


import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import blocks.IBlock;
import blocks.PasswordMatcher;

/**
 * Servlet implementation class CreationServlet
 */
public class CreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CreationServlet() {
        super();
    }
    
    //Just returns the simple interface for testing purposes
    //TODO - make this return all of the available categories and blocks
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		returnLandingPage(response);
		returnBlockClassInfoTest(response);
	}
	
	private void returnBlockClassInfoTest(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/xml");
		StringBuffer responseBody = new StringBuffer();
		responseBody.append(PasswordMatcher.getAvailableBlocksXML());
		response.getWriter().write(responseBody.toString());
	}
	
	//HTML generation for simple interface
	private void returnLandingPage(HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		StringBuffer responseBody = new StringBuffer();
		responseBody.append("<html>");
		//The form to create a new account
			responseBody.append("<form action=\"Create\" method=\"post\">");
				responseBody.append("Create a new account <br />");
				responseBody.append("Username: <input type=\"text\" name=\"newUID\" /><br />");
				responseBody.append("Password: <input type=\"text\" name=\"newPassword\" /><br />");
				responseBody.append("<input type=\"submit\" value=\"Submit\" /><br /><br />");
			responseBody.append("</form>");
		responseBody.append("</html>");
		response.getWriter().write(responseBody.toString());
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
			//TODO - maybe move this logic somewhere else
			List<IBlock> generatedBlocks = PasswordMatcher.generatePassword(newPassword);
			User newUser = new User(newUserID, generatedBlocks);
			//TODO - possibly check for the success of the PUT into the DB. Maybe return a different code depending on what needs to be done
			@SuppressWarnings("unused")
			boolean success = UserPersister.putUser(newUser);
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
