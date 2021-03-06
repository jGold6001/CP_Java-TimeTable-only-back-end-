package org.itstep.dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "STUDENTS")
public class Student{
	
	@Id
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "STUDENT_GROUP", nullable = false)
	private String studentGroup;
	
	public Student() {
	}

	public Student(String login, String password, String firstName, String lastName, String studentGroup) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentGroup = studentGroup;
	}
	
	
}
