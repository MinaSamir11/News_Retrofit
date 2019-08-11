package com.example.news_retofit;

public class Item {
	  private   Source source;
	  private   String author ;
	  private  String title ;

			public void setTitle(String title) {
				this.title = title;
			}
			public String getTitle() {
				return title;
			}
			public void setAuthor(String author) {
				this.author = author;
			}
			public String getAuthor() {
				return author;
			}
			public void setSource(Source source){
				this.source = source;
			}
			public Source getSource(){
				return source;
			}
	}
