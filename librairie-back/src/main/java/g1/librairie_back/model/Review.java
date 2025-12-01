package g1.librairie_back.model;

import java.time.LocalDate;

public class Review {
	
	private Integer id;
	
	private String review;
	
	private int note;
	
	private LocalDate dateReview;

	/*
	@ManyToOne
	@JoinColumn(name="client",nullable = false)
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="article",nullable = false)
	private Article article;
	*/
	
	public Review() {}
	
	public Review(String review, int note, LocalDate dateReview) {
		super();
		this.review = review;
		this.note = note;
		this.dateReview = dateReview;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public LocalDate getDateReview() {
		return dateReview;
	}

	public void setDateReview(LocalDate dateReview) {
		this.dateReview = dateReview;
	}

	@Override
	public String toString() {
		return "Panier [id=" + id + ", review=" + review + ", note=" + note + ", dateReview=" + dateReview + "]";
	}
	
}
