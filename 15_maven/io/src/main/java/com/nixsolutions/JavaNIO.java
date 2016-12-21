package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class JavaNIO {

	public static void copy(File from, File to) throws IOException {
		if (!to.exists()) {
			to.mkdir();
		}
		
		Files.walkFileTree(from.toPath(), new MyFileVisitor(to.toPath()));
	}
	
	static class MyFileVisitor extends SimpleFileVisitor<Path> {
		
	    private final Path targetPath;
	    private Path sourcePath = null;
	    
	    public MyFileVisitor(Path targetPath) {
	        this.targetPath = targetPath;
	    }

	    @Override
	    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException {
	        if (sourcePath == null) {
	            sourcePath = dir;
	        } else {
	        	Files.createDirectories(targetPath.resolve(sourcePath.relativize(dir)));
	        }
	        
	        return FileVisitResult.CONTINUE;
	    }

	    @Override
	    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
	    	Files.copy(file, targetPath.resolve(sourcePath.relativize(file)));
	    	
	    	return FileVisitResult.CONTINUE;
	    }
	}   
}
 