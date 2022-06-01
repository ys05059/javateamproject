package set단위class;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// set는 baseclass
public abstract class Set {
	
	private LocalTime rest_time;
	
	public Set() {
		rest_time = LocalTime.of(0, 2,0);
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
	
	public abstract void performed_update();	// 목표를 수행으로 load하는 method
}
