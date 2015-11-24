
//Java Util imports

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

//Java AWT imports
import java.awt.Desktop;

//Java Input/Output imports
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
* Allows to make modifications to a raw document file (i.e: .txt). This class allows to write a String to a file, or even more complex
* arrays, choosing to overwrite the file after several runs. It also can replace a specified existing String to another while the file
* existing. That can be useful for updating client databases. All of the methods of this class have to be used in a static way. Otherwise
* won't be possible.
* <p>
* Example:
* <p>  
* {@code FileContentModifier.write("I am the first line of the text file", "this/is/the/path/of/the/file.txt", true);}
* <p>
* The code above writes the String "I am the first line of the text file" at a file specified path "this/is/the/path/of/the/file.txt" wanting the
* file to be overwritten (true). If the path is valid but if the file is not found, it will be created. If the path is not valid, an
* exception will be thrown.
* <p>
* Example:
* <p>  
* "C:/Users/Hello/Folder/file.txt" is a valid file-path.
* <p>
* "C:/Users/Hello/Folder/file" is an invalid file-path.
* @author Akinyélé
* */
public class FileContentModifier {

	private static ArrayList<String> myList = new ArrayList<String>();

	//invisible constructor
	private FileContentModifier() {
		// cannot explicitally call this contructor
	}
	
	/**
	 * Writes the specified {@code content} to the specified {@code path}. The content of the file will not automatically overwritten.
	 * If you want to programmatically overwrite the file at the specified {@code path}, please use the complementary method
	 * {@code FileContentModifier.write(String content, String path, boolean overwrite)}. This method checks if the file exists. 
	 * If not, it creates a new file. If the path specified is incorrect (i.e: "/file/file.txt" is good, "/file/folder/" is not), 
	 * an exception will then be thrown.
	 * 
	 * @return {@code write} (if the writing was complete)
	 * @author Akinyélé
	 * */
	public static boolean write(String content, String path) throws IOException {
		File file = new File(path);
		boolean write = file.exists();
		boolean eo = true;
		if (!contains(content, path)) {
			
			if (!write) {
				if (!file.createNewFile()) {
					throw new IOException("Invalid path specified");
				}
			}
				
			BufferedWriter output = new BufferedWriter(new FileWriter(file,
					true));
			if (eo) {
				System.out.println("File existed.");
			}
			output.write(content);
			output.newLine();
			output.close();
			
			System.out.println("Operation complete.");
		} else {
			System.out.println("Content has already been found.");
			return false;
		}
		return write;
	}

	/**
	 * Writes the specified {@code content} to the specified {@code path} with a {@code boolean} value deciding whether the list
	 * storing the content of the file found at {@code path} should be overwritten. Checks if the file exists. If not, it creates
	 * a new file. If the path specified is incorrect (i.e: "/file/file.txt" is good, "/file/folder/" is not), an exception will
	 * be thrown.
	 * 
	 * @return {@code write} (if the writing was complete)
	 * @author Akinyélé
	 * */
	public static boolean write(String content, String path, boolean overwrite) throws IOException{
		File file = new File(path);
		boolean write = file.isFile() && file.exists();
		if (!write) {
			if (!file.createNewFile()) {
				throw new IOException("Invalid path specified.");
			}
		}
			
		BufferedWriter output = new BufferedWriter(new FileWriter(file,
				!overwrite));
		output.write(content);
//		output.write(System.getProperty("line.separator"));
		output.newLine();
		output.close();
		System.out.println("Operation complete.");
		
		return write;
	}
	
	/**
	 * Writes the specified {@code content} to the specified {@code path} with a {@code boolean} value deciding whether the list
	 * storing the content of the file found at {@code path} should be overwritten. Checks if the file exists. If not, it creates
	 * a new file. If the path specified is incorrect (i.e: "/file/file.txt" is good, "/file/folder/" is not), an exception will
	 * be thrown. It is important to implement the {@code toString} method if you want to write the proper Object's information,
	 * contained in the {@code ArrayList<Object> contents}.
	 * 
	 * @return {@code write} (if the writing was complete)
	 * @author Akinyélé
	 * */
		
