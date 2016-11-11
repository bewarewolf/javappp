package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.Save;

public class SaveFile implements Save {

  private static final Logger LOG = LogManager.getLogger("com.nixsolutions");
  
  @Override
  public void save(String data, String fileName) {
    LOG.traceEntry("save(data={}, file={})", data, fileName);
    
    if (data == null || fileName == null) {
      throw LOG.throwing(new SaveException("Data and file name should not be null"));
    }
    
    File saveTo = new File(fileName);

    if (saveTo.exists()) {      
      throw LOG.throwing(new SaveException("File already exists"));
    }

    FileWriter fw = null;
    
    try {
      LOG.info("Writing file");
      
      fw = new FileWriter(saveTo);
      fw.write(data);
    } catch (IOException ex) {      
      throw LOG.throwing(new SaveException(ex));
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          LOG.warn("Failed to close FileWriter", e);
        }
      }
      LOG.traceExit();
    }
  }

  public static void main(String[] args) {
    SaveFile sf = new SaveFile();
    String tempDir = System.getProperty("java.io.tmpdir");
    LOG.debug("tmp dir: " + tempDir);

    String path = tempDir + File.separator + "savedFile2.txt";
    LOG.debug("path: " + path);
    
    LOG.info("Saving " + path);
    sf.save("Some important data", path);
  }
}
