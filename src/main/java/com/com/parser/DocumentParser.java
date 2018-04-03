package com.com.parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.com.solr.DocumentBin;
import com.utils.WalkerClassUtils;

/**
 * 
 * @author oonyimadu
 *
 */
public class DocumentParser 
{
	
	String body;
	
	MediaType mediaType;
	DocumentBin documentBin;
	
		
	public DocumentBin getDocumentBin() {
		return documentBin;
	}


	public void setDocumentBin(DocumentBin documentBin) {
		this.documentBin = documentBin;
	}


	public String getBody() 
	{
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}

		
	private void processStream(ContentHandler handler, Metadata metadata, MediaType mediaType)
	{
		documentBin = new DocumentBin();
		
		if(mediaType != null)
		{
			String baseType = mediaType.getBaseType().toString();
			documentBin.setMediaBaseType(baseType);
			String mediaTyp = mediaType.getType();
			documentBin.setMediaType(mediaTyp);
			String mediaSubType = mediaType.getSubtype();
			documentBin.setMediaSubType(mediaSubType);
		}
		
		
		body = handler.toString();
		if(StringUtils.isNotBlank(body))
		{
			documentBin.setBody(body);
		}
		else
		{
			documentBin.setBody("");
		}
		
		
		
		
		String[] names = metadata.names();
		if(ArrayUtils.isNotEmpty(names))
		{
			String[] arr1 = {"dc:creator","meta:author", "Author"};
			List<String> authorFeat = getSpecificMetadataFeatures(arr1, metadata);
			documentBin.setAuthors(authorFeat);
			
			String[] arr2 = {"title","dc:title"};
			List<String> title = getSpecificMetadataFeatures(arr2, metadata);
			documentBin.setTitle(title);
			
			if(StringUtils.isNotBlank(metadata.get("meta:save-date")) )
			{
				
				String saveDate = metadata.get("meta:save-date");

				Date dateSave = WalkerClassUtils.StringToDate(saveDate);
				documentBin.setLastSaveDate(dateSave);
			}
			else if(StringUtils.isNotBlank(metadata.get("Last-Save-Date")))
			{
				String saveDate = metadata.get("Last-Save-Date");
				
				Date dateSave = WalkerClassUtils.StringToDate(saveDate);
				documentBin.setLastSaveDate(dateSave);
			}
			
			if(StringUtils.isNotBlank(metadata.get("Page-Count")) )
			{
				
				String pageCount = metadata.get("Page-Count");

				documentBin.setPageCount(Integer.parseInt(pageCount));
				
			}
			else if(StringUtils.isNotBlank(metadata.get("meta:page-count")))
			{
				String pageCount = metadata.get("meta:page-count");

				documentBin.setPageCount(Integer.parseInt(pageCount));
			}
							
		}
		
	}
	
	private List<String> getSpecificMetadataFeatures(String[] stringFeatures, Metadata metadata)	 
	{
		HashSet<String> set = new HashSet<String>();
		for(String str:stringFeatures)
		{
			List<String> returnList = returnValues(str, metadata);
			
			set.addAll(returnList);
		}
		
		List<String> list = new ArrayList<String>(set);
		return list;
	}
	
	private List<String> returnValues(String property, Metadata metadata)
	{
		List<String> nlist = new ArrayList<String>();
		String[] values = metadata.getValues(property);
		if(ArrayUtils.isNotEmpty(values))
		{
			nlist = Arrays.asList(values);
			
		}
		return nlist;
	}

	public void parseDocument(Path path) throws IOException, SAXException, TikaException
	{
		AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        InputStream stream = TikaInputStream.get(path);
        ContentHandler handler = new BodyContentHandler(-1);
        try 
        {
        	parser.parse(stream, handler, metadata);
        	mediaType = parser.getDetector().detect(stream, metadata);
        	processStream(handler, metadata, mediaType); 
        }
        finally
		{
			stream.close();
		}
	}
	
	
	
	
}
