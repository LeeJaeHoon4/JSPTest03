package com.nowon.green.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
//DB table 정보를 매핑하기 위한 클래스(Entity + DTO)
public class MemoDTO {
	private int no;
	private String content;
	private String  writer;
	private LocalDateTime cDate;
	private LocalDateTime uDate;

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public LocalDateTime getCdate() {
		return cDate;
	}
	public void setCdate(LocalDateTime cdate) {
		this.cDate = cdate;
	}
	public LocalDateTime getUdate() {
		return uDate;
	}
	public void setUdate(LocalDateTime udate) {
		this.uDate = udate;
	}

}
