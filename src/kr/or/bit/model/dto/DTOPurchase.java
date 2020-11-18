package kr.or.bit.model.dto;

import java.util.Date;

public class DTOPurchase {
	private int oNum;
	private String id;
	private String pName;
	private String pSize;
	private String saleTitle;
	private String imageAddr;
	private int pNum;
	private int pPrice;
	private int oAmount;
	private int saleNum;
	private Date oCreatedAt;
	
	public DTOPurchase() {}
	
	
	
	public DTOPurchase(String id, String pName, String pSize, String saleTitle, String imageAddr, int pNum, int pPrice, int oAmount, int saleNum) {
		this.id = id;
		this.pName = pName;
		this.pSize = pSize;
		this.saleTitle = saleTitle;
		this.imageAddr = imageAddr;
		this.pNum = pNum;
		this.pPrice = pPrice;
		this.oAmount = oAmount;
		this.saleNum = saleNum;
	}

	public DTOPurchase(int oNum, String id, int pNum, int oAmount, Date oCreatedAt) {
		this.oNum = oNum;
		this.id = id;
		this.pNum = pNum;
		this.oAmount = oAmount;
		this.oCreatedAt = oCreatedAt;
	}
	
	public DTOPurchase(String id, int pNum, int oAmount) {
		this.id = id;
		this.pNum = pNum;
		this.oAmount = oAmount;
	}

	public int getoNum() {
		return oNum;
	}

	public void setoNum(int oNum) {
		this.oNum = oNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public int getoAmount() {
		return oAmount;
	}

	public void setoAmount(int oAmount) {
		this.oAmount = oAmount;
	}

	public Date getoCreatedAt() {
		return oCreatedAt;
	}

	public void setoCreatedAt(Date oCreatedAt) {
		this.oCreatedAt = oCreatedAt;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpSize() {
		return pSize;
	}

	public void setpSize(String pSize) {
		this.pSize = pSize;
	}

	public String getSaleTitle() {
		return saleTitle;
	}

	public void setSaleTitle(String saleTitle) {
		this.saleTitle = saleTitle;
	}

	public String getImageAddr() {
		return imageAddr;
	}

	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}
	
}
