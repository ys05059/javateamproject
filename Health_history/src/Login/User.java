package Login;

import java.time.LocalDate;

public class User {
	private String ID;
	private String PW;
	private LocalDate signup_Date;
	private LocalDate withdrawal_Date;
	private float weight;
	private float height;
	private float bodyfat; //Ã¼Áö¹æ·ü
	private float skeletalmuscle; //°ñ°Ý±Ù·®
	
	public User() {
		this.ID = "";
		this.PW = "";
		this.signup_Date = LocalDate.of(2000, 1, 1);
		this.withdrawal_Date = LocalDate.of(2000, 1, 1);
		this.weight = 0;
		this.height = 0;
		this.bodyfat = 0;
		this.skeletalmuscle = 0;
	}
	
	public User(String new_ID, String new_PW) {
		ID = new_ID;
		PW = new_PW;
		this.signup_Date = LocalDate.of(0, 0, 0);
		this.withdrawal_Date = LocalDate.of(0, 0, 0);
		this.weight = 0;
		this.height = 0;
		this.bodyfat = 0;
		this.skeletalmuscle = 0;
	}

	public User(String new_ID, String new_PW, LocalDate new_signup_date, float new_weight, float new_height, float new_bodyfat, float new_skeletalmuscle) {
		ID = new_ID;
		PW = new_PW;
		this.signup_Date = new_signup_date;
		this.withdrawal_Date = LocalDate.of(0, 0, 0);
		this.weight = new_weight;
		this.height = new_height;
		this.bodyfat = new_bodyfat;
		this.skeletalmuscle = new_skeletalmuscle;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	
	public LocalDate getSignup_Date() {
		return signup_Date;
	}

	public void setSignup_Date(LocalDate signup_Date) {
		this.signup_Date = signup_Date;
	}

	public LocalDate getWithdrawal_Date() {
		return withdrawal_Date;
	}

	public void setWithdrawal_Date(LocalDate withdrawal_Date) {
		this.withdrawal_Date = withdrawal_Date;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getBodyfat() {
		return bodyfat;
	}

	public void setBodyfat(float bodyfat) {
		this.bodyfat = bodyfat;
	}

	public float getSkeletalmuscle() {
		return skeletalmuscle;
	}

	public void setSkeletalmuscle(float skeletalmuscle) {
		this.skeletalmuscle = skeletalmuscle;
	}
}
