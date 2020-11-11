package kr.or.bit.model.dto;

public class DTOMember {
	private String id;
	private String pwd;
	private String name;
	private String hp;
	private String cardNum;
	private String address;
	
	public DTOMember() {}
	
	public DTOMember(String id, String pwd, String name, String hp, String cardNum, String address) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.hp = hp;
		this.cardNum = cardNum;
		this.address = address;
	}
	
	public DTOMember(String id, String pwd, String hp, String cardNum, String address) {
		this.id = id;
		this.pwd = pwd;
		this.hp = hp;
		this.cardNum = cardNum;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DTOMember [id=" + id + ", pwd=" + pwd + ", name=" + name + ", hp=" + hp + ", cardNum=" + cardNum
				+ ", address=" + address + "]";
	}
	
}
