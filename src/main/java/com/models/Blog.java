package com.models;

public class Blog{
	private int id;
	private String title;
	private String desc;
	private String content;
	private int aid;
	
	public Blog(String title, String desc, String content, int aid){
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.content = content;
		this.aid = aid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public Blog(int id, String title, String desc, String content, int aid){
		this(title,desc,content,aid);
		this.id = id;
	}

}