package backend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import blocks.PasswordMatcher;

/**
 * Servlet implementation class VerificationServlet
 */
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public VerificationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	returnLandingPage(response);
	}
    
	//Returns the landing/login page
	private void returnLandingPage(HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		StringBuffer responseBody = new StringBuffer();
		responseBody.append("<html>");
		//The form to login to an existing account
			responseBody.append("<form action=\"Verify\" method=\"post\">");
				responseBody.append("Log in to an existing account <br />");
				responseBody.append("Username: <input type=\"text\" name=\"existingUserID\" /><br />");
				responseBody.append("Password: <input type=\"text\" name=\"existingPassword\" /><br />");
				responseBody.append("<input type=\"submit\" value=\"Submit\" /><br /><br />");
			responseBody.append("</form>");
		responseBody.append("</html>");
		response.getWriter().write(responseBody.toString());
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Get all parameters
    	String userID = request.getParameter("existingUserID");
    	String password = request.getParameter("existingPassword");
    	//Note, password matcher class is responsible for evaluating all passwords
    	boolean matched = PasswordMatcher.match(userID, password, request);
    	if (matched)
    	{
    		validationSucceeded(response);
    	}
    	else
    	{
    		validationFailed(response);
    	}
	}
	
	//Returns a message indicating the supplied username does not exist
	private void validationSucceeded(HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		StringBuffer responseBody = new StringBuffer();
		//TODO - return an actual response, talk to feo
		responseBody.append("JACKSON!!!!!");
		response.getWriter().write(responseBody.toString());
	}
	
	//Returns a page indicating the supplied username does not exist
	private void validationFailed(HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		StringBuffer responseBody = new StringBuffer();
		//TODO - return an actual response, talk to feo
		responseBody.append("INVALID PASSWORD!!!");
		response.getWriter().write(responseBody.toString());
	}

}
