package com.zee.utl.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import com.zee.utl.CleanJunckData;

@Component
@ConditionalOnClass(CleanJunckData.class)
public class CleanJunckDataTask {
	private static final Logger log = LoggerFactory.getLogger(CleanJunckDataTask.class);

	@Autowired
	@Qualifier("cleanJunckData")
	private CleanJunckData cleanJunckData;

	@Value("${mangoTaskSwitch.ecTask}")
	private Boolean ecTask;// 定时任务开关

	//@Scheduled(fixedRate = 1000*60*600)
	public void executeTask() throws Exception {
		if (ecTask) {
			cleanJunckData.cleanParentTable();
		}
	}


}