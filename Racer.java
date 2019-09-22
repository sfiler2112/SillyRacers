/*
 * Name: Racer
 * Author: Sean Filer
 * Purpose: Uses the interface runnable to create a thread that will track the racer's progress on the course.  Fields
 *          maxSpeed and speedRange are determined randomly when the Racer is initialized.
 */

import java.util.ArrayList;
import java.util.Random;
import java.lang.Runnable;

public class Racer implements Runnable{
    private String name;
    private double maxSpeed;  // meters per second, determines the maximum value the current speed can be
    private int speedRange; // determines how low the speed values can fall to from the max speed for any given second to provide the currentSpeed.
    private double currentSpeed; // meters per second, the current speed the racer is traveling. updates every second.
    private int wholeSecondCount; // tracks the number of seconds the racer has been traversing the course.
    private double trueFinishTime; // the actual time the racer would have passed the finish line in their final second.
    private double overshot; // if the racer passes the finish line, this is the extra distance they travelled.  Used to determine their true finish time from the wholeSecondCount.
    private double distanceCovered;
    private ArrayList<Double> recordOfSpeeds = new ArrayList<>(); // a list that contains the racers speed for each second of the race
    private int lapNumber; // used to keep track of which racer is in the lead of a race when updating relative positions.
    private Course raceCourse;
    private Race race;

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
    
    public void run(){
        beginRacing();
    }

    public String getName(){
        return name;
    }
    
    /* 
     * The following method's purpose is to initialize the Racer's fields that are required to use the run() method.  
    */
    public void prepareToRace(Course raceCourse, Race race){ 
        distanceCovered = 0.0;
        wholeSecondCount = 0;
        lapNumber = 0;
        this.race = race;
        this.raceCourse = raceCourse;
        System.out.println(name + " ready to race on " + raceCourse.getName());
    }

    
    private void beginRacing(){ // This method needs to be modified to support use as a runnable thread
        
        /*
         * The following while-loop measures how far the racer has travelled after each second.  Each loop
         * iteration will have a chance for the current speed to change, affecting the distance traveled for that second.
         */
        int lapDistance = 0;
        int halfSecondCount = 0; // counts every half second
        currentSpeed = (maxSpeed - (speedRange * new Random().nextDouble()));
        while(distanceCovered <= raceCourse.getTotalLength()){
            while(lapDistance < raceCourse.getLapLength()){
                if(halfSecondCount == 2){
                    halfSecondCount = 0;
                    currentSpeed = (maxSpeed - (speedRange * new Random().nextDouble()));
                    recordOfSpeeds.add(currentSpeed);
                    wholeSecondCount++;
                }
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e){
                    /* Do nothing */
                }
                distanceCovered += currentSpeed;
                halfSecondCount++;
            }
            lapDistance = distanceCovered - raceCourse.getTotalLength();
        }
        overshot = distanceCovered - raceCourse.getTotalLength();
        System.out.println(name + " finished.\n\tOvershot/Whole second count: " + overshot + "/" + wholeSecondCount);
        calculateTrueFinishTime();
        race.addFinishingRacer(this);
    }

    public void calculateTrueFinishTime(){
        double afterFinishLineTime = overshot / currentSpeed;

        trueFinishTime = wholeSecondCount - afterFinishLineTime;
    }

    public double getTrueFinishTime(){
        return trueFinishTime;
    }
}
