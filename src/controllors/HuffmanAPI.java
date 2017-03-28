package controllors;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import utils.Serializer;

public class HuffmanAPI  {

	private Serializer serializer;
	
	public HashMap<String, StringBuffer> code = new HashMap<String, StringBuffer>();
	
	public StringBuilder code2 = new StringBuilder();
    
   

    public  HuffmanAPI(Serializer serializer)throws Exception {

		this.serializer = serializer; 

	}

	/**
	 * @param an empty Constructor 
	 */
	public HuffmanAPI() {

	}


	/**
	 * @param load from xml file 
	 */
	@SuppressWarnings("unchecked")
	public void load() throws Exception
	{
		serializer.read();
		
		code2 = (StringBuilder) serializer.pop();
		

	}

	/**
	 * @param save to xml file

	 */


	public void store() throws Exception
	{

		serializer.push(code2);
		
		serializer.write(); 


	}

	
	
	/**
	 *@param loadDefaultfiles external files with dictionary data
	 *@return populated   hashtree of dictionary 
	 */

	public  void loadDefaultFiles() throws FileNotFoundException{
		JFileChooser fileChooser = new JFileChooser();
		
		//only accept txt files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT/XML FILES", "txt", "xml");
		fileChooser.setFileFilter(filter);
		 fileChooser.setVisible(true);
		 if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			   
			   //get the file
		java.io.File file = fileChooser.getSelectedFile();
		File size =fileChooser.getSelectedFile();
	
	
		long bytes = size.length();
		//long free = size.getFreeSpace();
		//long total = size.getTotalSpace();
	  System.out.println("Size of File in bytes   " + bytes);
			   
		Scanner inUsers = new Scanner(file);
	
		while (inUsers.hasNextLine()) {
			String userDetails = inUsers.nextLine();
			@SuppressWarnings("unused")
			String hexCode= "0CADD099";
			Item items = new Item(hexCode + userDetails);
			
	       
		}
		inUsers.close();


	}

	}
	}

