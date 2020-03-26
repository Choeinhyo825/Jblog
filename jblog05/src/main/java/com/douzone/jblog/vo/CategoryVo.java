package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String desc;
	private String regDate;
	private String id;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CategortVo [no=" + no + ", name=" + name + ", desc=" + desc + ", regDate=" + regDate
				+ ", id=" + id + "]";
	}

}
