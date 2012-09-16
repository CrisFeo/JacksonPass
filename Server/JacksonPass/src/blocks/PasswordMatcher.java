package blocks;

import backend.User;
import backend.UserPersister;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.reflections.Reflections;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PasswordMatcher {
	private static HashMap<String, Class> patternmapping;
	private static HashSet<Class> categories;
	private static String xml;
	
	
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
	
	
	//Make sure the hashmap and hashset are populated
	static 
	{
		//Ideally, these will only occur once
		//Populate the HashMap of pattern strings to IBlock Class objects
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
		
		//Populate the HashSet of Class objects that implement ICategory
		categories = new HashSet<Class>();
		Set<Class<? extends ICategory>> cats = reflections.getSubTypesOf(ICategory.class);
		for (Class<? extends ICategory> cat : cats)
		{
			try {
				if (!patternmapping.containsValue(cat))
					categories.add(cat);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		
		//Generate the XML needed by the client
		xml = generateAvailableBlocksXML();
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
	
	public static String getAvailableCategories()
	{
		StringBuffer return_string = new StringBuffer();
		for (Class c : categories)
		{
			return_string.append(c.getCanonicalName()+"\n");
		}
		return return_string.toString();
	}
	
	public static String getAvailableBlocksXML()
	{
		return xml;
	}
	
	public static String generateAvailableBlocksXML()
	{
		String return_string = "";
		
		try
		{
			//Create a set of Block objects to check against
			HashSet<IBlock> blocktypes = new HashSet<IBlock>();
			for (Class c : patternmapping.values())
			{
				IBlock bl = (IBlock) c.newInstance();
				blocktypes.add(bl);
			}
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			//create root element, <categories>
			Element rootElement = doc.createElement("categories");
			doc.appendChild(rootElement);

			//Create all Category Elements
			for (Class category_class : categories)
			{
				//Create the appropriate elements for each category
				Element category = doc.createElement("category");
				Element category_blocks = doc.createElement("blocks");
				//Instantiate the class
//				System.out.println("Currently XMLing: "+category_class.getCanonicalName());
				ICategory c = (ICategory) category_class.newInstance();
				
				//Create and attach the blocks element, give it an ID equal to it's name
				Element blocks = doc.createElement("blocks");
				blocks.setAttribute("category", c.getClass().getName());
				blocks.setIdAttribute("category", true);
				category.appendChild(blocks);
				
				//Crate and populate traits
				Element category_traits = doc.createElement("traits");
				Element trait_name = doc.createElement("trait");
				trait_name.setAttribute("name", "name");
				trait_name.setTextContent(c.getName());
				Element trait_cat_color = doc.createElement("trait");
				trait_cat_color.setAttribute("name", "cat_color");
				trait_cat_color.setTextContent(c.getColor());
				Element trait_block_color = doc.createElement("trait");
				trait_block_color.setAttribute("name", "block_color");
				trait_block_color.setTextContent(c.getBlockColor());
				//Append each trait to traits
				category_traits.appendChild(trait_name);
				category_traits.appendChild(trait_cat_color);
				category_traits.appendChild(trait_block_color);
				//Append traits to category
				category.appendChild(category_traits);
				
				//Append category to categories
				rootElement.appendChild(category);
			}

			//Create and populate an element for each class
			for (IBlock b : blocktypes)
			{
				//create the block element
				Element block = doc.createElement("block");
				Element block_traits = doc.createElement("traits");
				
				//Populate the traits
				Element trait_full_name = doc.createElement("trait");
				trait_full_name.setAttribute("name", "full_name");
				trait_full_name.setTextContent(b.getFullName());
				Element trait_short_name = doc.createElement("trait");
				trait_short_name.setAttribute("name", "short_name");
				trait_short_name.setTextContent(b.getShortName());
				Element trait_pattern = doc.createElement("trait");
				trait_pattern.setAttribute("name", "pattern");
				trait_pattern.setTextContent(b.getPattern());
				
				//Append each trait to traits
				block_traits.appendChild(trait_full_name);
				block_traits.appendChild(trait_short_name);
				block_traits.appendChild(trait_pattern);
				
				//Append traits to block
				block.appendChild(block_traits);
				
				//Attach the block to the appropriate category
				String parentClass = b.getClass().getSuperclass().getName();
//				System.out.println("SuperClass ID: "+parentClass);
				Element parent = doc.getElementById(parentClass);
				parent.appendChild(block);
			}

			//write the content into xml file
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			writer.flush();
			return_string = writer.toString();

		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(TransformerException tfe){
			tfe.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return return_string;
	}

}
