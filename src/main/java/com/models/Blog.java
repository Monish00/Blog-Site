package com.models;

public class Blog{
	public int id;
	public String title;
	public String desc;
	public String content;
	public int aid;
	public Blog(int id, String title, String desc, String content, int aid){
		this(title,desc,content,aid);
		this.id = id;
	}
	public Blog(String title, String desc, String content, int aid){
		this.id = id;
		this.title = title;
		this.desc = desc;
		this.content = content;
		this.aid = aid;
	}

}