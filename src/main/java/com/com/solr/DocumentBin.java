package com.com.solr;

import java.util.Date;
import java.util.List;

public class DocumentBin
{
	private String id;
	private String body;
	private String fileLocation;
	private Date creationTime;
	private String creationTimeString;
	private Date lastAccessTime;
	private String lastAccsessTimeString;
	private Date lastModifiedTime;
	private String lastModifiedTimeString;
	private long size; 
	private String fileType; //values include "OTHER", "DIRECTORY", "REGULARFILE", "SYMBOLICLINK"
	private String mediaBaseType;
	private String mediaType;
	private String mediaSubType;
	private String fileParent;
	private String fileName;
	
	
	private List<String> authors;
	private List<String> title;
	private Date lastSaveDate;
	private Integer pageCount;
	
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getMediaBaseType() {
		return mediaBaseType;
	}
	public void setMediaBaseType(String mediaBaseType) {
		this.mediaBaseType = mediaBaseType;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public List<String> getTitle() {
		return title;
	}
	public void setTitle(List<String> title) {
		this.title = title;
	}
	public Date getLastSaveDate() {
		return lastSaveDate;
	}
	public void setLastSaveDate(Date lastSaveDate) {
		this.lastSaveDate = lastSaveDate;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaSubType() {
		return mediaSubType;
	}
	public void setMediaSubType(String mediaSubType) {
		this.mediaSubType = mediaSubType;
	}
	public String getCreationTimeString() {
		return creationTimeString;
	}
	public void setCreationTimeString(String creationTimeString) {
		this.creationTimeString = creationTimeString;
	}
	public String getLastAccsessTimeString() {
		return lastAccsessTimeString;
	}
	public void setLastAccsessTimeString(String lastAccsessTimeString) {
		this.lastAccsessTimeString = lastAccsessTimeString;
	}
	public String getLastModifiedTimeString() {
		return lastModifiedTimeString;
	}
	public void setLastModifiedTimeString(String lastModifiedTimeString) {
		this.lastModifiedTimeString = lastModifiedTimeString;
	}
	public String getFileParent() {
		return fileParent;
	}
	public void setFileParent(String fileParent) {
		this.fileParent = fileParent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	 
}
