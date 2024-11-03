
package Parser;

public class Review {
	private String reviewer; 
	private int rating; 
	private String comment;
	
	public Review () {}
	
	public Review(String reviewer, int rating, String comment) {
		super();
		this.reviewer = reviewer;
		this.rating = rating;
		this.comment = comment;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Review [reviewer=" + reviewer + ", rating=" + rating + ", comment=" + comment + "]";
	}
	
}
