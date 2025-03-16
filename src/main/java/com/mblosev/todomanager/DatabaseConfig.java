package com.mblosev.todomanager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mblosev.todomanager.Entities.TodoTask;
import com.mblosev.todomanager.Repositories.TodoTaskRepository;

@Configuration
public class DatabaseConfig {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    CommandLineRunner initDatabase(TodoTaskRepository repository) {
        return _ -> {
            Calendar calendar = new GregorianCalendar(2024, Calendar.JULY, 14);

            log.info("Adding task: " + repository.save(new TodoTask(0, calendar.getTime(), "Test Todo #1", false)));

            calendar.add(Calendar.MONTH, 3);

            log.info("Adding task: " + repository.save(new TodoTask(0, calendar.getTime(), "Test Todo #2", false)));

            calendar.set(Calendar.YEAR, 2025);
            calendar.set(Calendar.MONTH, Calendar.FEBRUARY);

            log.info("Adding task: " + repository.save(new TodoTask(0, calendar.getTime(), "Test Todo #3", false)));

            calendar.set(Calendar.MONTH, Calendar.MARCH);

            log.info("Adding task: " + repository.save(new TodoTask(0, calendar.getTime(), "Test Todo #4", false)));
        };
    }
}
