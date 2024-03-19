package in.madhu.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentEnq {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer enqid;
	private String enqName;
	private Long enqPhno;
	private String enqMode;
	private String enqCourse;
	private String enqStatus;
	@CreationTimestamp
	private LocalDate Created_Date;
	private Integer cid;
	public Integer getEnqid() {
		return enqid;
	}
	public void setEnqid(Integer enqid) {
		this.enqid = enqid;
	}
	public String getEnqName() {
		return enqName;
	}
	public void setEnqName(String enqName) {
		this.enqName = enqName;
	}
	public Long getEnqPhno() {
		return enqPhno;
	}
	public void setEnqPhno(Long enqPhno) {
		this.enqPhno = enqPhno;
	}
	public String getEnqMode() {
		return enqMode;
	}
	public void setEnqMode(String enqMode) {
		this.enqMode = enqMode;
	}
	public String getEnqCourse() {
		return enqCourse;
	}
	public void setEnqCourse(String enqCourse) {
		this.enqCourse = enqCourse;
	}
	public String getEnqStatus() {
		return enqStatus;
	}
	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}
	public LocalDate geteCreated_Date() {
		return Created_Date;
	}
	public void seteCreated_Date(LocalDate eCreated_Date) {
		this.Created_Date = eCreated_Date;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	

}
