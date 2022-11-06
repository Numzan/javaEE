package edu.whu.demo.aspect;
import lombok.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class ApiMatch {
    int count,ExceptionCount;
    long longestTime,shortestTime,averageTime;
    List<Long> times = Collections.synchronizedList(new ArrayList<>());
    public API2(){
        count =0;
        ExceptionCount=0;
    }
    public void setCount(){
        count+=1;
    }
    public void setTimes(long time){
        times.add(time);
        longestTime = calLongestTime();
        shortestTime = calShortestTime();
        averageTime = calAverageTime();
    }
    public long calLongestTime(){
        long time= Collections.max(times);
        return time;
    }
    public long calShortestTime(){
        long time = Collections.min(times);
        return time;
    }
    public long calAverageTime() {
        long sum = 0;
        for (long time : times) {
            sum+=time;
        }
        long result = sum/times.size();
        return result;
    }
}