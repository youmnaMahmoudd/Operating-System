// Process class to represent a process
public class Process {
    private int PID;
    private String Name;
    private int priorityNumber;

    private double arrivalTime = 0.0;
    private double burstTime = 0.0;
    private double waitingTime = 0.0;
    private double turnAroundTime = 0.0;

    private boolean isActive = false;
    private boolean isArrived = false;
    private boolean isStarted = false;
    private boolean isFinished = false;

    public Process(int PID, String name, int priorityNumber, double arrivalTime, double burstTime, double waitingTime, double turnAroundTime, boolean isActive, boolean isArrived, boolean isStarted, boolean isFinished) {
        this.PID = PID;
        Name = name;
        this.priorityNumber = priorityNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = waitingTime;
        this.turnAroundTime = turnAroundTime;
        this.isActive = isActive;
        this.isArrived = isArrived;
        this.isStarted = isStarted;
        this.isFinished = isFinished;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public double getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(double turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isArrived() {
        return isArrived;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return "Process{" +
                "PID=" + PID +
                ", Name='" + Name + '\'' +
                ", priorityNumber=" + priorityNumber +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", waitingTime=" + waitingTime +
                ", turnAroundTime=" + turnAroundTime +
                ", isActive=" + isActive +
                ", isArrived=" + isArrived +
                ", isStarted=" + isStarted +
                ", isFinished=" + isFinished +
                '}';
    }
}
