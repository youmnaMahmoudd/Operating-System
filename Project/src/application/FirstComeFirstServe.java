package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FirstComeFirstServe extends Scheduler
{
    @Override
    public void process() {
        List<Process> rows = this.getRows();
        Collections.sort(rows, Comparator.comparingDouble(Process::getArrivalTime));

        List<Event> timeline = new ArrayList<>();
        double currentTime = 0.0;

        for (Process row : rows) {
            double startTime = Math.max(currentTime, row.getArrivalTime());
            double finishTime = startTime + row.getBurstTime();
            timeline.add(new Event(row.getName(), (int) startTime, (int) finishTime));
            currentTime = finishTime;
        }

        this.getTimeline().addAll(timeline);

        for (Process row : rows) {
            Event event = this.getEvent(row);
            row.setWaitingTime(event.getStartTime() - row.getArrivalTime());
            row.setTurnAroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}