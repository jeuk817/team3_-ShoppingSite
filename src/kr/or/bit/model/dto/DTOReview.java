package kr.or.bit.model.dto;

import java.util.Date;

public class DTOReview {
	private int revNum;
	private int saleNum;
	private String id;
	private String revContent;
	private int revStars;
	private Date revCreatedAt;
	
	public DTOReview() {}
	
	public DTOReview(int saleNum, String id, String revContent, int revStars) {
		this.saleNum = saleNum;
		this.id = id;
		this.revContent = revContent;
		this.revStars = revStars;
	}
	
	public DTOReview(int revNum, int saleNum, String id, String revContent, int revStars, Date revCreatedAt) {
		this.revNum = revNum;
		this.saleNum = saleNum;
		this.id = id;
		this.revContent = revContent;
		this.revStars = revStars;
		this.revCreatedAt = revCreatedAt;
	}

	public int getRevNum() {
		return revNum;
	}

	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRevContent() {
		return revContent;
	}

	public void setRevContent(String revContent) {
		this.revContent = revContent;
	}

	public int getRevStars() {
		return revStars;
	}

	public void setRevStars(int revStars) {
		this.revStars = revStars;
	}

	public Date getRevCreatedAt() {
		return revCreatedAt;
	}

	public void setRevCreatedAt(Date revCreatedAt) {
		this.revCreatedAt = revCreatedAt;
	}
	
}
