package Login;

import java.io.Serializable;
import java.util.ArrayList;

import set´ÜÀ§class.dayRecord;

public class User implements Serializable{
	private String ID;
	private String PW;
	private String Gender;
	private String Nickname;
	private float weight;
	private float height;
	private float bodyfat; //Ã¼Áö¹æ·ü
	private float skeletalmuscle; //°ñ°Ý±Ù·®
	
	public User() {
		this.ID = "";
		this.PW = "";
		this.Gender = "";
		this.Nickname = "";
		this.weight = 0;
		this.height = 0;
		this.bodyfat = 0;
		this.skeletalmuscle = 0;

	}
	
	public User(String new_ID, String new_PW) {
		ID = new_ID;
		PW = new_PW;
		this.Gender = "";
		this.Nickname = "";
		this.weight = 0;
		this.height = 0;
		this.bodyfat = 0;
		this.skeletalmuscle = 0;

	}
	
	public User(String new_ID, String new_PW,String new_Gender, String new_Nickname, float new_weight, float new_height, float new_bodyfat, float new_skeletalmuscle) {
		ID = new_ID;
		PW = new_PW;
		Gender = new_Gender;
		Nickname = new_Nickname;
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

	public void setGender(String Gender) {
		this.Gender = Gender;
	}
	
	public String getGender() {
		return Gender;
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
	
	public String toString() {
		return ID + "/" + PW + "/" + Gender + "/" + Nickname + "/" + weight + "/"
				+ height + "/" + bodyfat + "/" + skeletalmuscle;
	}

}
