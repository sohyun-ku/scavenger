package sample.app;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/**
 * @author olle.hallin@crisp.se
 */
@SuppressWarnings("ALL")
@Log
@SpringBootApplication
@EnableAspectJAutoProxy
@RequiredArgsConstructor
@Controller
public class SampleApp {
    private final int dummy = 17;
    private final SampleService1 sampleService1;

    public static void main(String[] args) throws InterruptedException {
        log.info(SampleApp.class.getSimpleName() + " starts on Java " + System.getProperty("java.version"));
        SpringApplication.run(SampleApp.class, args);
        log.info("Exit");
    }

    public int add(int p1, int p2) {
        return privateAdd(p1, p2);
    }

    private int privateAdd(int p1, int p2) {
        return p1 + p2;
    }

    public static void intentionallySlowMethod() { // To prevent sampleApp from shutting down too quickly
        double result = 0;
        for (int i = 0; i < 1_00_000_000; i++) {
            result += Math.sqrt(i);
        }
        log.info("Computation result: " + result);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("2+2=" + add(2, 2));
        sampleService1.doSomething(1);
        intentionallySlowMethod();
    }
}
