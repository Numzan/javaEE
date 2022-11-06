package edu.whu.demo.aspect;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
@Aspect
@Component
public class ApiGet {
    @Before("controllerPointcut()")
    public void beforeCotrollers(JoinPoint jp) {
        System.out.println("----------------------------");
        addToMap(jp.getSig().getName());
    }

    @Pointcut("execution(* edu.whu.demo.controller.*.*(..))")
    public void controllerPointcut() {
    }

    public boolean addToMap(String name) {
        for (Map.Entry<String, ApiMatch> entry : ApiGet.entrySet()) {
            if (entry.getKey().equals(name)) {
                ApiMatch ApiMatch = entry.getValue();
                entry.setValue(ApiMatch);
                ApiMatch.setCount();
                System.out.println(entry.getKey() + " Count: " + entry.getValue().getCount());
                return true;
            }
        }
        ApiMatch newApiMatch = new ApiMatch();
        newApiMatch.setCount();
        System.out.println(name + " Count: " + newApiMatch.getCount());
        return false;
    }

    public void Time(String name, long time) {
        for (Map.Entry<String, ApiMatch> entry : ApiGet.entrySet()) {
            if (entry.getKey().equals(name)) {
                ApiMatch ApiMatch = entry.getValue();
                ApiMatch.setTimes(time);
                entry.setValue(ApiMatch);
                System.out.println(name + entry.getValue().getTimes());
            }
        }
    }
}

