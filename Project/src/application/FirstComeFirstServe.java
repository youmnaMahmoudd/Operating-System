package application;

import application.Process;

import java.util.Collections;
import java.util.List;

public class FirstComeFirstServe extends Scheduler
{
    @Override
    public void process()
    {
        Collections.sort(this.getRows(), (Object o1, Object o2) -> {
            if (((Process) o1).getArrivalTime() == ((Process) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((Process) o1).getArrivalTime() < ((Process) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });

        List<Event> timeline = this.getTimeline();

        for (Process row : this.getRows())
        {
            if (timeline.isEmpty())
            {
                timeline.add(new Event(row.getName(), (int) row.getArrivalTime(), (int) (row.getArrivalTime() + row.getBurstTime())));
            }
            else
            {
                Event event = timeline.get(timeline.size() - 1);
                timeline.add(new Event(row.getName(), event.getFinishTime(), (int) (event.getFinishTime() + row.getBurstTime())));
            }
        }

        for (Process row : this.getRows())
        {
            row.setWaitingTime(this.getEvent(row).getStartTime() - row.getArrivalTime());
            row.setTurnAroundTime(row.getWaitingTime() + row.getBurstTime());
        }
    }
}