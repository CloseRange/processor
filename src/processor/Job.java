package processor;

/**
 * A simple Job class that can be processed
 */
public class Job {
    private static final int MIN_PRIORITY = -20;
    private static final int MAX_PRIORITY = 19;
    private int priority; // -20  -  19
    private int length; // 1  -  100
    private int timeWaiting;
    private String name;

    /**
     * Creates a job with a priority length and name
     * @param priority the urgency for this job to be completed. Lower value is higher priority.
     * @param length how many cycles are needed to process this Job
     * @param name the name of this job
     */
    public Job(int priority, int length, String name) {
        this.priority = Utility.clamp(priority, MIN_PRIORITY, MAX_PRIORITY);
        this.length = length;
        this.name = name;
    }
    /**
     * processes a single cycle of this job
     * @return true if the job is complete
     */
    protected boolean process() {
        length--;
        System.out.println(this);
        return length <= 0;
    }

    /**
     * adds to the time waiting variable and changes priority as needed.
     * @param maxWaitTime the max wait time. for increasing prioirty as needed
     */
    protected void increment(int maxWaitTime) {
        if(++timeWaiting > maxWaitTime) {
            timeWaiting = 0;
            priority = Utility.clamp(priority - 1, MIN_PRIORITY, MAX_PRIORITY);
        }
    }
    /**
     * returns the priorty of this job
     * @return the priorty of this job
     */
    protected int getPriority() {
        return priority;
    }
    @Override
    public String toString() {
        return String.format("%s (priority: %d, time remaining: %d)", name, priority, length);
    }
}
