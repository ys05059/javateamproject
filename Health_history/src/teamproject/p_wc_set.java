package teamproject;

import java.time.Duration;
import java.time.LocalTime;

import set����class.wc_set;

// ���� ����xȽ�� set
public class p_wc_set extends wc_set implements performed {
	private LocalTime start_time;
	private LocalTime end_time;

	public Duration getperform_time() {
		Duration duration = Duration.between(start_time, end_time);
		return duration;
	}
}
