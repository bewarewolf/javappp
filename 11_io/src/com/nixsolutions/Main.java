package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
	
	public static void main (String[] args) throws IOException {		
		File path = Paths.get(".").toAbsolutePath().normalize().resolve("content").toFile();
		File pathIo = Paths.get(".").toAbsolutePath().normalize().resolve("javaIo").toFile();
		File pathNio = Paths.get(".").toAbsolutePath().normalize().resolve("javaNio").toFile();
		File pathCommons = Paths.get(".").toAbsolutePath().normalize().resolve("commonsIo").toFile();
		
		JavaIO.copy(path, pathIo);
		JavaNIO.copy(path, pathNio);
		CommonsIO.copy(path, pathCommons);
	}
	
	public static void generateFileTree(File path) {
		
	}
}
