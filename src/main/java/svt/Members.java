package svt;

import java.io.Serializable;
import javax.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Members
 *
 */
@Entity
@Table(name="MEMBERS")
public class Members implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MEMBERID")
	private String memberId;
	
//	@NotNull
//	@Size(min = 2)
//	@Pattern(regexp = "^[A-Z0-9]*$", flags = Pattern.Flag.CASE_INSENSITIVE,
//        message = "FirstName can only contain letters and numbers")
	@Column(name="FIRSTNAME")
	private String firstName;
	
//	@NotNull
//	@Size(min = 2)
//	@Pattern(regexp = "^[A-Z0-9#]*$", flags = Pattern.Flag.CASE_INSENSITIVE,
//        message = "LastName can only contain letters and numbers and # for Jibe")
	@Column(name="LASTNAME")
	private String lastName;
	
//	@NotNull
//	@CCNum
	@Column(name="CCNO")
	private String ccNo;
	
//	@Size(min=3, max=3)
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="DATEJOINED")
	private java.util.Date dateJoined;
	
//	@NotNull
	@Column(name="VALIDATEMEMID")
	private String validateMemID;
	
//	@Min(value=0)
	@Column(name="GASPOINTS")
	private int gasPoints;
	/**
	 * 
	 */
	public Members() {
		super();
	}
	public Members(String id) 
	{
		this.memberId = id;
	}
	public Members(String id, String fname, String lname, String ccNo, String loc, java.sql.Date datejoined, String valMemID, int points)
	{
		this.memberId = id;
		this.firstName = fname;
		this.lastName = lname;
		this.ccNo = ccNo;
		this.location = loc;
		this.dateJoined = datejoined;
		this.validateMemID = valMemID;
		this.gasPoints = points;
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the ccNo
	 */
	public String getCcNo() {
		return ccNo;
	}
	/**
	 * @param ccNo the ccNo to set
	 */
	public void setCcNo(String ccNo) {
		this.ccNo = ccNo;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the dateJoined
	 */
	public java.util.Date getDateJoined() {
		return dateJoined;
	}
	/**
	 * @param dateJoined the dateJoined to set
	 */
	public void setDateJoined(java.util.Date dateJoined) {
		this.dateJoined = dateJoined;
	}
	/**
	 * @return the validateMemID
	 */
	public String getValidateMemID() {
		return validateMemID;
	}
	/**
	 * @param validateMemID the validateMemID to set
	 */
	public void setValidateMemID(String validateMemID) {
		this.validateMemID = validateMemID;
	}
	/**
	 * @return the gasPoints
	 */
	public int getGasPoints() {
		return gasPoints;
	}
	/**
	 * @param gasPoints the gasPoints to set
	 */
	public void setGasPoints(int gasPoints) {
		this.gasPoints = gasPoints;
	}
   
}
