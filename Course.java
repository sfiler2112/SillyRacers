/*
 * Name: Course
 * Author: Sean Filer
 * Purpose: Consists of a length and a total number of laps to complete.  These values are passed to the class when it is initialized.
 *          Possibility to expand later with features like straightaways and curves that modify a racer's speed in that location.
 */
public class Course {
    private int lapLength;
    private int laps;
    private int totalLength;

    public Course(int lapLength, int laps){
        this.lapLength = lapLength;
        this.laps = laps;
        System.out.println("Course created! Length/Laps: " + lapLength + "/" + laps );
        this.totalLength = lapLength * laps;
    }

    public int getTotalLength(){
        return totalLength;
    }
}