	public static boolean write(ArrayList<?> list, String path, boolean overwrite) throws IOException {
		boolean trying = false;
		for (Object obj : list) {
			trying = write(obj.toString(), path, overwrite);
		}
		return trying;
	}
	
	/**
	 * Encrypts a given (decrypted) message. Can encrypt almost everything. A non-recognized character
	 * will be encrypted as a "space" (' ').
	 * 
	 * @return {@code message} (encrypted)
	 * @author Akinyélé
	 * */
	
	public static String encrypt(String message) {
		char[] seq = message.toCharArray();
		char[] temp = new char[seq.length];
		for (int i = 0; i<message.length(); i++) {
			if (seq[i] == 'a' || seq[i] == 'A') {
				if (seq[i] == 'a') {
					temp[i] = 'b';
				} else {
					temp[i] = 'B';
				}
			} else if (seq[i] == 'b' || seq[i] == 'B') {
				if (seq[i] == 'b') {
					temp[i] = 'c';
				} else {
					temp[i] = 'C';
				}
			} else if (seq[i] == 'c' || seq[i] == 'C') {
				if (seq[i] == 'c') {
					temp[i] = 'd';
				} else {
					temp[i] = 'D';
				}
			} else if (seq[i] == 'd' || seq[i] == 'D') {
				if (seq[i] == 'd') {
					temp[i] = 'e';
				} else {
					temp[i] = 'E';
				}
				//� : $
				//� : &
				//� : ?
				//� : *
			} else if (seq[i] == 'e' || seq[i] == 'E' || seq[i] == '�' || seq[i] == '�' || seq[i] == '�' || seq[i] == '�') {
				if (seq[i] == 'e') {
					temp[i] = 'f';
				} else if (seq[i] == '�') {
					temp[i] = '$';
				} else if (seq[i] == '�') {
					temp[i] = '&';
				} else if (seq[i] == '�') {
					temp[i] = '?';
				} else if (seq[i] == '�') {
					temp[i] = '*';
				} else {
					temp[i] = 'F';
				}
			} else if (seq[i] == 'f' || seq[i] == 'F') {
				if (seq[i] == 'f') {
					temp[i] = 'g';
				} else {
					temp[i] = 'G';
				}
			} else if (seq[i] == 'g' || seq[i] == 'G') {
				if (seq[i] == 'g') {
					temp[i] = 'h';
				} else {
					temp[i] = 'H';
				}
			} else if (seq[i] == 'h' || seq[i] == 'H') {
				if (seq[i] == 'h') {
					temp[i] = 'i';
				} else {
					temp[i] = 'I';
				}
			} else if (seq[i] == 'i' || seq[i] == 'I') {
				if (seq[i] == 'i') {
					temp[i] = 'j';
				} else {
					temp[i] = 'J';
				}
			} else if (seq[i] == 'j' || seq[i] == 'J') {
				if (seq[i] == 'j') {
					temp[i] = 'k';
				} else {
					temp[i] = 'K';
				}
			} else if (seq[i] == 'k' || seq[i] == 'K') {
				if (seq[i] == 'k') {
					temp[i] = 'l';
				} else {
					temp[i] = 'L';
				}
			} else if (seq[i] == 'l' || seq[i] == 'L') {
				if (seq[i] == 'l') {
					temp[i] = 'm';
				} else {
					temp[i] = 'M';
				}
			} else if (seq[i] == 'm' || seq[i] == 'M') {
				if (seq[i] == 'm') {
					temp[i] = 'n';
				} else {
					temp[i] = 'N';
				}
			} else if (seq[i] == 'n' || seq[i] == 'N') {
				if (seq[i] == 'n') {
					temp[i] = 'o';
				} else {
					temp[i] = 'O';
				}
			} else if (seq[i] == 'o' || seq[i] == 'O') {
				if (seq[i] == 'o') {
					temp[i] = 'p';
				} else {
					temp[i] = 'P';
				}
			} else if (seq[i] == 'p' || seq[i] == 'P') {
				if (seq[i] == 'p') {
					temp[i] = 'q';
				} else {
					temp[i] = 'Q';
				}
			} else if (seq[i] == 'q' || seq[i] == 'Q') {
				if (seq[i] == 'q') {
					temp[i] = 'r';
				} else {
					temp[i] = 'R';
				}
			} else if (seq[i] == 'r' || seq[i] == 'R') {
				if (seq[i] == 'r') {
					temp[i] = 's';
				} else {
					temp[i] = 'S';
				}
			} else if (seq[i] == 's' || seq[i] == 'S') {
				if (seq[i] == 's') {
					temp[i] = 't';
				} else {
					temp[i] = 'T';
				}
			} else if (seq[i] == 't' || seq[i] == 'T') {
				if (seq[i] == 't') {
					temp[i] = 'u';
				} else {
					temp[i] = 'U';
				}
			} else if (seq[i] == 'u' || seq[i] == 'U') {
				if (seq[i] == 'u') {
					temp[i] = 'v';
				} else {
					temp[i] = 'V';
				}
			} else if (seq[i] == 'v' || seq[i] == 'V') {
				if (seq[i] == 'v') {
					temp[i] = 'w';
				} else {
					temp[i] = 'W';
				}
			} else if (seq[i] == 'w' || seq[i] == 'W') {
				if (seq[i] == 'w') {
					temp[i] = 'x';
				} else {
					temp[i] = 'X';
				}
			} else if (seq[i] == 'x' || seq[i] == 'X') {
				if (seq[i] == 'x') {
					temp[i] = 'y';
				} else {
					temp[i] = 'Y';
				}
			} else if (seq[i] == 'y' || seq[i] == 'Y') {
				if (seq[i] == 'y') {
					temp[i] = 'z';
				} else {
					temp[i] = 'Z';
				}
			} else if (seq[i] == 'z' || seq[i] == 'Z') {
				if (seq[i] == 'z') {
					temp[i] = 'a';
				} else {
					temp[i] = 'A';
				}
			} else if (seq[i] == '1') {
				temp[i] = '3';
			} else if (seq[i] == '0') {
				temp[i] = '7';
			} else if (seq[i] == '2') {
				temp[i] = '1';
			} else if (seq[i] == '3') {
				temp[i] = '0';
			} else if (seq[i] == '5') {
				temp[i] = '4';
			} else if (seq[i] == '6') {
				temp[i] = '5';
			} else if (seq[i] == '9') {
				temp[i] = '6';
			} else if (seq[i] == '7') {
				temp[i] = '2';
			} else if (seq[i] == '8') {
				temp[i] = '8';
			} else if (seq[i] == '4') {
				temp[i] = '9';
			} else if (seq[i] == '!') {
				temp[i] = '.';
			} else if (seq[i] == '.') {
				temp[i] = '!';
			}
		}
		StringBuffer sb = new StringBuffer();
		for (char c : temp) {
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	/**
	 * Decrypts a given encrypted message. Decrypting an non-encrypted message will return {@code null}.
	 * 
	 * @return {@code message} (encrypted)
	 * @author Akinyélé
	 * */
	
	public static String decrypt(String message) {
		char[] seq = message.toCharArray();
		char[] temp = new char[seq.length];
		for (int i = 0; i<message.length(); i++) {
			if (seq[i] == 'a' || seq[i] == 'A') {
				if (seq[i] == 'a') {
					temp[i] = 'z';
				} else {
					temp[i] = 'Z';
				}
			} else if (seq[i] == 'b' || seq[i] == 'B') {
				if (seq[i] == 'b') {
					temp[i] = 'a';
				} else {
					temp[i] = 'A';
				}
			} else if (seq[i] == 'c' || seq[i] == 'C') {
				if (seq[i] == 'c') {
					temp[i] = 'b';
				} else {
					temp[i] = 'B';
				}
			} else if (seq[i] == 'd' || seq[i] == 'D') {
				if (seq[i] == 'd') {
					temp[i] = 'c';
				} else {
					temp[i] = 'C';
				}
				//� : $
				//� : &
				//� : ?
				//� : *
			} else if (seq[i] == 'e' || seq[i] == 'E' || seq[i] == '�' || seq[i] == '�' || seq[i] == '�' || seq[i] == '�') {
				if (seq[i] == 'e') {
					temp[i] = 'd';
				} else if (seq[i] == '�') {
					temp[i] = '$';
				} else if (seq[i] == '�') {
					temp[i] = '&';
				} else if (seq[i] == '�') {
					temp[i] = '?';
				} else if (seq[i] == '�') {
					temp[i] = '*';
				} else {
					temp[i] = 'D';
				}
			} else if (seq[i] == 'f' || seq[i] == 'F') {
				if (seq[i] == 'f') {
					temp[i] = 'e';
				} else {
					temp[i] = 'E';
				}
			} else if (seq[i] == 'g' || seq[i] == 'G') {
				if (seq[i] == 'g') {
					temp[i] = 'f';
				} else {
					temp[i] = 'F';
				}
			} else if (seq[i] == 'h' || seq[i] == 'H') {
				if (seq[i] == 'h') {
					temp[i] = 'g';
				} else {
					temp[i] = 'G';
				}
			} else if (seq[i] == 'i' || seq[i] == 'I') {
				if (seq[i] == 'i') {
					temp[i] = 'h';
				} else {
					temp[i] = 'H';
				}
			} else if (seq[i] == 'j' || seq[i] == 'J') {
				if (seq[i] == 'j') {
					temp[i] = 'i';
				} else {
					temp[i] = 'I';
				}
			} else if (seq[i] == 'k' || seq[i] == 'K') {
				if (seq[i] == 'k') {
					temp[i] = 'j';
				} else {
					temp[i] = 'J';
				}
			} else if (seq[i] == 'l' || seq[i] == 'L') {
				if (seq[i] == 'l') {
					temp[i] = 'k';
				} else {
					temp[i] = 'K';
				}
			} else if (seq[i] == 'm' || seq[i] == 'M') {
				if (seq[i] == 'm') {
					temp[i] = 'l';
				} else {
					temp[i] = 'L';
				}
			} else if (seq[i] == 'n' || seq[i] == 'N') {
				if (seq[i] == 'n') {
					temp[i] = 'm';
				} else {
					temp[i] = 'M';
				}
			} else if (seq[i] == 'o' || seq[i] == 'O') {
				if (seq[i] == 'o') {
					temp[i] = 'n';
				} else {
					temp[i] = 'N';
				}
			} else if (seq[i] == 'p' || seq[i] == 'P') {
				if (seq[i] == 'p') {
					temp[i] = 'o';
				} else {
					temp[i] = 'O';
				}
			} else if (seq[i] == 'q' || seq[i] == 'Q') {
				if (seq[i] == 'q') {
					temp[i] = 'p';
				} else {
					temp[i] = 'P';
				}
			} else if (seq[i] == 'r' || seq[i] == 'R') {
				if (seq[i] == 'r') {
					temp[i] = 'q';
				} else {
					temp[i] = 'Q';
				}
			} else if (seq[i] == 's' || seq[i] == 'S') {
				if (seq[i] == 's') {
					temp[i] = 'r';
				} else {
					temp[i] = 'R';
				}
			} else if (seq[i] == 't' || seq[i] == 'T') {
				if (seq[i] == 't') {
					temp[i] = 's';
				} else {
					temp[i] = 'S';
				}
			} else if (seq[i] == 'u' || seq[i] == 'U') {
				if (seq[i] == 'u') {
					temp[i] = 't';
				} else {
					temp[i] = 'T';
				}
			} else if (seq[i] == 'v' || seq[i] == 'V') {
				if (seq[i] == 'v') {
					temp[i] = 'u';
				} else {
					temp[i] = 'U';
				}
			} else if (seq[i] == 'w' || seq[i] == 'W') {
				if (seq[i] == 'w') {
					temp[i] = 'v';
				} else {
					temp[i] = 'V';
				}
			} else if (seq[i] == 'x' || seq[i] == 'X') {
				if (seq[i] == 'x') {
					temp[i] = 'w';
				} else {
					temp[i] = 'W';
				}
			} else if (seq[i] == 'y' || seq[i] == 'Y') {
				if (seq[i] == 'y') {
					temp[i] = 'x';
				} else {
					temp[i] = 'X';
				}
			} else if (seq[i] == 'z' || seq[i] == 'Z') {
				if (seq[i] == 'z') {
					temp[i] = 'y';
				} else {
					temp[i] = 'Y';
				}
			} else if (seq[i] == '$') {
				temp[i] = '�';
			} else if (seq[i] == '?') {
				temp[i] = '�';
			} else if (seq[i] == '*') {
				temp[i] = '�';
			} else if (seq[i] == '&') {
				temp[i] = '�';
			} else if (seq[i] == '1') {
				temp[i] = '2';
			} else if (seq[i] == '0') {
				temp[i] = '3';
			} else if (seq[i] == '2') {
				temp[i] = '7';
			} else if (seq[i] == '3') {
				temp[i] = '1';
			} else if (seq[i] == '4') {
				temp[i] = '5';
			} else if (seq[i] == '5') {
				temp[i] = '6';
			} else if (seq[i] == '6') {
				temp[i] = '9';
			} else if (seq[i] == '7') {
				temp[i] = '0';
			} else if (seq[i] == '8') {
				temp[i] = '8';
			} else if (seq[i] == '9') {
				temp[i] = '4';
			} else if (seq[i] == '.') {
				temp[i] = '!';
			} else if (seq[i] == '!') {
				temp[i] = '.';
			}
		}
		StringBuffer sb = new StringBuffer();
		
		char[] ok = temp;
		
		for (char c : ok) {
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	/**
	 * Writes the specified {@code arrayOfContent} to the specified {@code path} with a {@code boolean} value deciding whether the list
	 * storing the content of the file found at {@code path} should be overwritten. Checks if the file exists. If not, it creates
	 * a new file. If the path specified is incorrect (i.e: "/file/file.txt" is good, "/file/folder/" is not), an exception will
	 * be thrown.
	 * 
	 * @return {@code write} (if the writing was complete)
	 * @author Akinyélé
	 * */	
	public static void write(String[] arrayOfContent, String path, boolean overwrite) throws IOException {
		for (String content : arrayOfContent) {
			write(content, path, overwrite);
		}
	}
	
	/**
	 * Writes the specified {@code arrayOfArraysOfContent} to the specified {@code path} with a {@code boolean} value deciding whether the list
	 * storing the content of the file found at {@code path} should be overwritten. Checks if the file exists. If not, it creates
	 * a new file. If the path specified is incorrect (i.e: "/file/file.txt" is good, "/file/folder/" is not), an exception will
	 * be thrown.
	 * 
	 * @return {@code write} (if the writing was complete)
	 * @author Akinyélé
	 * */	
	public static void write(String[][] arrayOfArraysOfContent, String path, boolean overwrite) throws IOException {
		for (String[] arrayOfContent : arrayOfArraysOfContent) {
			write(arrayOfContent, path, overwrite);
		}
	}
	
	/**
	 * Allows to replace an instance {@code toFind} in a document to another instance {@code toWrite} to a specified {@code path}.
	 * This method stores the content of the file, finds the index of {@code toFind}, sets {@code toWrite} at that index, creates
	 * a temporary variable storing the original content for any undo changes, writes the changes to the new file. If two instances
	 * of {@code toFind} are found at {@code path}, only the first one will be replaced.
	 * If {@code toFind}, the method prints an error message and the replacement is cancelled. Returns the temporary value.
	 * 
	 * @return temporary_value
	 * @author Akinyélé
	 * @throws IOException 
	 * */
	protected static ArrayList<String> replace(String toFind, String toWrite, String path) throws IOException {
		ArrayList<String> temporary_value;
		if (getIndex(toFind, path) >= 0) {
			getFileToArrayList(path);
			temporary_value = myList;
			System.out.println(myList);
			myList.set(getIndex(toFind, path), toWrite);
			System.out.println(myList);
			setArrayListToFile(path);
		} else {
			temporary_value = null;
			System.err.println("Problem with text file or the value searched was not found.");
		}
		return temporary_value;
		
	}
	
	/**
	 * Allows to replace an instance {@code toFind} in a document to another instance {@code toWrite} to a specified {@code path}.
	 * This method stores the content of the file, finds the index of {@code toFind}, sets {@code toWrite} at that index, creates
	 * a temporary variable storing the original content for any undo changes, writes the changes to the new file. 
	 * If {@code toFind}, the method prints an error message and the replacement is cancelled. Returns the temporary value. Also
	 * allows to open the file to see if the replacement was successful.
	 * 
	 * @return temporary_value
	 * @author Akinyélé
	 * @throws IOException 
	 * */
	public static ArrayList<String> replace(String toFind, String toWrite, String path, boolean openTheFile) throws IOException {
		ArrayList<String> temporary_value;
		if (getIndex(toFind, path) >= 0) {
			getFileToArrayList(path);
			temporary_value = myList;
			System.out.println(myList);
			myList.set(getIndex(toFind, path), toWrite);
			System.out.println(myList);
			setArrayListToFile(path);
		} else {
			temporary_value = null;
			System.err.println("Problem with text file or the value searched was not found.");
		}
		
		if (openTheFile) {
			try {
				Desktop d = Desktop.getDesktop();
				d.open(new File(path));
			} catch (IOException e) {}
		}
		
		return temporary_value;
		
	}
	
	/**
	 * Allows to replace an instance {@code toFind} in a document to another instance {@code toWrite} to a specified {@code path}.
	 * This method stores the content of the file, finds the index of {@code toFind}, sets {@code toWrite} at the specified {@code index}, creates
	 * a temporary variable storing the original content for any undo changes, writes the changes to the new file. 
	 * If {@code toFind}, the method prints an error message and the replacement is cancelled. Returns the temporary value. Also
	 * allows to open the file to see if the replacement was successful.
	 * 
	 * @return temporary_value
	 * @author Akinyélé
	 * @throws IOException 
	 * */
	public static ArrayList<String> replace(String toFind, String toWrite, int index, String path, boolean openTheFile) throws IOException {
		ArrayList<String> temporary_value;
		if (getIndex(toFind, path) >= 0 && index >= 0) {
			getFileToArrayList(path);
			String compare = myList.get(index);
			if (compare.equals(toFind)) {
				temporary_value = myList;
				myList.set(index, toWrite);
				setArrayListToFile(path);
			} else {
				System.out.println("The instance at index specified is not valid.");
				return null;
			}
		} else {
			throw new IOException("Problem with text file, invalid index or the value \"toFind\" was not found.");
		}
		return temporary_value;
		
	}

	public static int getIndex(String toFind, String path) {
		int index = -1;
		if (contains(toFind, path)) {
			index = 0;
			try {
				BufferedReader input = new BufferedReader(new FileReader(
						new File(path)));
				String line;
				while ((line = input.readLine()) != null) {
					if (line == toFind || line.equals(toFind))
						break;
					index++;
				}
				input.close();
			} catch (Throwable t) {}
		}
		return index;
	}
	
	public static int getIndex(String path) {
		int index = -1;
		File file = new File(path);
		if (file.exists()) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(file));
				index = 0;
				while (input.readLine() != null) {
					index++;
				}
				input.close();				
			} catch (IOException e) {}
		}
		return index;
	}
	
	public static boolean contains(String toFind, String path) {
		boolean found = false;
		if (path != null && toFind != null) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(
						new File(path)));
				String line;
				while ((line = input.readLine()) != null && !found) {
					if (line == toFind || line.equals(toFind))
						found = true;
				}
				input.close();
			} catch (Throwable t) {
			}

		}
		return found;
	}
	
	public static boolean containsLightly(String toFind, String path) {
		if (!contains(toFind, path)) {
			boolean found = false;
			if (path != null && toFind != null) {
				try {
					BufferedReader input = new BufferedReader(new FileReader(
							new File(path)));
					String line;
					while ((line = input.readLine()) != null && !found) {
						if (line.equals(toFind) || line.contains(toFind))
							found = true;
					}
					input.close();
				} catch (Throwable t) {
				}

			}
			return found;
		}
		return true;
	}

	public static String getInstance(int index, String path) {
		getFileToArrayList(path);		
		if (myList != null) {
			System.out.println(myList+" for path "+path);
			String instance = myList.get(index);
			clean();
			return instance;
		}
		return null;
	}
	
	public static String getInstance(String path) {
		getFileToArrayList(path);
		if (myList != null) {
			System.out.println(myList+" for path "+path);
			String instance = myList.get(0);
			return instance;
		}
		return null;
	}
	
	public static String[] getInstances(String path) {
		getFileToArrayList(path);
		int index = myList == null ? 0 : myList.size();
		if (index != 0) {
			String[] array = new String[index];
			for (int i = 0; i<index; i++) {
				array[i] = myList.get(i);
			}
			return array;
		}
		return new String[]{};
	}
	
	public static String[] getReversedInstances(String path) {
		getFileToArrayList(path);
		reverse();
		int index = myList == null ? 0 : myList.size();
		if (index != 0) {
			String[] array = new String[index];
			for (int i = 0; i<index; i++) {
				array[i] = myList.get(i);
			}
			return array;
		}
		return null;
	}
	
	private static void reverse() {
		Collections.reverse(myList);
	}
	
	public static void reverse(String path) {
		getFileToArrayList(path);
		Collections.reverse(myList);
		try {
			setArrayListToFile(path);
		} catch (IOException e) {
			//if exception occurs, leave source as it is.
			Collections.reverse(myList);
			myList = null;
			myList = new ArrayList<String>();
		}
	}
	
	public static boolean removeInFile(String toRemove, String path) {
		getFileToArrayList(path);
		if (myList.contains(toRemove)) {
			myList.remove(toRemove);
			try {
				setArrayListToFile(path);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public static boolean removeInFile(int indexToRemove, String path) {
		getFileToArrayList(path);
		if (myList.size() > indexToRemove) {
			myList.remove(indexToRemove);
			try {
				setArrayListToFile(path);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public static void clean() {
		System.out.println("java.tools.FileContentModifier.clean() called.");
		myList = null;
		myList = new ArrayList<String> ();
	}
		
	private static void getFileToArrayList(String path) {
//		System.out.println("Before: " + myList);
		clean();
		if (path.contains(".bra") || path.contains(".txt")) {
			if (path != null) {
				try {
					Scanner x = new Scanner(new File(path));
					while (x.hasNextLine()) {
						myList.add(x.nextLine());
					}
					x.close();
				} catch (Throwable t) {
				}
			}
		} else {System.err.println("Not a Banque d'Aki file or text file."); return; }
//		System.out.println("After: " + myList);
	}

	private static void setArrayListToFile(String path) throws IOException {
		if (path != null) {
			emptyTextFileAt(path, false);
			for (int i = 0; i < myList.size(); i++) {
				write(myList.get(i), path);
			}
		}
	}
	
	private static void emptyTextFileAt(String path, boolean eraseList) {
		if (eraseList) {
			myList = null;
			myList = new ArrayList<String> ();
		}
		File file = new File(path);
		if (file.exists()) {
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e) {		
			}
		} else System.err.println("Text file not existant.");
		
	}
	
	public static String convertSlashes(String toConvert, boolean toBack) {
		
		if (toConvert != null && (toConvert.contains("\\") || toConvert.contains("/"))) {
			char[] seq = toConvert.toCharArray();
			if (toBack) {
				for (int i = 0; i<seq.length; i++) {
					char c = seq[i];
					if (c == '/') {
						seq[i] = '\\';
					}
				}
			} else {
				for (int i = 0; i<seq.length; i++) {
					char c = seq[i];
					if (c == '\\') {
						seq[i] = '/';
					}
				}
			}
			toConvert = new String(seq);
		}
		
		return toConvert;
	
	}

	/**
	 * Only for testing purposes.
	 * */
	public static void main(String[] args) {
		
	}
}
