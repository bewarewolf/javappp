package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class Utils {

	public static byte[] serialize(MyBean in) throws IOException {
		ObjectOutputStream os = null;
		
		try {
			ByteArrayOutputStream bs = new ByteArrayOutputStream();		
		
			GZIPOutputStream gzipOut = new GZIPOutputStream(bs) {
				{
					this.def.setLevel(Deflater.BEST_COMPRESSION);
				}
			};
			os = new ObjectOutputStream(gzipOut); 
			os.writeObject(in);
			
			gzipOut.finish();
			
			return bs.toByteArray();
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
	
	public static MyBean deserialize(byte[] in) throws IOException, ClassNotFoundException {
		ObjectInputStream is = null;
		
		try {
			ByteArrayInputStream bs = new ByteArrayInputStream(in);			
			GZIPInputStream gzipIn = new GZIPInputStream(bs);
			is = new ObjectInputStream(gzipIn); 
			
			return (MyBean) is.readObject();
		} catch (IOException | ClassNotFoundException ex) {
			throw ex;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
}
