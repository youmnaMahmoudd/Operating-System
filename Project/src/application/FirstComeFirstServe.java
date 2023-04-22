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
    public void Modify(List<Process> data){
        List <Process> ans = new ArrayList<>();
        int id=1;
        for(int i=0;i<data.size();i++) {
            Process p = data.get(i);
            System.out.println(p.arrivaltime);
            int startTime = p.getStartTime();
            int dur = (int)p.getBurstTime();
            System.out.println(dur);

            for(int j = startTime;j<(dur+startTime);j++) {
                Process g = new Process(id,p.getArrivalTime(), p.getBurstTime(), p.getColor());
                System.out.println("g"+g.ID);
                g.setStartTime(j);
                g.setBurstTime(1);
                ans.add(g);
            }
            id++;
        }
        this.getRows().clear();
        for(int i=0;i< ans.size();i++){
            this.getRows().add(ans.get(i));
        }


    }
}