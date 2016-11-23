package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaIO {

  public static void copy(File from, File to) {
    if (!to.exists()) {
      to.mkdir();
    }

    for (File f : from.listFiles()) {
      File newTo = new File(to, f.getName());

      if (f.isDirectory()) {
	newTo.mkdir();
	copy(f, newTo);
      } else {
	copyFile(f, newTo);
      }
    }
  }

  private static void copyFile(File from, File to) {
    try (FileInputStream fis = new FileInputStream(from); 
	FileOutputStream fos = new FileOutputStream(to);) {

      if (!to.exists()) {
	to.createNewFile();
      }

      byte[] buffer = new byte[1024];
      int length;
      while ((length = fis.read(buffer)) > 0) {
	fos.write(buffer, 0, length);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
