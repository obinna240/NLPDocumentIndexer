package com.parser;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DBDocumentHandler extends DefaultHandler 
{
	private List<DocStructure> docList;
	private List<String> listOfP;
	private DocStructure doc;
	private boolean headline;
	private boolean text;
	private boolean p;
	StringBuffer textContent;
	StringBuilder bodyContent;
	
	@Override
	 public void startElement(String uri, String localName, String qName, 
			 Attributes attributes)
	 {
		if (qName.equalsIgnoreCase("body")) 
		{
			//create a new document
			doc = new DocStructure();
			 //init list of DocumentStructures
            if (CollectionUtils.isEmpty(docList))
            {
            	docList = new LinkedList<DocStructure>();
            }
            
		}
		else if(qName.equalsIgnoreCase("headline"))
		{
			headline = true;
			textContent = null;
			//textContent.setLength(0);
		}
		else if(qName.equalsIgnoreCase("text"))
		{
			text = true;
			bodyContent = new StringBuilder();
			bodyContent.setLength(0);
			if(listOfP == null)
			{
				listOfP = new LinkedList<String>();
			}
		}
		else if(qName.equalsIgnoreCase("p"))
		{
			p = true;
		}
	 }
	 
	@Override
	 public void endElement(String uri, String localName, String qName) throws SAXException {
	        if (qName.equalsIgnoreCase("body"))
	        {
	        	docList.add(doc);
	                
	        }
	        else if(qName.equalsIgnoreCase("text"))
	        {
	        	doc.setBodyp(listOfP);
	        	//listOfP.clear();
	        	listOfP = null;
	        }
	    }
	

	
	 @Override
	 public void characters(char ch[], int start, int length) throws SAXException {

	        if (headline) 
	        {
	           if(textContent ==null)
	           {
	        	   textContent = new StringBuffer().append(ch, start, length);
	           }
	           else
	           {
	        	   textContent.append(ch, start, length);
	           }
	        
	        	doc.setHeading(textContent.toString());
	            headline = false;
	        } 
	        else if (text) {
	        	bodyContent.append(ch, start, length);
	        	//String texts = new String(ch, start, length);
	        	doc.setBody(bodyContent.toString());
	            text = false;
	            
	        } 
	        else if (p) {
	        	String para = new String(ch, start, length);
	        	listOfP.add(para);
	            p = false;
	        } 
	    }

	public List<DocStructure> getDocList() {
		return docList;
	}

	public void setDocList(List<DocStructure> docList) {
		this.docList = docList;
	}
}
