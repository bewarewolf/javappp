package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import exception.Save;

public class SaveFile implements Save {

	@Override
	public void save(String data, String fileName) {
		if (data == null || fileName == null) {
			throw new SaveException("Data and file name should not be null");
		}
		
		File saveTo = new File(fileName);
		
		if (saveTo.exists()) {
			throw new SaveException("File already exists");
		}
		
		try (FileWriter fw = new FileWriter(saveTo)) {
			fw.write(data);
		} catch (IOException ex) {
			ex.printStackTrace();	
			throw new SaveException(ex);
		}
	}

	public static void main (String[] args) {
		SaveFile sf = new SaveFile();
		String tempDir = System.getProperty("java.io.tmpdir");
		
		String path = tempDir + File.separator + "savedFile.txt";
		System.out.println("Writing to file " + path);
		sf.save("Some important data", path);
	}
}
