package com.com.parser;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.DublinCore;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.DelegatingParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.PhoneExtractingContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;





public class TikaParser extends AbstractParser //DelegatingParser //
{
	public void newParser() throws FileNotFoundException
	{
		InputStream in = new FileInputStream(new File(""));
		try
		{
			//Parser parser = new Parser();
			//parser.parse(input, handler);
		}
		finally
		{
			//in.close();
		}
	}

	@Override
	public Set<MediaType> getSupportedTypes(ParseContext context) {
		// TODO Auto-generated method stub
		return Collections.singleton(MediaType.application(""));
	}

	@Override
	public void parse(InputStream stream, ContentHandler handler,
			Metadata metadata, ParseContext context) throws IOException,
			SAXException, TikaException {
		// TODO Auto-generated method stub
		metadata.set(Metadata.CONTENT_TYPE, "application/csv");
        metadata.set("Hello", "World");

        XHTMLContentHandler xhtml = new XHTMLContentHandler(handler, metadata);
        xhtml.startDocument();
        xhtml.endDocument();
		
	}
	
	//using Tika facade
	//The Tika facade, provides a number of very quick and easy ways to have your 
	//content parsed by Tika, and return the resulting plain text
	public  static String parseToExample() throws IOException, TikaException
	{
		Tika tika = new Tika();
		//InputStream stream = TikaParser.class.getResourceAsStream("/NLP Predictors.docx");
		//String tstring = tika.parseToString(new File("Kartik-DiverseEnsemble.pdf"));
		
		String mediaType = tika.detect(new File("acm_paper.tex"));
		
		
		//prints out the mediaType
		System.out.println(mediaType);
		
		MediaType type = MediaType.parse(mediaType);
		
		System.out.println("type "+type.getType());
		System.out.println("subtype "+type.getSubtype());
		System.out.println("basetype "+type.getBaseType());
		
		Map<String, String> mstring = type.getParameters();
		
		System.out.println("parameters "+mstring);
		
		for(String keys: mstring.keySet())
		{
			System.out.println("key = "+keys+ " "+mstring.get(keys));
		}
		
		/**
		MediaTypeRegistry registry = MediaTypeRegistry.getDefaultRegistry();
		for (MediaType types : registry.getTypes()) {
		Set<MediaType> aliases = registry.getAliases(types);
		System.out.println(types + ", also known as " + aliases);
		}
		*/
	//	System.out.println(tstring);
	  //  return tstring;
	return null;
	}
	
	//Parsing using the Auto-Detect Parser
	//For more control, you can call the Tika Parsers directly. Most likely, you'll 
	//want to start out using the Auto-Detect Parser, which automatically figures out 
	//what kind of content you have, then calls the appropriate parser for you.
	public static void parseExample2() throws IOException, SAXException, TikaException 
	{
	   
	   	     
	    FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath("gas_july.pdf");
		TikaInputStream is = null;
		try
		{
			AutoDetectParser parser = new AutoDetectParser();
			BodyContentHandler handler = new BodyContentHandler();
			Metadata metadata = new Metadata();
			is = TikaInputStream.get(path);
			parser.parse(is, handler, metadata);
			System.out.println(handler.toString());
			
			
		
		}
		finally
		{
			is.close();
		}
	    
	        	   
	}
	
	public static String parseToHTML() throws IOException, SAXException, TikaException 
	{
	    ContentHandler handler = new ToXMLContentHandler();
	 
	    AutoDetectParser parser = new AutoDetectParser();
	    Metadata metadata = new Metadata();         
	    
	    FileSystem fs = FileSystems.getDefault();
		//Path path = fs.getPath("Kartik-DiverseEnsemble.pdf");
	    Path path = fs.getPath("d.xml");
	    parser.parse(TikaInputStream.get(path), handler, metadata);
	    
	    System.out.println(handler.toString());
	    
	    return handler.toString();
	}
	
