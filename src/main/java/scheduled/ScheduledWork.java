package scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledWork {
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void timelogger() {
		Calendar calender= Calendar.getInstance();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.debug("debug log={}",dateFormat.format(calender.getTime()));
	}
	
}
