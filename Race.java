/*
 * Name: Race
 * Author: Sean Filer
 * Purpose: Composed of a list of racers and a course, keeps track of the racers as they progress and provides data on the finishing order as the course is completed.
 */
import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Race {
    private ArrayList<Racer> racers;
    private Course raceCourse;
    private ArrayList<Racer> topThreeRacers = new ArrayList<>();
    private ReentrantLock raceLock = new ReentrantLock();
    private int leaderLapNum;

    public Race(ArrayList<Racer> racers, Course raceCourse){
        this.racers = racers;
        this.raceCourse = raceCourse;
        System.out.println("Race has been set up!\nRacers:");
        for(Racer currentRacer: racers){
            System.out.println("\t"+currentRacer.getName());
        }
    }
    
    public void updateRacerPositions(Racer argRacer){
        if(argRacer.getLapNumber() > leaderLapNum)
    }

    public void startRace(){
        leaderLapNum = 0;
        for(Racer currentRacer: racers){
            currentRacer.prepareToRace(raceCourse, this);
        }
    }

    public void addFinishingRacer(Racer racer){
        if(topThreeFinishers.size()<3){
            topThreeFinishers.add(racer);
        }
    }

    public String raceResults(){
        String results = "Race Results:";
        for(Racer currentRacer: racers){
            results = results + "\nRacer/Time: " + currentRacer.getName() + "/" + currentRacer.getTrueFinishTime();
        }
        int finisher = 1;
        results = results + "\nTop Three finishers:";
        for(Racer currentFinisher: topThreeFinishers){
            results = results + "\n" + finisher + " place Racer/True finish time: " + currentFinisher.getName() + "/" + currentFinisher.getTrueFinishTime();
            finisher++;
        }
        return results;
    }


}
