package com.nmid.student.domain;

public class Student {
	private String name;
	private String id;
	private String age;
	private String stdNumber;
	private String major;
	private String gender;
	private String computerScore;
	private String mathScore;
	private String englishScore;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getStdNumber() {
		return stdNumber;
	}
	public void setStdNumber(String stdNumber) {
		this.stdNumber = stdNumber;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getComputerScore() {
		return computerScore;
	}

	public void setComputerScore(String computerScore) {
		this.computerScore = computerScore;
	}

	public String getMathScore() {
		return mathScore;
	}

	public void setMathScore(String mathScore) {
		this.mathScore = mathScore;
	}

	public String getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(String  englishScore) {
		this.englishScore = englishScore;
	}
}
