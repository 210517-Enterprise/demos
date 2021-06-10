package com.revature;
import static spark.Spark.*;

/*
 * This is how we import the framework...
 * 
 * If we statically import, we can access the 
 * static members of a class directly without 
 * class name or any object - like get(), post()...
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// Creating a lightweight HTTP server using Spark Micro-Framework https://sparkjava.com/documentation
public class SparkServer {
	
    private static final String INPUT_FILE = "C:\\Users\\SophieGavrila\\Desktop\\demos\\week4\\ThroughputHttpServer\\src\\main\\resources\\war_and_peace.txt";
	
    // We cannot manage thread pools with this framework, on this server (at least, the framework absrtacts this capability away from us)
    public static void main(String[] args) {
		
    	startServer();
    	
    	// we'll apply some type of internale service error method
    	internalServerError("<html><body><h1>Custom 500 handling method has been implemented....</h1></body></html>");
    	// hypertext! we have hypertext transfer protocol,  and we hypertext markup language (html)
	}
    
    public static void startServer() {
    	
    	port(1234); // set a custom port
    	
    	// whenever we send a get request to the /search context, we respond with the amount of times the words appeared
    	get("/search", (request, response) -> {
    		
    		response.type("text/html");
    		
    		/*
    		 * Verify that the query param = "word"
    		 * notice that I don't have to provide extra code to split up my query (like in our old server)
    		 */
    		if(!request.queryMap().hasKey("word")) {
    			response.status(422);
    			return "Your query parameter key must be equal to \"word\"";
    			
    		}
    		
    		String input = request.queryParams("word"); // this is a helpful method straight from the framework which helps us acquire the
    													// value of a query param
    		
    		long count = countWord(input);
    		
    		return "The word \"" + input + "\" appeared " + count + " times";
    	});
    }
    
    private static long countWord(String word) throws IOException {
    	
    	System.out.println("countWord() method triggered");
    	
    	String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
    	
        long count = 0;
        int index = 0;
		/*
		 * Run a loop.
		 * in every iteration look for the next appearance of the word in the text
		 */
        while (index >= 0) {
        	
            index = text.indexOf(word, index);

            // if index is positive, we successfully found the word in the book.
            if (index >= 0) {
                count++;  
                index++; // increment index so that we can keep searching for the 
				 		 // word again starting from the next position in in the book.
            }
        }
        
    	return count;
    }
}