	//If you just want the body of the xhtml document, without the header, 
	//you can chain together a BodyContentHandler and a ToXMLContentHandler as shown
	public static String parseBodyToHTML() throws IOException, SAXException, TikaException 
	{
	    ContentHandler handler = new ToXMLContentHandler();
	        	
	    AutoDetectParser parser = new AutoDetectParser();
	    
	    Metadata metadata = new Metadata();
	    
	    FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath("NLP Predictors.docx");
	    //parser.parse(TikaInputStream.get(path), handler, metadata);
	    
	  //  String convertedString = handler.toString();
	   // System.out.println(handler.toString());
	    
	  //  System.out.println(convertedString);
	    
	    
	   // InputStream stream = new ByteArrayInputStream(convertedString.getBytes(StandardCharsets.UTF_8));
	    TikaInputStream ts = TikaInputStream.get(path);
	    
	    handler = new BodyContentHandler();
	    parser.parse(ts, handler, metadata);
	    String convertedString = handler.toString();
	    System.out.println(metadata.size());
	    
	    
	    return convertedString;
	    
	}
	
	
	public static Metadata parseBodyToHTML2() throws IOException, SAXException, TikaException 
	{
	    ContentHandler handler = new BodyContentHandler();
	        	 
	    AutoDetectParser parser = new AutoDetectParser();
	    
	    Metadata metadata = new Metadata();
	    
	    FileSystem fs = FileSystems.getDefault();
		//Path path = fs.getPath("03 Glow.mp3");
	   Path path = fs.getPath("Attachments_2016922.zip");
	    parser.parse(TikaInputStream.get(path), handler, metadata);
	    
	    
	    String convertedString = handler.toString();
	   //System.out.println(handler.toString());
	    
	    //System.out.println(convertedString);
	    
	    /**
	    InputStream stream = new ByteArrayInputStream(convertedString.getBytes(StandardCharsets.UTF_8));
	    TikaInputStream ts = TikaInputStream.get(stream);
	    
	    handler = new BodyContentHandler();
	    parser.parse(ts, handler, metadata);
	   */
	   System.out.println(convertedString);
	    
	    String[] names = metadata.names();
	    for(String n:names)
	    {
	    	String val = metadata.get(n);
	    	System.out.println("name "+n +"  value "+val);
	    }
	    
	    return metadata;
	    
	}
	
	public static void process(Path path) throws Exception 
	{
		
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        // The PhoneExtractingContentHandler will examine any characters for phone numbers before passing them
        // to the underlying Handler.
        PhoneExtractingContentHandler handler = new PhoneExtractingContentHandler(new BodyContentHandler(), metadata);
        InputStream stream = TikaInputStream.get(path);
        try 
        {
            parser.parse(stream, handler, metadata, new ParseContext());
        }
        finally
		{
			stream.close();
		}
        System.out.println(metadata);
        
        String[] numbers = metadata.getValues("phonenumbers");
       for(String str:numbers)
       {
    	   System.out.println(str);
       }
    }
	
	/**
	public static void  usePhoneExtractingContentHandler() throws IOException, SAXException, TikaException 
	{
	    ContentHandler handler = new PhoneExtractingContentHandler(null, null);
	        	 
	    AutoDetectParser parser = new AutoDetectParser();
	    
	    Metadata metadata = new Metadata();
	    
	    FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath("Kartik-DiverseEnsemble.pdf");
	    parser.parse(TikaInputStream.get(path), handler, metadata);
	    
	    String convertedString = handler.toString();
	    System.out.println(convertedString);
	    
	  //  System.out.println(convertedString);
	    
	    
	  //  InputStream stream = new ByteArrayInputStream(convertedString.getBytes(StandardCharsets.UTF_8));
	   // TikaInputStream ts = TikaInputStream.get(stream);
	    
	  //  handler = new BodyContentHandler();
	  //  parser.parse(ts, handler, metadata);
	   // System.out.println(metadata);
	    
	   // return metadata;
	    
	}
	*/
	
