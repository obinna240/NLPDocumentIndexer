package com.com.parser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.Collections;
import java.util.HashSet;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.PhoneExtractingContentHandler;

public class GrabPhoneNumbersExample {
    private static HashSet<String> phoneNumbers = new HashSet<>();
    private static int failedFiles, successfulFiles = 0;

    /**
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage `java GrabPhoneNumbers [corpus]");
            return;
        }
        Path folder = Paths.get(args[0]);
        System.out.println("Searching " + folder.toAbsolutePath() + "...");
        processFolder(folder);
        System.out.println(phoneNumbers.toString());
        System.out.println("Parsed " + successfulFiles + "/" + (successfulFiles + failedFiles));
    }
    */
    
    public static void main(String[] args) {
        
        Path folder = Paths.get("C:/Users/oonyimadu/PERSONAL");
        System.out.println("Searching " + folder.toAbsolutePath() + "...");
        processFolder(folder);
        System.out.println(phoneNumbers.toString());
        System.out.println("Parsed " + successfulFiles + "/" + (successfulFiles + failedFiles));
    }
    

    public static void processFolder(Path folder) {
        try {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        process(file);
                        successfulFiles++;
                    } catch (Exception e) {
                        failedFiles++;
                        // ignore this file
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    failedFiles++;
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            // ignore failure
        }
    }

    public static void process(Path path) throws Exception {
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        // The PhoneExtractingContentHandler will examine any characters for phone numbers before passing them
        // to the underlying Handler.
        PhoneExtractingContentHandler handler = new PhoneExtractingContentHandler(new BodyContentHandler(), metadata);
        try (InputStream stream = new BufferedInputStream(Files.newInputStream(path))) 
        {
            parser.parse(stream, handler, metadata, new ParseContext());
        }
        String[] numbers = metadata.getValues("phonenumbers");
        Collections.addAll(phoneNumbers, numbers);
    }
}
