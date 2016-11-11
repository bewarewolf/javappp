package com.nixsolutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CommonsIO {

	public static void copy(File from, File to) throws IOException {
		FileUtils.copyDirectory(from, to);
	}
}