	//Streaming the plain text in chunks
	//Sometimes, you want to chunk the resulting text up, perhaps to output as 
	//you go minimising memory use, perhaps to output to HDFS files, or any other reason! 
	//With a small custom content handler, you can do that.
	public static List<String> parseToPlainTextChunks() throws IOException, SAXException, TikaException {
	    final int MAXIMUM_TEXT_CHUNK_SIZE = 5;
		final List<String> chunks = new ArrayList();
	    chunks.add("");
	    ContentHandlerDecorator handler = new ContentHandlerDecorator() {
	        @Override
	        public void characters(char[] ch, int start, int length) {
	            String lastChunk = chunks.get(chunks.size() - 1);
	            String thisStr = new String(ch, start, length);
	 
	            if (lastChunk.length() + length > MAXIMUM_TEXT_CHUNK_SIZE) {
	                chunks.add(thisStr+"---------------------");
	            } else {
	                chunks.set(chunks.size() - 1, lastChunk + thisStr+"------------------------");
	            }
	        }
	    };
	 
	    AutoDetectParser parser = new AutoDetectParser();
	    Metadata metadata = new Metadata();
	   
	   
	    FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath("ACM_PAPER_1.docx");
	    parser.parse(TikaInputStream.get(path), handler, metadata);
	    
	    String convertedString = handler.toString();
	   // System.out.println(handler.toString());
	    
	    InputStream stream = new ByteArrayInputStream(convertedString.getBytes(StandardCharsets.UTF_8));
	    TikaInputStream ts = TikaInputStream.get(stream);
	   
	    parser.parse(ts, handler, metadata);
	    System.out.println(chunks.size());
	   System.out.println(chunks);
	    return chunks;
	    
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void parseMyDocuments() throws IOException, SAXException, TikaException 
	{
		 	FileSystem fs = FileSystems.getDefault();
			Path path = fs.getPath("ACM_PAPER_1.docx");
		
			AutoDetectParser parser = new AutoDetectParser();
		    Metadata metadata = new Metadata();
		   
		    LinkContentHandler link = new LinkContentHandler();
		    OutputStream outp = new FileOutputStream(new File("Kartik-DiverseEnsemble.pdf"));
		    InputStream ts = TikaInputStream.get(path);
		    try
		    {
		    	BodyContentHandler bc = new BodyContentHandler(outp);
		    	ContentHandler handler = new TeeContentHandler(bc, link);
			   			
			    parser.parse(ts, bc, metadata);
			    
			   // String str = bc.toString();
			   // String lst = link.toString();
			    //handler.
			    
			    String convertedString = handler.toString();
			    
			   // System.out.println(str);
			   // System.out.println(lst);
			    System.out.println(metadata);
			    System.out.println(metadata.names().length);
		    }
		    finally
		    {
		    	outp.close();
		    	ts.close();
		    }
		   
		    
	}
	
	public static void main(String[] args) throws Exception
	{
		/**
		TikaConfig tika = new TikaConfig();

		File f  =  new File("NLP Predictors.docx");
		System.out.println(f.toString());
		System.out.println(f.getName());
		
		Metadata metadata = new Metadata();
		metadata.set(Metadata.RESOURCE_NAME_KEY, f.toString());
		
		FileSystem fs = FileSystems.getDefault();
		Path path = fs.getPath("NLP Predictors.docx");
		
		
		
		MediaType mimetype = tika.getDetector().detect(TikaInputStream.get(path), metadata);
				
		System.out.println("File " + f + " is " + mimetype);
		  */
		
		//parseToExample();
		//parseExample2();
		parseToHTML();
		//parseBodyToHTML();
		//parseToPlainTextChunks();
		
		//parseBodyToHTML2();
		//usePhoneExtractingContentHandler();
		
		// Path folder = Paths.get("C:/Users/oonyimadu/PERSONAL");
		
		//parseMyDocuments();
		
		/**
		File f = new File("C:/Users/oonyimadu/PERSONAL/tp");
		File[] fa = f.listFiles();
		 for(File ff: fa)
		 {
			
			String fileName = ff.getName();
			System.out.println("file start "+fileName) ;
			process(Paths.get("C:/Users/oonyimadu/PERSONAL/tp/"+fileName));
			System.out.println("END ===== ") ;
		 }
		*/
	}
}
