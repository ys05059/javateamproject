package set´ÜÀ§class;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// set´Â baseclass
public class Set {
	
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
}
