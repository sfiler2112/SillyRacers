/*
 * Name: SillyRacersMain
 * Author: Sean Filer
 * Purpose: A toy program to play with threads and concurrency.  This class will contain the main method.
 */

import java.util.ArrayList;

public class SillyRacersMain {
    private Course raceCourse;
    private ArrayList<Racer> racers = new ArrayList<>();
    private Race sillyRace;

    public static void main(String args[]){
        SillyRacersMain srm = new SillyRacersMain();
        srm.selectCourse();
        srm.selectRacers();
        try{ //Try block is to ensure the race is completed before trying to display the results.
            srm.createSillyRace();
        } catch (Exception e){
            /*Do nothing*/
        } finally {
            System.out.println(srm.displayRaceResult());
        }
    }

    public void selectCourse(){
        raceCourse = new Course(150, 3);
    }

    public void selectRacers(){
        for(int i = 1; i <= 10; i++){
            racers.add(new Racer("racer" + i));
        }
    }

    public void createSillyRace(){
        sillyRace = new Race(racers, raceCourse);
        sillyRace.startRace();
    }

    public String displayRaceResult(){
        return sillyRace.raceResults();
    }
}
