package com.helper;

import java.util.List;

import com.models.Blog;

public class currentView {
	private static List<Blog> blog;

	public static List<Blog> getBlog() {
		return blog;
	}
	
	public static Blog getBlogById(int id) {
		 Blog bl = null;
		 if(blog != null)
	        for (Blog b : blog) {
	            if (b.getId() == id) {
	                bl = b;
	                break; 	            }
	        }
	        return bl;
	}

	public static void setBlog(List<Blog> blog) {
		currentView.blog = blog;
	}
}
