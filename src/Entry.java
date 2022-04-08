import processor.*;

public class Entry {
    public static void main(String[] args) throws Exception {
        JobList jobList = new JobList("output3.txt");
        CPU.initilize(jobList, 0);
        while(CPU.process());
        System.out.println("Done!");
    }
}
