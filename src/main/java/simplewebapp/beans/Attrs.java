package simplewebapp.beans;

import javax.annotation.Generated;

import com.owlike.genson.annotation.JsonProperty;

public class Attrs {

	private String name;

	private String degree;

	private String date;

	private String age;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Attrs() {
	}

	/**
	 *
	 * @param date
	 * @param name
	 * @param degree
	 * @param age
	 */
	public Attrs(@JsonProperty("name") String name, @JsonProperty("degree") String degree,
			@JsonProperty("date") String date, @JsonProperty("age") String age) {
		this.name = name;
		this.degree = degree;
		this.date = date;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}