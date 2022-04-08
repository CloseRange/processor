package processor;

import java.util.HashMap;

import net.datastructures.HeapPriorityQueue;

/**
 * A static class used to process cpu job cycles. Storse all jobs and processes them
 */
public class CPU {
    private static int maxWaitTime = 2;

    private static HeapPriorityQueue<Integer, Job> jobs = new HeapPriorityQueue<>();
    private static HashMap<Job, ?> allJobs = new HashMap<>();
    private static JobList jobList = null;

    /**
     * applies data to the cpu adding a list of jobs and the max wait time for each job
     * @param jobList the JobList that need to be processed
     * @param maxWaitTime the max wait time for jobs
     */
    public static void initilize(JobList jobList, int maxWaitTime) {
        CPU.jobList = jobList;
        CPU.maxWaitTime = maxWaitTime;
    }
    /**
     * removes a job from the CPU
     * @return the removed job
     */
    private static Job removeNextJob() {
        Job job = jobs.removeMin().getValue();
        allJobs.remove(job);
        return job;
    }
    /**
     * adds a job to the CPU
     * @param job the job to add
     */
    public static void addJob(Job job) {
        allJobs.put(job, null);
        jobs.insert(job.getPriority(), job);
    }
    /**
     * processes a single CPU cycle
     * @return true the CPU still has jobs to process
     */
    public static boolean process() {
        if(jobs.size() > 0) {
            Job job = removeNextJob();
            if(!job.process()) {
                addJob(job);
            }

            for(Job j : allJobs.keySet()) {
                if(j == job) continue;
                j.increment(maxWaitTime);
            }
        }

        if(jobList != null) {
            Job next = jobList.nextJob();
            if(next != null) {
                addJob(next);
            }
        }
        
        return jobs.size() != 0;
    }
}
