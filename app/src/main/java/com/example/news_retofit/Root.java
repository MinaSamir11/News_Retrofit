package com.example.news_retofit;
import java.util.List;

public class Root{
    private List<Item> articles;
    public void setArticles(List<Item> articles){
        this.articles = articles;
    }
    public List<Item> getArticles(){
        return articles;
    }
}