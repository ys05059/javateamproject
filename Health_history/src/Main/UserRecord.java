package Main;

import java.io.Serializable;
import java.util.ArrayList;

import set¥‹¿ßclass.dayRecord;

public class UserRecord implements Serializable {
	private String ID;
	private ArrayList <dayRecord> UserWorkInfo;
	
	public UserRecord() {
		this.ID = "";
		this.UserWorkInfo = new ArrayList<dayRecord>();
	}
	
	public UserRecord(String ID, ArrayList <dayRecord> UserWorkInfo) {
		this.ID = ID;
		this.UserWorkInfo = UserWorkInfo;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<dayRecord> getUserWorkInfo() {
		return UserWorkInfo;
	}

	public void setUserWorkInfo(ArrayList<dayRecord> userWorkInfo) {
		UserWorkInfo = userWorkInfo;
	}
	
	public String toString() {
		for (int i = 0; i < UserWorkInfo.size(); i++) {
			UserWorkInfo.get(i).printallexr_ary();
		}
		return ID; 
	}
}
