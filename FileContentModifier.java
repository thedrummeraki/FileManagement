
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
