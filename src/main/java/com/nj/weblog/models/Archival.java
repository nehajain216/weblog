package com.nj.weblog.models;

public class Archival 
{
	private Long count;
	private Integer month;
	private Integer year;
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Archival(Long count, Integer month, Integer year) {
		super();
		this.count = count;
		this.month = month;
		this.year = year;
	}
	@Override
	public String toString() {
		return "Archival [count=" + count + ", month=" + month + ", year=" + year + "]";
	}
	
}
