package com.com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
















import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.ContentStreamBase;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.handler.extraction.ExtractingParams;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SolrAPIUpdate
{
	/**
	static String url = "http://localhost:8983/solr/wordDocCore";
	
	static SolrClient client = new HttpSolrClient.Builder(url).build();
	
	public void populateIndex()
	{
		SolrInputDocument document = new SolrInputDocument();
		
	}
	*/
	public static void main(String[] args) throws ClientProtocolException, IOException, SolrServerException
	{
		/**
		String restURL = "http://localhost:8983/solr/wordDocCore/schema";
		String jsonString = "{"+"\"add-field\":{"+" \"names\":\"sell-by\","+ "\"types\":\"tdate\","+"\"stored\":true }"+"}";
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity request= new HttpEntity( jsonString, headers );	
		  		
				
		RestTemplate restTemplate = new RestTemplate();
		
		//URI u = restTemplate.postForLocation(restURL, request);
		ResponseEntity obj = restTemplate.postForEntity(restURL, request, null);
		System.out.println(obj);
		Object o = obj.getBody();
		System.out.println(obj.getHeaders());
		
		
		//System.out.println(u);
		*/
		String url = "http://localhost:8983/solr/DocumentIndex";
		
		SolrClient client = new HttpSolrClient.Builder(url).build();
		
		
		ContentStreamUpdateRequest req = new ContentStreamUpdateRequest("/update/extract");
		
		//req.setParam(ExtractingParams.EXTRACT_ONLY, "true");
		
		req.setParam(ExtractingParams.LOWERNAMES,"false");
		
		req.setParam("literal.stream_size", "4");
		req.setParam("literal.id", "4");
		req.setParam("literal.docurl", "https://stackoverflow.com/questions/1264709/convert-inputstream-to-byte-array-in-java");
		
		/**
		req.setParam("literal.documentContent", "d1");
		req.setParam("literal.documentTitle", "d1");
		req.setParam("fmap.content", "documentcontent");
		req.setParam("fmap.last_save_date","");
		req.setParam("fmap.author", "author");
		*/
		
		//req.addFile(new File("Kartik-DiverseEnsemble.pdf"),"pdf");
		
	
		ContentStreamBase.URLStream cc = new ContentStreamBase.URLStream(new URL("https://stackoverflow.com/questions/1264709/convert-inputstream-to-byte-array-in-java")); 
		req.addContentStream(cc);
		
		req.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
		
	    NamedList<Object> result = client.request(req);
	    
	    req.setParam(ExtractingParams.EXTRACT_ONLY, "true");
	    
	    System.out.println("Result: " + result);
	   
	    System.out.println(result.size());
	    System.out.println(result.getVal(0));
	    System.out.println(result.getVal(1));
	    System.out.println(result.getVal(2));
	    
	    
	    
		System.exit(0);
	}
}
