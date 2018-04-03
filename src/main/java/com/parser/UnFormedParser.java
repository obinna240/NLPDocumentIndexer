package com.parser;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.XdmNode;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.ccil.cowan.tagsoup.Parser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.xmlcalabash.core.XProcConfiguration;
import com.xmlcalabash.core.XProcException;
import com.xmlcalabash.core.XProcRuntime;

public class UnFormedParser 
{
	public static XdmNode x() throws IOException, DocumentException, SAXException
	{
		File f = new File("sample_news.txt");
		
		String fs = FileUtils.readFileToString(f, "utf-8");
		 StringReader inputStream = new StringReader(fs);
		    InputSource source = new InputSource(inputStream);
		   Parser parser = new Parser();
		   XProcRuntime runtime = new XProcRuntime(new XProcConfiguration());
		    parser.setEntityResolver(runtime.getResolver());
		    SAXSource saxSource = new SAXSource(parser, source);
		    net.sf.saxon.s9api.DocumentBuilder builder = runtime.getProcessor().newDocumentBuilder();
		    try {
		        XdmNode doc = builder.build(saxSource);
		        return doc;
		    } catch (Exception e) {
		        throw new XProcException(e);
		    }
		
	}
	
	public static void main(String[] args) throws IOException, DocumentException, SAXException
	{
		XdmNode c = x();
		FileUtils.write(new File("rewrittenXml.xml"), c.toString(), "UTF-8");
		//System.out.println(c.toString());
	}
}
