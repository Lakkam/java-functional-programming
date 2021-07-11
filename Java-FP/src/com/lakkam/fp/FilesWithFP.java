package com.lakkam.fp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class FilesWithFP {

	public static void main(String[] args) throws IOException {
		
		
		Files.lines(Paths.get("files.txt")).forEach(System.out::println); //This will print file content
		
		//Lets say, if we want to to fetch unique words from the file content
		Files.lines(Paths.get("files.txt")).map(str->str.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);

	}

}
