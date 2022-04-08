package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A simple list of jobs
 */
public class JobList {
    private ArrayList<Job> jobs;
    /**
     * Constructor a JobList using a file name as input
     * @param filename the file name
     * @throws FileNotFoundException if the file doesn't exist
     */
    public JobList(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        jobs = new ArrayList<>();
        while(scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            if(line.contains("no new job this slice")) {
                jobs.add(null);
            } else {
                String[] words = line.split(" ");
                int wordLen = words.length;
                if(wordLen < 9) {
                    continue;
                }
                if(!words[0].equals("add")
                    || !words[1].equals("job")
                    || !words[wordLen-2].equals("priority")
                    || !words[wordLen-3].equals("and")
                    || !words[wordLen-5].equals("length")
                    || !words[wordLen-6].equals("with")) {
                        continue;
                }
                try {
                    int priority = Integer.parseInt(words[wordLen - 1]);
                    int length = Integer.parseInt(words[wordLen - 4]);
                    String name = "";
                    for(int i=2; i<=wordLen-7; i++) {
                        if(i != 2) name += " ";
                        name += words[i];
                    }
                    jobs.add(new Job(priority, length, name));
                } catch(Exception e) {}

            }
        }
    }
    /**
     * gets the next job in the list, removing it in the process.
     * @return the next job
     */
    protected Job nextJob() {
        if(jobs.size() == 0) return null;
        return jobs.remove(0);
    }
    /**
     * returns the number of jobs remaining in the list.
     * @return the number of jobs remaining in the list.
     */
    public int size() {
        return jobs.size();
    }
}
