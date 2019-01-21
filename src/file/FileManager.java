package file;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileManager {
	
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

}
