/*
 * Name: Racer
 * Author: Sean Filer
 * Purpose: Uses the interface runnable to create a thread that will track the racer's progress on the course.  Fields
 *          maxSpeed and speedRange are determined randomly when the Racer is initialized.
 */

import java.util.ArrayList;
import java.util.Random;

public class Racer {
    private String name;
    private double maxSpeed;  // meters per second, determines the maximum value the current speed can be
    private int speedRange; // determines how low the speed values can fall to from the max speed for any given second to provide the currentSpeed.
    private double currentSpeed; // meters per second, the current speed the racer is traveling. updates every second.
    private int wholeSecondCount; // tracks the number of seconds the racer has been traversing the course.
    private double trueFinishTime; // the actual time the racer would have passed the finish line in their final second.
    private double overshot; // if the racer passes the finish line, this is the extra distance they travelled.  Used to determine their true finish time from the wholeSecondCount.
    private double distanceCovered;
    private ArrayList<Double> recordOfSpeeds = new ArrayList<>(); // a list that contains the racers speed for each second of the race

    public Racer(String racerName){
        Random rg = new Random();
        name = racerName;
        maxSpeed = (rg.nextDouble() * 20) + 5; // Highest possible max speed is 25 meters per second, lowest possible max speed is 5.
        if(maxSpeed > 15){
            speedRange = rg.nextInt(10);
        } else {
            speedRange = rg.nextInt(5);
        }

        System.out.println("Racer created! Name/Max Speed/Speed Range: " + name + "/" + maxSpeed + "/" + speedRange);
    }

    public String getName(){
        return name;
    }

    public void beginRacing(Course raceCourse){
        distanceCovered = 0.0;
        wholeSecondCount = 0;
        /*
         * The following while-loop measures how far the racer has travelled after each second.  Each loop
         * iteration will have a chance for the current speed to change, affecting the distance traveled for that second.
         */
        while(distanceCovered <= raceCourse.getTotalLength()){
            currentSpeed = (maxSpeed - (speedRange * new Random().nextDouble()));
            distanceCovered += currentSpeed;
            recordOfSpeeds.add(currentSpeed);
            wholeSecondCount++;
        }
        overshot = distanceCovered - raceCourse.getTotalLength();
        System.out.println(name + " finished.\n\tOvershot/Whole second count: " + overshot + "/" + wholeSecondCount);
        calculateTrueFinishTime();
    }

    public void calculateTrueFinishTime(){
        double afterFinishLineTime = overshot / currentSpeed;

        trueFinishTime = wholeSecondCount - afterFinishLineTime;
    }

    public double getTrueFinishTime(){
        return trueFinishTime;
    }
}
