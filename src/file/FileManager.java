package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to manager the usage of files.
 * @author danilo.melo.rocha
 *
 */
public class FileManager {
	
	/**
	 * Read file as String.  
	 * @param filePath
	 * @return String
	 */
	public static String getInput(String filePath) {
        String input = null;
        try {
            BufferedReader buff = new BufferedReader(new FileReader(filePath));
            input = buff.readLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        return input;
    }

	/**
	 * Write StringBuffer in a file.
	 * @param buffer
	 * @param location
	 */
    public static void createFile(StringBuffer buffer, String location) {
        try {
            File fl = new File(location);
            fl.createNewFile();
            FileWriter fw = new FileWriter(fl);
            fw.write(buffer.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
