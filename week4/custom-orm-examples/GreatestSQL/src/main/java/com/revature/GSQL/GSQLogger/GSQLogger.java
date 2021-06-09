package com.revature.GSQL.GSQLogger;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class.  Class serves as the logging of the framework.
 * Singleton, so only a single instance cna ever exists.
 * user of framework generally has no need to explicitly interact or use this class.
 */
public class GSQLogger {
    private static final GSQLogger logger = new GSQLogger();
    private PrintWriter log;
    private DateTimeFormatter format;

    /**
     * private constructor for Logger class.
     * creates a new static instance of class.
     */
    private GSQLogger() {
        try {
            log = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/GSQLog.txt",false)));
            format = DateTimeFormatter.ofPattern("yy-MM-dd: HH:mm:ss");

        }catch(IOException ioe) {
            //do something here.
        }
    }

    /**
     * Get the instance of the singleton of this class.
     * @return Instance of GSGLogger class.
     */
    public static GSQLogger getInstance() {
        return logger;
    }

    /**
     * Writes times and message to the log file.
     * @param message message to print to log file.
     */
    private void writeToLogger(final String message) {
        System.out.println("Sorry, there was a problem with your request. Please show log file to a smart developer.");
        log.write("\n======================================\n");
        log.write(LocalDateTime.now().format(format) + "\n\t");
        log.write(message);
    }

    /**
     * Writes an error message to the log file.
     * @param error message to write to log file.
     */
    public void writeError(final String error) {
        writeToLogger(error);
        log.flush();
    }

    /**
     * Writes an exception trace to the log file.
     * @param e exception to write to log file.
     */
    public void writeError(final Exception e) {
        writeToLogger("");
        e.printStackTrace(log);
        log.flush();
    }
}
