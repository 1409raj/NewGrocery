package com.sampark.grocery.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class MainClass {

	public static void main(String[] args) {
		  /* Total number of processors or cores available to the JVM */
		  System.out.println("Available processors (cores): " + 
		  Runtime.getRuntime().availableProcessors());

		  /* Total amount of free memory available to the JVM */
		  System.out.println("Free memory (bytes): " + 
		  Runtime.getRuntime().freeMemory());

		  /* This will return Long.MAX_VALUE if there is no preset limit */
		  long maxMemory = Runtime.getRuntime().maxMemory();
		  /* Maximum amount of memory the JVM will attempt to use */
		  System.out.println("Maximum memory (bytes): " + 
		  (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

		  /* Total memory currently in use by the JVM */
		  System.out.println("Total memory (bytes): " + 
		  Runtime.getRuntime().totalMemory());

		  try {
			    String line;
			    Process p = Runtime.getRuntime().exec("top");
			    BufferedReader input =
			            new BufferedReader(new InputStreamReader(p.getInputStream()));
			    while ((line = input.readLine()) != null) {
			        System.out.println(line); //<-- Parse data here.
			    }
			    input.close();
			} catch (Exception err) {
			    err.printStackTrace();
			}
		  
	}
}
