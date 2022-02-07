package com.webblog.configuration;

import com.webblog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@EnableScheduling
public class SchedulerConfiguration {

    @Autowired
    PersonService personService;

    @Scheduled(fixedRate = 60000L)
    public void scheduleTaskWithFixedRate() {
        personService.deactivatedPersonScheduler();
    }
}
