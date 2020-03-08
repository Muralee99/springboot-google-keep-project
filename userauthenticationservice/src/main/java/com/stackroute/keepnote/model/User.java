package com.stackroute.keepnote.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */
@Entity
@Table(name="userkeep")
public class User {

    /*
	 * This class should have five fields (userId,firstName,lastName,
	 * userPassword,userRole,userAddedDate). Out of these five fields, the field
	 * userId should be the primary key. This class should also contain the getters
	 * and setters for the fields, along with the no-arg , parameterized constructor
	 * and toString method.The value of userAddedDate should not be accepted from
	 * the user but should be always initialized with the system date
	 */

    @Id
    @Column(name = "user_id",unique=true,columnDefinition="VARCHAR(20)")
    private String userName;

   /* @Id
    @Column(name = "user_id")
    private String userId;*/

    @Column(name="user_password")
    private String userPassword;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_role")
    private String userRole;

    @Column(name="user_added_date")
    private Date userAddedDate;
    
    @Column(name="user_mobile")
    private String userMobile;
    
    @Column(name="user_email")
    private String userEmail;

    public User()
    {

    }
    
    public User(String userName, String userPassword, String firstName,
			String lastName, String userRole, Date userAddedDate,
			String userMobile, String userEmail) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.userAddedDate = userAddedDate;
		this.userMobile = userMobile;
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getUserAddedDate() {
        return userAddedDate;
    }

    public void setUserAddedDate(Date userAddedDate) {
        this.userAddedDate = userAddedDate;
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userAddedDate=" + userAddedDate +
                '}';
    }
}
