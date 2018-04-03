package com.com.fileExtractors;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.EnumSet;

import org.apache.commons.lang.StringUtils;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import com.com.parser.DocumentParser;
import com.com.solr.DocumentBin;
import com.com.solr.SolrIndexer;
import com.utils.WalkerClassUtils;



public class DirectoryWalker 
{
	private static int failedFiles, successfulFiles = 0;

	static final String DIR_NAME = "C:/users/oonyimadu/Documents";//"C:/users/oonyimadu/Personal/Attachments_2016922.zip";
	static final String[] FILETYPEARRAY = {".doc",".docx",".txt",".text",".pdf",".xls",".xlsx",".ppt",".pptx"};
	static int count = 90;
	
	public static class Finder  extends SimpleFileVisitor<Path> 
	{
		 @Override
	     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)       
		 {
			
			 String fileName = file.getFileName().toString();
			 if(StringUtils.endsWithAny(fileName, FILETYPEARRAY))
			 {
				 DocumentParser parser = new DocumentParser();
				 
				
				 
				 try 
				 {
					parser.parseDocument(file);
				}
				 catch (IOException | SAXException | TikaException e) 
				 {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 DocumentBin documentBin = parser.getDocumentBin();
				 
				// do parse here and include the following in metadata
				 FileTime ftime = attrs.creationTime();
				 if(ftime!=null)
				 {
					 String creationTime = WalkerClassUtils.fileTimeToString(ftime);
					 documentBin.setCreationTimeString(creationTime);
					 Date creationDate = WalkerClassUtils.fileTimeToDate(ftime);
					 documentBin.setCreationTime(creationDate);
				 }
				 
				 FileTime faccesstime = attrs.lastAccessTime();
				 if(faccesstime!=null)
				 {
					 String lastAccsessTime = WalkerClassUtils.fileTimeToString(faccesstime);
					 documentBin.setLastAccsessTimeString(lastAccsessTime );
					 Date lastAccsessDate = WalkerClassUtils.fileTimeToDate(faccesstime);
					 documentBin.setLastAccessTime(lastAccsessDate);
				 }
				 
				 FileTime fmodtime = attrs.lastModifiedTime();
				 if(fmodtime!=null)
				 {
					 String lastModifiedTime = WalkerClassUtils.fileTimeToString(fmodtime);
					 documentBin.setLastModifiedTimeString(lastModifiedTime);
					 Date lastModifiedDate = WalkerClassUtils.fileTimeToDate(fmodtime);
					 documentBin.setLastModifiedTime(lastModifiedDate);
				 }
				 
				 long fsize = attrs.size();
				 documentBin.setSize(fsize);
				 
				 
				 String fileType = WalkerClassUtils.checkDocumentType(attrs);
				 documentBin.setFileType(fileType);
				 
				 String fileParent = file.getParent().toString();
				 documentBin.setFileParent(fileParent);
				 String actualFileLocation  = file.toString();
				 documentBin.setFileLocation(actualFileLocation);
				documentBin.setId(fileName+ Integer.toString(count++));
				
				SolrIndexer sindexer = new SolrIndexer();
				sindexer.indexDocument(documentBin);
				
				System.out.println(count++ +" xx"+ fileName+ " "+ actualFileLocation+ " I am the parent "+fsize+" "+ file.toString()+" --- "+file.getParent());
		 	}
			 return FileVisitResult.CONTINUE;
		 }
		 
		  @Override
		  public FileVisitResult postVisitDirectory(Path dir, IOException exc)                                    
		  {
		      //print or log all visited directories 
			  //System.out.format("Directory: %s%n", dir);
			 // System.out.println("Ihave visited directory "+dir.getFileName());
		      return FileVisitResult.CONTINUE;
		  }
		  
		  public FileVisitResult preVisitDirectory(Path dir, IOException exc)
		  {
			// System.out.println("I am about to visit directory "+dir.getFileName());
			return FileVisitResult.CONTINUE;
			  
		  }
		  
		  @Override
		  public FileVisitResult visitFileFailed(Path file,
		                                       IOException exc) 
		  {
		        System.err.println(exc);
		        
		        return FileVisitResult.CONTINUE;
		  }
		  
		  public static void main(String[] args) throws IOException
		  {
			  Path startingDirectory = Paths.get(DIR_NAME);
			  Finder finder = new Finder();
			  EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
			  Files.walkFileTree(startingDirectory, opts, Integer.MAX_VALUE, finder);
		  }
		  
	}
}

/**
 *  
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
    */
