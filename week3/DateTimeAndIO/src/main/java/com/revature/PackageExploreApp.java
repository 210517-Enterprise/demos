package com.revature;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Consumer;

/*
 * https://www.novixys.com/blog/java-nio-using-bytebuffer/
 * 
 * Basic package explorer written in Java.  Leverages Java NIO and Date Time packages.
 * 
 * User Stories:
 * As a user:
 * 
 * 		- I can read and write files
 * 		- I can create and read from directories
 * 		- I can read Data and Time of last file modification
 * 
 * Java NIO (New IO) is an alternative IO API for Java, meaning alternative to
 * the standard Java IO and Java Networking API's. Java NIO offers a different
 * IO programming model than the traditional IO APIs.
 * 
 * Java NIO is a buffer oriented package. It means that the data can be
 * written/read to/from a buffer which further processed using a channel.
 * 
 * Java NIO enables you to do non-blocking IO. For instance, a thread can ask a
 * channel to read data into a buffer. While the channel reads data into the
 * buffer, the thread can do something else. Once data is read into the buffer,
 * the thread can then continue processing it. The same is true for writing data
 * to channels.
 * 
 */
public class PackageExploreApp {
	
	
	public static void main(String[] args) {
		
		String stringPath = "src/main/resources/output.txt";
		
		/*
		 * Path is an interface which is introduced in Java NIO file package during Java
		 * version 7,and is the representation of location in particular file system.As
		 * path interface is in Java NIO package so it get its qualified name as
		 * java.nio.file.Path.
		 */
		Path path = FileSystems.getDefault().getPath(stringPath);
		/*
		 * The FileSystems class defines methods to create file systems that provide
		 * access to other types of (custom) file systems. A file system is the factory
		 * for several types of objects: The getPath method converts a system dependent
		 * path string, returning a Path object that may be used to locate and access a
		 * file.
		 */
		try {
			RandomAccessFile reader = new RandomAccessFile(stringPath, "r");
			FileChannel channel = reader.getChannel();
			/*
			 * Channels are a medium of writing a stream of data to a data container called a buffer
			 */
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			// if buffer size is too large it will write too much information to the channel
			// https://stackoverflow.com/questions/34493320/how-does-buffer-size-affect-nio-channel-performance
			int bufferSize = 1024;
			
			if(bufferSize > channel.size()) {
				bufferSize = (int) channel.size();
			}
			
			// https://www.novixys.com/blog/java-nio-using-bytebuffer/
			ByteBuffer buff = ByteBuffer.allocate(bufferSize); // byte array is created by this class
			
			while(channel.read(buff) > 0) {
				out.write(buff.array(), 0, buff.position());
				buff.clear();
			}
			
			String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
			System.out.println(fileContent);
			
			// Write to a file
			Files.write(path, "\nHello there".getBytes(), StandardOpenOption.APPEND);
			
			
			// Reading from the file
			List<String> stringList = Files.readAllLines(path);
			Consumer<String> printer = System.out::println;   //s -> System.out.println(s); 
			// Here I'm calling our Consumer function.
			Files.readAllLines(path).forEach(printer);
			
			// Get File attributes
			BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println("File creation time: " + attributes.creationTime());
			System.out.println("Last modified: " + attributes.lastModifiedTime());
			System.out.println("Last accessed: " + attributes.lastAccessTime());
			System.out.println(Files.getAttribute(path, "creationTime"));
				
			// Create a directory
			Path dirPath = FileSystems.getDefault().getPath("src/main/java/com/revature/resources/test");
			Files.createDirectories(dirPath);
				
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}




















