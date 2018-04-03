package com.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TikaParserForXml 
{
	public static void main(String[] args) throws IOException, SAXException, TikaException
	{
		 //detecting the file type
	    BodyContentHandler handler = new BodyContentHandler(-1);
	    Metadata metadata = new Metadata();
	    FileSystem fs = FileSystems.getDefault();
	    Path path = fs.getPath("rewrittenXml.xml");
	    TikaInputStream inputstream = TikaInputStream.get(path);
	    ParseContext pcontext = new ParseContext();
	    
	    //Xml parser
	    XMLParser xmlparser = new XMLParser(); 
	    xmlparser.parse(inputstream, handler, metadata, pcontext);
	    String x = handler.toString();
	    System.out.println("Contents of the document:" + handler.toString());
	    System.out.println("Metadata of the document:");
	    String[] metadataNames = metadata.names();
	    
	    for(String name : metadataNames) 
	    {
	       System.out.println(name + ": " + metadata.get(name));
	    }
	}
}

