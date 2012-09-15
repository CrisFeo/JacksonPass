
import java.util.LinkedList;
import java.util.List;
import blocktypes.IBlock;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class User {
	
	//The User's unique ID.
	@PrimaryKey
	private String userID;
	//The User's password
	private List<IBlock> password;
	
	//BerkeleyDB requires a default constructor
	public User() {}
	
	//Constructor that we will actually use
	public User(String userID, String password) 
	{
		this.userID = userID;
		this.password = generateBlocks(password);
	}
	
	//TODO - fill this method in once the string -> block mappings are defined
	private List<IBlock> generateBlocks(String s)
	{
		return new LinkedList<IBlock>();
	}
	
	//Getters and Setters
	public String getUserID() {return this.userID;}
	
	public String getPassword() 
	{
		String ret_string = "";
		for (IBlock b : this.password)
		{
			ret_string += b.getRepresentation();
		}
		return ret_string;
	}
	
}

