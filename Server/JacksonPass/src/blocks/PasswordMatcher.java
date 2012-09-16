package blocks;

import backend.User;
import backend.UserPersister;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.reflections.Reflections;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PasswordMatcher {
	private static HashMap<String, Class> patternmapping;
//	private static HashSet<Class> 

	public static boolean match(String userName, String password, HttpServletRequest request)
	{
		//Look up the user in the DB
		User currentUser = UserPersister.getUser(userName);
		if (currentUser == null)
			return false;
		List<IBlock> password_blocks = currentUser.getPassword();
		//Get the string representation of the PW, concatenate all of the individual block passwords
		String correctPW = "";
		try
		{
			//There could be errors thrown during lookup (Calls to API, computation, etc...)
			for (IBlock block : password_blocks)
			{
				correctPW += block.getRepresentation(request);
			}
		} catch (Exception e)
		{
			//TODO - return more information than just false, need to differentiate between incorrect password and 
			return false;
		}
		return correctPW.equals(password);
	}
	
	
	//Make sure the hashmap is populated
	static 
	{
		//Ideally, this will only actually occurr once
		System.out.println("Populating Map of Patterns to Block Class-Types");
		patternmapping = new HashMap<String, Class>();
		Reflections reflections = new Reflections("blocks");
		Set<Class<? extends IBlock>> classes = reflections.getSubTypesOf(IBlock.class);
		for (Class<? extends IBlock> c : classes)
		{
			Object patternString = null;
			try {
//				System.out.println(c.getCanonicalName());
				Method getRepresentation = c.getMethod("getPattern", new Class[] {});
//				System.out.println(getRepresentation.getName());
//				Object block = c.newInstance();
				patternString = getRepresentation.invoke(c.newInstance(), new Object[] {});
//				System.out.println(patternString);
				patternmapping.put((String) patternString, c);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("Number of classes successfully reflected: "+patternmapping.size());
	}
	
	public static List<IBlock> generatePassword(String rawPattern)
	{
		//TODO - decide on DELIMITER
		//Split the raw pattern into "blocks"
		String[] splits = rawPattern.split("%");

		//Instantiate a block class for each raw string
		LinkedList<IBlock> generated_password = new LinkedList<IBlock>();
		for (int i=0; i < splits.length; i++)
		{
			String current_raw_string = splits[i];
			//Probably want to ignore empty splits... TODO check this out once delimiter is decided
			if (current_raw_string.isEmpty())
			{
				continue;
			}
			IBlock current_generated_block = generateBlock(current_raw_string);
			//Do a null-check
			if (current_generated_block == null)
			{
				continue;
			}
			generated_password.addLast(current_generated_block);
		}
		return generated_password;
	}
	
	private static IBlock generateBlock(String pattern)
	{
		//Lookup the class for the pattern in the patternmapping
//		System.out.println("Pattern: "+pattern);
		Class c = patternmapping.get(pattern);
		try {
			return (IBlock) c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getAvailableBlocks() 
	{
		StringBuffer return_string = new StringBuffer();
		for (String pattern : patternmapping.keySet())
		{
			Class block_class = patternmapping.get(pattern);
			return_string.append("Block class name: "+block_class.getCanonicalName()+", Block class pattern: "+pattern+"<br/>");
		}
		return return_string.toString();
	}
	
	public static String getAvailableBlocksXML()
	{
		/*
		try
		{
		  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		  //root elements
		  Document doc = docBuilder.newDocument();

		  Element rootElement = doc.createElement("categories");
		  doc.appendChild(rootElement);
		  
		  //staff elements
		  Element staff = doc.createElement("Staff");
		  rootElement.appendChild(staff);

		  //set attribute to staff element
		  Attr attr = doc.createAttribute("id");
		  attr.setValue("1");
		  staff.setAttributeNode(attr);

		  //shorten way
		  //staff.setAttribute("id", "1");

		  //firstname elements
		  Element firstname = doc.createElement("firstname");
		  firstname.appendChild(doc.createTextNode("yong"));
		  staff.appendChild(firstname);

		  //lastname elements
		  Element lastname = doc.createElement("lastname");
		  lastname.appendChild(doc.createTextNode("mook kim"));
		  staff.appendChild(lastname);

		  //nickname elements
		  Element nickname = doc.createElement("nickname");
		  nickname.appendChild(doc.createTextNode("mkyong"));
		  staff.appendChild(nickname);

		  //salary elements
		  Element salary = doc.createElement("salary");
		  salary.appendChild(doc.createTextNode("100000"));
		  staff.appendChild(salary);

		  //write the content into xml file
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transformer = transformerFactory.newTransformer();
		  DOMSource source = new DOMSource(doc);

		  StreamResult result =  new StreamResult(new File("C:\\testing.xml"));
		  transformer.transform(source, result);

		  System.out.println("Done");

		}catch(ParserConfigurationException pce){
		  pce.printStackTrace();
		}catch(TransformerException tfe){
		  tfe.printStackTrace();
		} */
		return "";
	}
	
}
