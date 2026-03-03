package library.models;

import java.util.List;
import java.util.ArrayList;

public class Book {
    private String author;
    private String title;
    private String genre;
    private String shortDescription;
    private int publishedYear;
    private List<String> keywords;
    private double rating;
    private String id;

    public Book(String author, String title, String genre, String shortDescription, int publishedYear, List<String> keywords, double rating, String id){

            if(publishedYear > 2026){
                throw new IllegalArgumentException("Year cannot be in the future!");
    }

        this.author = author;
        this.title = title;
        this.genre = genre;
        this.shortDescription = shortDescription;
        this.publishedYear = publishedYear;
        this.keywords = new ArrayList<>(keywords);
        this.rating = rating;
        this.id = id;

}
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public double getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }
}