package application;


import java.util.ArrayList;
import java.util.List;

public abstract class Scheduler {
    private final List<Process> rows;
    private final List<Event> timeline;
    private int timeQuantum;

    public Scheduler() {
        rows = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;
    }

    public boolean add(Process row)
    {
        return rows.add(row);
    }

    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }

    public int getTimeQuantum()
    {
        return timeQuantum;
    }

    public double getAverageWaitingTime()
    {
        double avg = 0.0;

        for (Process row : rows)
        {
            avg += row.getWaitingTime();
        }

        return avg / rows.size();
    }

    public double getAverageTurnAroundTime()
    {
        double avg = 0.0;

        for (Process row : rows)
        {
            avg += row.getTurnAroundTime();
        }

        return avg / rows.size();
    }

    public Event getEvent(Process row)
    {
        for (Event event : timeline)
        {
            if (row.getName().equals(event.getProcessName()))
            {
                return event;
            }
        }

        return null;
    }

    public Process getRow(String process)
    {
        for (Process row : rows)
        {
            if (row.getName().equals(process))
            {
                return row;
            }
        }

        return null;
    }

    public List<Process> getRows()
    {
        return rows;
    }

    public List<Event> getTimeline()
    {
        return timeline;
    }

    public abstract void process();
    public abstract void Modify(List<Process> rows);
}