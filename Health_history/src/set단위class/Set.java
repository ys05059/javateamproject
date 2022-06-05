package set단위class;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// set는 baseclass
public abstract class Set {
	
	private LocalTime rest_time;
	private boolean perform_check;
	
	public Set() {
		rest_time = LocalTime.of(0, 2,0);
		perform_check = false;
	}
	
	public Set(LocalTime other_resttime) {
		rest_time = other_resttime;
		perform_check = false;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if(!(o instanceof Set))
			return false;
		Set s = (Set) o;
		return rest_time.equals(s.getRest_time());	
	}

	public LocalTime getRest_time() {
		return rest_time;
	}

	public void setRest_time(LocalTime other_resttime) {
		this.rest_time = other_resttime;
	}
	public void setRest_time(String other_resttime) {
		// format  -> 02:30
		rest_time = LocalTime.parse("00:"+other_resttime,DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	public boolean getPerform_check() {
		return perform_check;
	}


	public void setPerform_check(boolean perform_check) {
		this.perform_check = perform_check;
	}


	public abstract void performed_update();	// 목표를 수행으로 load하는 method
	public abstract void performed_reset();	// 수행으로 reset하는 method
	
}
