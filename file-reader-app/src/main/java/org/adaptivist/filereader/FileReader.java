package org.adaptivist.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


    /*
        A command-line application that takes a path
        to a file as an argument and prints
        a word count of its contents.
    */
public class FileReader {
    public static void main(String[] args) throws IOException {

        //Checking if there is only  one argument passed and throwing exception for all other illegal arguments
        if(args.length == 0 || args.length >1)
            throw new IllegalArgumentException("Multiple arguments found");


        /*
        Creating a java stream to read the file from the argument. This includes the following steps
            1. Splitting the contents of the file with spaces so that we will get a string of array having all the words
            2. doing a parallel process to make it more efficient (order is not important for insertion)
            3. Mapping with a regex pattern to avoid special characters
            4. Saving them to a map with key as the string elements and value as the frequency
            5. Streaming the map
            6. Sorting map on the basis of value
            7. Printing the result
        */

        System.out.println("\n File Reader Output: \n");
        Arrays.stream(Files.readString(Paths.get(args[0]))
                .split(" "))
                .parallel()
                .map(data -> data.replaceAll("[^a-zA-Z0-9]", ""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(data -> System.out.println(data.getKey() + " : " + data.getValue()));

    }
}