

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerificationServlet
 */
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public VerificationServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//Get all parameters
    	String userID = request.getParameter("userID");
    	String password = request.getParameter("password");
    	boolean matched = evaluatePassword(userID, password);
    	if (matched)
    	{
    		validationSucceeded(response);
    	}
    	else
    	{
    		validationFailed(response);
    	}
	}
	
	private boolean evaluatePassword(String userID, String password) {
		User currentUser = UserPersister.getUser(userID);
		String correctPW = currentUser.getPassword();
		return correctPW.equals(password);
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
