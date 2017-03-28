package controllors;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Scanner;


import edu.princeton.cs.introcs.Stopwatch;
import utils.Serializer;
import utils.XMLSerializer;



/**
 * Creates a new scanner for i/o
 * @param a new File called dictionary.xml
 * @returns an array and hashtree of data
 * @
 */
public class Main {


	private static Scanner input = new Scanner(System.in);


	public static void main(String[] args) throws Exception {



		File  code = new File("code1.xml");
		Serializer serializer = new XMLSerializer(code);
		HuffmanAPI h = new HuffmanAPI(serializer); 
		Huffman hm = new Huffman();




		/**
		 * Runs the program using i/o from user
		 * 
		 */

		System.out.println("\n\nHuffman Coding");
		System.out.println("-----------------");

		boolean goodInput = false;
		do{
			try{
				int option = mainMenu();
				while (option != 0)
				{
					goodInput = true;

					switch (option)
					{
					case 1:
						Stopwatch    stopwatch = new Stopwatch();
						h.loadDefaultFiles();

						System.out.println("File Imported \n\n");
						// print out results
						System.out.println("FREQ\tSYMBOL\tHUFFMAN CODE");


						CharacterCounter c = new CharacterCounter();
						c.increment(Item.item);
						hm.printMap(c);
						double    time = stopwatch.elapsedTime(); 
						System.out.println("elapsed    time " + time);

						h.code.put(Item.item, hm.test);



						System.out.println("hashmap code " + h.code);
						System.out.println("hashmap decode " + hm.decode);



						System.out.println("\n\nHuffman Encoding of file  :  \n" +  hm.test.toString());     

						//reading out file

						h.code2.append(hm.test);

						//convert full encoded string to byte array
						String code2Convert =h.code2.toString();
						System.out.println("code2Convert  : " + code2Convert);

						byte[] bytes = code2Convert.getBytes(StandardCharsets.UTF_8);
						StringBuffer binary = new StringBuffer() ;
						System.out.println("\n\nByte Array toString  " + Arrays.toString(bytes));
						for (byte b : bytes)
						{
							int val = b;
							for (int i = 0; i < 8; i++)
							{
								binary.append(((val & 128) == 0 ? 0 : 1));
								val <<= 1;
							}
							binary.append(' ');
						}
						System.out.println("\n\nByte Array Binary   " + binary);




				//		byte parseString =  hm.binaryStringToByte(code2Convert);
					//	 String parseS = String.format("%8s", Integer.toBinaryString(parseString & 0xFF)).replace(' ', '0');
					//	 System.out.println("parseString : " + parseS); 

						FileOutputStream stream = new FileOutputStream("../Huffman2/src/lib/bytes.dat");
						try {
							stream.write(bytes);

						} finally {
							stream.close();
						}


						FileOutputStream map = new FileOutputStream("../Huffman2/src/lib/map.dat");
						ObjectOutputStream maps = new ObjectOutputStream(map);
						maps.writeObject(hm.decode);
						map.close();






						BitWriter bw = new BitWriter(code2Convert.length());
						
						String strbits = code2Convert.toString();
						for( int i = 0; i < strbits.length(); i++) {
							bw.writeBit( strbits.charAt(i) == '1');

						}
						System.out.println("Printing Bitwriter as string  : " +strbits);
						


						System.out.println("\n\nString in Hex Value from BitWriter  ");
						byte[] b = bw.toArray();
						for( byte a : b ) {
							System.out.format("%02X", a);
						}   
						
						
						FileOutputStream bitwriter = new FileOutputStream("../Huffman2/src/lib/bitwriter.dat");
						ObjectOutputStream m = new ObjectOutputStream(bitwriter);
						m.writeObject(b);
						bitwriter.close();
						/*try {
							bitwriter.write(b);

						} finally {
							bitwriter.close();
						}*/
						
							
						//inputted string 1011000111  1011 0001 1100 0000
						//B1 == 10110001
						//C0 == 11000000

						break;
					case 2:


						FileInputStream f = new FileInputStream("../Huffman2/src/lib/map.dat");
						ObjectInputStream readMap = new ObjectInputStream(f);
						hm.decodeMap = (HashMap<String, Character>) readMap.readObject();
						readMap.close();
						System.out.println("DECODE MAP" + hm.decodeMap);
						
						
						//reading in the byte array
						byte[] array = Files.readAllBytes(new File("../Huffman2/src/lib/bytes.dat").toPath());
						String y = new String(array);
						System.out.println("Reading from bytes.dat  : " + y);

						//reading in the bitwriter
						FileInputStream bitwrite = new FileInputStream("../Huffman2/src/lib/bitwriter.dat");
						ObjectInputStream readBit = new ObjectInputStream(bitwrite);
						byte[] bitWriterArray = (byte[]) readBit.readObject();
						readMap.close();
						System.out.println("Reading from bitWriter.dat  : " + Arrays.toString(bitWriterArray));
						
						
						
						

						String result = " ";
						System.out.println("Result from Decode : " + hm.printDecode(y,result));

						String magicBytes1 = " 0CADD099";
						String magic1  = hm.printDecode(y,result).substring(0, 9);

						if (magicBytes1.equals(magic1)){


							System.out.println("File Sigature a Match : " + magic1);
						}
						else {
							System.out.println("File Signature Incorrect");
						}

						System.out.println("Size of BitWriter File bitwriter.dat : " + bitWriterArray.length);
						
						
						break;

					default:    System.out.println("Invalid option entered: " + option);
					mainMenu();
					break;
					}

					//pause the program so that the user can read what we just printed to the terminal window
					System.out.println("\n\nPress any key to continue...");
					input.nextLine();

					//display the main menu again
					option = mainMenu();
					//the user chose option 0, so exit the program
				}
				System.out.println("Exiting... bye");
				h.store();
				System.exit(0);

			}

			catch (Exception e) {
				input.nextLine(); //swallows bug
				System.err.println("Num expected - You Entered Text. Try Again...\n");
			}
		} while (!goodInput);  	




	}


	/**
	 * mainMenu() - This method displays the menu for the application, 
	 * reads the menu option that the user entered and returns it.
	 * @return     the users menu choice
	 */

	private static  int mainMenu() {
		System.out.println("\n1)Print Huffman Code");
		System.out.println("\n2)Decode");

		System.out.println("0) Exit");
		System.out.print("==>>");

		int option = input.nextInt();
		return option;
	}







}//end of main

