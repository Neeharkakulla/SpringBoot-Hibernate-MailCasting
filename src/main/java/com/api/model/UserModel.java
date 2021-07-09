package com.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="mailcastinguser")
public class UserModel {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	@Email
	@NotBlank(message="required field")
	@NotEmpty(message="required field")
	private String email;
	
	@NotBlank(message="required field")
	@NotEmpty(message="required field")
	@Size(min=8 ,message="Please Enter atleast 8 charecters")
	private String password;
	

	@NotBlank(message="required field")
	@NotEmpty(message="required field")
	private String name;
	

	
	
	@Min(value=5000000000L,message="Enter a valid contact")
	@Max(9999999999L)
	private long contact;
	

	@NotBlank(message="required field")
	@NotEmpty(message="required field")
	private String gender;
	

	@NotBlank(message="required field")
	@NotEmpty(message="required field")
	private String country;
	public UserModel(int id, String email, String password, String name, String gender, long contact,
			String country) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.gender = gender;
		this.country = country;
	}
	public UserModel(String email, String password, String name, String gender, long contact, String country) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.contact = contact;
		this.gender = gender;
		this.country = country;
	}
	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", contact="
				+ contact + ", gender=" + gender + ", country=" + country + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	

}
