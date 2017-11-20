package com.epam.adok.core.timer;

import com.epam.adok.core.annotation.value.Value;
import com.epam.adok.core.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

@Singleton // чтобы не плодить таймеры
@Startup // чтобы создать инстанс бина при запуске приложения
public class NotificationsAutoCleanerTimer {

    private static final Logger log = LoggerFactory.getLogger(NotificationsAutoCleanerTimer.class);

    @Inject
    @Value(key = "one.day.in.milliseconds")
    private Long oneDayInMilliseconds;

    @Inject
    @Value(key = "number.of.weeks.timer")
    private Integer numberOfWeeks;

    @Resource
    private TimerService timerService;

    @EJB
    private NotificationService notificationService;

    @PostConstruct
    public void init() {
        log.info("Entering init() method...");
        Timer timer = timerService.createIntervalTimer(
                new Date(),
                oneDayInMilliseconds,
                new TimerConfig(null, false));
    }

    @Timeout
    public void process(Timer timer) {
        log.info("Entering process() method.");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, -numberOfWeeks);
        Date expiryDate = cal.getTime();

        log.info("Expiry Date : {}", expiryDate);

        notificationService.deleteAllNotificationsByCreatedOnBefore(expiryDate);

        Date nextTimeout = timer.getNextTimeout();

        log.info("Next Timeout Date : {}", nextTimeout);
    }
}
