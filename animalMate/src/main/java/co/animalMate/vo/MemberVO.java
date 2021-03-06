package co.animalMate.vo;

import java.sql.Date;

public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private String nName;
	private String tel;
	private String eDate;
	private String author;
	private int point;
	private String status;
	private String location1;
	private String location2;
	private String email;
	private String pic;
	private int zoomin1;
	private int zoomin2;
	private int code;
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public MemberVO() {

	}

	public MemberVO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNname() {
		return nName;
	}

	public void setNName(String nName) {
		this.nName = nName;
	}

	public String getEdate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocation1() {
		return location1;
	}

	public void setLocation1(String location1) {
		this.location1 = location1;
	}

	public String getLocation2() {
		return location2;
	}

	public void setLocation2(String location2) {
		this.location2 = location2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getZoomin1() {
		return zoomin1;
	}

	public void setZoomin1(int zoomin1) {
		this.zoomin1 = zoomin1;
	}

	public int getZoomin2() {
		return zoomin2;
	}

	public void setZoomin2(int zoomin2) {
		this.zoomin2 = zoomin2;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", nName=" + nName + ", tel=" + tel + ", eDate="
				+ eDate + ", author=" + author + ", point=" + point + ", status=" + status + ", location1=" + location1
				+ ", location2=" + location2 + ", email=" + email + ", pic=" + pic + ", zoomin1=" + zoomin1
				+ ", zoomin2=" + zoomin2 + "]";
	}
}
