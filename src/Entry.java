import processor.*;
/* 
Description: Assignment 2
Author: Michael Hulbert 
Date: 4/2/2022
Bugs: None that I know of
Reflection: I had a bit of trouble understanding the instructions at first.
It was overall easy I completed it the first day it was assigned but none of the outputs matched
so i just left it alone till the day before it was due and realized you updated the outputs
and it was correct. Probobly good to always clarify with the client.
*/ 


public class Entry {
    public static void main(String[] args) throws Exception {
        JobList jobList = new JobList("output3.txt");
        CPU.initilize(jobList, 0);
        while(CPU.process());
        System.out.println("Done!");
    }
}
