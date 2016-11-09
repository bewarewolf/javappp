package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader implements PathClassLoader {

	private Path path;
	
	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	public void setPath(String dir) {
		path = Paths.get(dir);
	}

	@Override
	public String getPath() {
		return path.toString();
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = null;
		
		try {
			Path resolvedPath = path.resolve(name.replace('.', File.separatorChar).concat(".class"));
			data = Files.readAllBytes(resolvedPath);
			return defineClass(name, data, 0, data.length);
		} catch (IOException ex) {
			return super.findClass(name);
		}
	}		
}
