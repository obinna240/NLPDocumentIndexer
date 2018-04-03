package com.com.solr;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrIndexer
{
	private static String url = "http://localhost:8983/solr/wordDocCore";
	private static SolrClient solr = new HttpSolrClient.Builder(url).build();
	
	public void indexDocument(DocumentBin documentBin)
	{
		if(documentBin != null)
		{
			SolrInputDocument sDoc = new SolrInputDocument();
			sDoc.addField("id", documentBin.getId());
			sDoc.addField("body", documentBin.getBody());
			sDoc.addField("fileLocation", documentBin.getFileLocation());
			sDoc.addField("creationTime", documentBin.getCreationTime());
			sDoc.addField("creationTimeString", documentBin.getCreationTimeString());
			sDoc.addField("lastAccessTime", documentBin.getLastAccessTime());
			sDoc.addField("lastAccsessTimeString", documentBin.getLastAccsessTimeString());
			sDoc.addField("lastModifiedTime", documentBin.getLastModifiedTime());
			sDoc.addField("lastModifiedTimeString", documentBin.getLastModifiedTimeString());
			sDoc.addField("size", documentBin.getSize());
			sDoc.addField("fileType", documentBin.getFileType());
			sDoc.addField("mediaBaseType", documentBin.getMediaBaseType());
			sDoc.addField("mediaType", documentBin.getMediaType());
			sDoc.addField("mediaSubType", documentBin.getMediaSubType());
			sDoc.addField("fileParent", documentBin.getFileParent());
			sDoc.addField("lastSaveDate", documentBin.getLastSaveDate());
			sDoc.addField("pageCount", documentBin.getPageCount());
			sDoc.addField("fileName", documentBin.getFileName());
			List<String> authors = documentBin.getAuthors();
			if(CollectionUtils.isNotEmpty(authors))
			{
				for(String auth:authors)
				{
					if(StringUtils.isNotBlank(auth)){
					sDoc.addField("authors", auth);}
				}
				
			}
			List<String> title = documentBin.getTitle();
			if(CollectionUtils.isNotEmpty(title))
			{
				for(String titles:title)
				{
					if(StringUtils.isNotBlank(titles)){
					sDoc.addField("title", titles);}
				}
				
			}
			
			try {
				solr.add(sDoc);
			} catch (SolrServerException | IOException e) {
				System.out.println("Cannot add document "+documentBin.getFileName());
				
				e.printStackTrace();
				
			}
			try {
				solr.commit();
				
			} catch (SolrServerException | IOException e) {
				
				System.out.println("Cannot commit document "+documentBin.getFileName());
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(url);
	}
}
