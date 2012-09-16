package backend;

import java.util.List;
import blocks.IBlock;
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
	public User(String userID, List<IBlock> password) 
	{
		this.userID = userID;
		this.password = password;
	}
	
	//Getters and Setters
	public String getUserID() {return this.userID;}
	public List<IBlock> getPassword() {return password;}
	
}

