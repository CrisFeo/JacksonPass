import java.io.File;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.StoreConfig;


public class UserPersister {
	  /** The EntityStore for our Contact database. */
	  private static EntityStore store;
	  
	  /** The PrimaryIndex accessor for users. */
	  private static PrimaryIndex<String, User> userIndex; 
	  
	  /** Initialize the static variables at class load time to ensure there's only one of them. */
	  static {
	    // Create the directory in which this store will live.
	    String currDir = System.getProperty("user.dir");
	    File dir = new File(currDir, "userDB");
	    boolean success = dir.mkdirs();
	    if (success) {
	      System.out.println("Created the userDB directory.");
	    }
	    
	    EnvironmentConfig envConfig = new EnvironmentConfig();
	    StoreConfig storeConfig = new StoreConfig();
	    envConfig.setAllowCreate(true);
	    storeConfig.setAllowCreate(true);
	    Environment env = new Environment(dir,  envConfig);
	    UserPersister.store = new EntityStore(env, "EntityStore", storeConfig);
	    userIndex = store.getPrimaryIndex(String.class, User.class); 
	    // Guarantee that the environment is closed upon system exit.
	    DbShutdownHook shutdownHook = new DbShutdownHook(env, store);
	    Runtime.getRuntime().addShutdownHook(shutdownHook);
	  }
	  
	  /**
	   * Store the passed User in the database.
	   * @param user The User to store. 
	   */
	  public static void putUser(User user) {
	    userIndex.put(user);
	  }
	  
	  /**
	   * Retrieve a User from the database given its key.
	   * @param userID The primary key for the Contact.
	   * @return The User instance. 
	   */
	  public static User getUser(String userID) {
	    return userIndex.get(userID);
	  }
	  
	  /**
	   * Removes the User instance with the specified userID.
	   * @param userID The userID for the instance to be deleted.
	   */
	  public static void deleteUser(String userID) {
	    userIndex.delete(userID);
	  }
}
