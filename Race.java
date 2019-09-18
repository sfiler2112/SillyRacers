/*
 * Name: Race
 * Author: Sean Filer
 * Purpose: Composed of a list of racers and a course, keeps track of the racers as they progress and provides data on the finishing order as the course is completed.
 */
import java.util.ArrayList;

public class Race {
    private ArrayList<Racer> racers  = new ArrayList<>();
    private Course raceCourse;

    public Race(ArrayList<Racer> racers, Course raceCourse){
        this.racers = racers;
        this.raceCourse = raceCourse;
        System.out.println("Race has been set up!\nRacers:");
        for(Racer currentRacer: racers){
            System.out.println("\t"+currentRacer.getName());
        }
    }

    public void startRace(){
        for(Racer currentRacer: racers){
            currentRacer.beginRacing(raceCourse);
        }
    }

    public String raceResults(){
        String results = "Race Results:";
        for(Racer currentRacer: racers){
            results = results + "\nRacer/Time: " + currentRacer.getName() + "/" + currentRacer.getTrueFinishTime();
        }
        return results;
    }


}
