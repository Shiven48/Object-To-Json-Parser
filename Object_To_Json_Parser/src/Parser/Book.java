package Parser;

import java.util.Arrays;
import java.util.List;

public class Book {
	
	private String title;
	private Author author;
	private int publication;
	private String[] genres;
	private List<Review> reviews;
	
	public Book() {}
	
	public Book(String title, Author author, int publication, String[] genres, List<Review> reviews) {
		super();
		this.title = title;
		this.author = author;
		this.publication = publication;
		this.genres = genres;
		this.reviews = reviews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getPublication() {
		return publication;
	}

	public void setPublication(int publication) {
		this.publication = publication;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", publication=" + publication + ", genres=" + Arrays.toString(genres) + "]";
	}	 
}