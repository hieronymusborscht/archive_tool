package jto.out;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterOfFiles {

	//private static final String FILENAME = "index.html";
	public static void writeAFile( String content, String target_folder, String document_name) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		
		

		try {
			fw = new FileWriter(target_folder+"/"+document_name);
			bw = new BufferedWriter(fw);
			bw.write(content);
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {

				if (bw != null){
					bw.close();
				}
				if (fw != null){
					fw.close();
				}
			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	public static boolean DoesFolderExist(String file_path) {
		java.io.File f = new File(file_path);
		return (f.exists() && f.isDirectory() && f.canWrite());
	}
	

}
