package com.revature;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class App {

	private static final String INPUT_FILE = "C:\\Users\\SophieGavrila\\Desktop\\demos\\week4\\ThroughputHttpServer\\src\\main\\resources\\war_and_peace.txt";
	
	private static final int NUMBER_OF_THREADS = 1;
	
	public static void main(String[] args) throws IOException {
		
		String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
		
		// we will pass this string object to a startServer method (this represent the data that our server manages
		startServer(text);

	}
	
	public static void startServer(String text) throws IOException {
		
		
		/*
		 * 1st param: takes the address of our localhost and a port we want to listen to requests on
		 * 
		 * 2nd param: this is the backlog size of a queue for HTTP Server request (we'll set this to 0)
		 * becasue we want all requests to end up in our thread pool queue
		 */
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		
		/*
		 * Create a context which assigns a handler object to a specific http route.
		 * This handler handles each incoming http request and sends a repsonse
		 */
		server.createContext("/search", new WordCountHandler(text)); // we will create this handler class
		
		/*
		 * Create the Executor which will schedule each incoming http request to 
		 * a pool of threads
		 */
		Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		
		
		/*
		 * We pass the executor the Http Server
		 */
		server.setExecutor(executor);

		server.start();
	}

	/*
	 * Implement the WordCountHandler class itself which implements the logic of
	 * searching for the word and counting its recurrence throughout the text.
	 */
	private static class WordCountHandler implements HttpHandler {
		
		private String text;
		
		public WordCountHandler(String text) {
			this.text = text;
		}

		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			
			// Here were are caputring the word that the client is searching for
			String query = httpExchange.getRequestURI().getQuery(); // this will generate word=talk or word=some-value-that-the-user-is-looking-for
			
			String[] keyValue = query.split("="); // now we've separated our query to an array
			String action = keyValue[0];
			String word = keyValue[1];
			
			
			// Check that the query param KEY is indeed "word"
			if(!action.equals("word")) {
				httpExchange.sendResponseHeaders(400, 0); // essentially we're sending back to the client that the request was not good....
				return;
			}
			
			String count = countWord(word); // create a method called countWord
			
			// transforms the string to a byte[] 
			byte[] response = count.getBytes();
			// we need to make sure that our response object is capable of sending back a valid status code and response body size
			httpExchange.sendResponseHeaders(200, response.length);
			
			// We will represent our repsonse body as an Output Stream
			OutputStream outputStream = httpExchange.getResponseBody();
			
			// then write the response body into Http RepsonseBody output Stream
			outputStream.write(response);
			
			// close the stream. This automatically sends the response to the client.
			outputStream.close();
			
		}
		
		private String countWord(String word) {
			// implment some logic here to check the number of times the word shows up in the text
			System.out.println("countWord() method triggered");
			
			long count = 0;
			int index = 0;
			
			/*
			 * Run a loop.
			 * In every iteration look for the next appearance of the word in the text
			 */
			
			while (index >= 0) {
				
				index = text.indexOf(word, index);
				
				// if the index is positive, we successfully found the word in the book
				if (index >=0) {
					count++;;
					index++;
				}

			}			
			return "The word \"" + word + "\" appeared " + count + " times";
		}

	}
	
}










