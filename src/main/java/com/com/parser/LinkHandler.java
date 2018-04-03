package com.com.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.ParserDecorator;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;

public class LinkHandler
{
	static final int MAXIMUM_TEXT_CHUNK_SIZE = 1;
	public static List<String> parseToPlainTextChunks() throws IOException, SAXException, TikaException
	{
		 final List<String> chunks = new ArrayList<>();
		  chunks.add("");
		  
		  ContentHandlerDecorator handler = new ContentHandlerDecorator()
		  {
			  @Override
		      public void characters(char[] ch, int start, int length)
		      {
				  String lastChunk = chunks.get(chunks.size() - 1);
		            String thisStr = new String(ch, start, length);
		 
		            if (lastChunk.length() + length > MAXIMUM_TEXT_CHUNK_SIZE) {
		                chunks.add(thisStr);
		            } else {
		                chunks.set(chunks.size() - 1, lastChunk + thisStr);
		            }
		      }
		  };
		  
		  AutoDetectParser parser = new AutoDetectParser();
		    Metadata metadata = new Metadata();
		    
		    FileSystem fs = FileSystems.getDefault();
			Path path = fs.getPath("C:/Users/oonyimadu/PERSONAL/Attachments_2016922.zip");
		    try (InputStream stream = TikaInputStream.get(path))
		    {
		        parser.parse(stream, handler, metadata, new ParseContext());
		        return chunks;
		    }
	}
	
	public static void main(String[] args) throws IOException, SAXException, TikaException
	{
		
		//File f = new File("testMvc");
		
		Metadata met = new Metadata();
		
		
		
		FileSystem fs = FileSystems.getDefault();
		
		
		Path path = fs.getPath("C:/Users/oonyimadu/PERSONAL/Attachments_2016922.zip");
		
		InputStream is = TikaInputStream.get(path);
		
		BodyContentHandler bhandler = new BodyContentHandler();
		//TeeContentHandler handler = new TeeContentHandler(bhandler, )
		
		AutoDetectParser dp = new AutoDetectParser();
		dp.parse(is,bhandler, met);
		
		
	
		for(String str:met.names())
		{
			String[] arr = met.getValues(str);
			System.out.println(str + " ::" + String.join(",", arr));
		}
		
		
		
		
		System.out.println(bhandler.toString());
		
		/**
		ZipFile zip = new ZipFile("C:/Users/oonyimadu/PERSONAL/Attachments_2016922");
		for (ZipEntry entry : Collections.list(zip.entries()))
		{
			System.out.println(entry.getName());
		}
		*/
		
		/**
		for(String str:parseToPlainTextChunks())
		{
			System.out.print(str);
		}
		*/
		
	
		 // Tika tika = new Tika();
		  
		//System.out.println(tika.parseToString(is));
		
	}
}
