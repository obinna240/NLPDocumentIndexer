package com.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.SAXException;

public class XML_Parser
{
	 public static void main(String[] args) 
	 {
		    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		    try {
		        javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
		        DBDocumentHandler2 handler = new DBDocumentHandler2();
		        saxParser.parse(new File("rewrittenXml.xml"), handler);
		        
		        List<DocStructure> docList = handler.getDocList();
		       
		        for(DocStructure dT : docList)
		        {
		        	System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		            System.out.println(dT.getHeading());
		            String bd = dT.getBody();
		            bd = StringUtils.trimToEmpty(StringUtils.normalizeSpace(bd));
		            System.out.println(bd);
		            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		        }
		    } 
		    catch (ParserConfigurationException | SAXException | IOException e) {
		        e.printStackTrace();
		    }
	 }
}
