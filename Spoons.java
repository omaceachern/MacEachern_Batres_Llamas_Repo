package MacEachern_Batres_Llamas_Repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Spoons {
    /*Contains the data from the file and methods to access it
    make an array list of students in 9,10,11,12, and faculty
    assigns students by grade or if they are faculty
    maps: keys: everyone playing, value: thier target (next on list)
        find as key on map. value will be the eliminated players value
        ex:
        Batres: itzel
        itzel: olivia
        olivia: bono
        bono: batres
     */
    public Spoons(String pathname) throws FileNotFoundException{
        File f = new File (pathname);
        Scanner sc = new Scanner (f);
        String [] header = sc.nextLine().split(",");
        int name_idx=0;
        int grade_idx=1;
        int status_idx=2;

        ArrayList <String> nines = new ArrayList<>();
        ArrayList <String> tens = new ArrayList<>();
        ArrayList <String> elevens = new ArrayList<>();
        ArrayList <String> twelves = new ArrayList<>();
        ArrayList <String> faculty = new ArrayList<>();
        

        while (sc.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));

            String name = line.get(name_idx);
            Integer grade = Integer.parseInt(line.get(grade_idx));

            if (grade.equals(9)) {
                nines.add(name);
            } else if (grade.equals(10)) {
                tens.add(name);
            } else if (grade.equals(11)){
                elevens.add(name);
            } else if (grade.equals(12)){
                twelves.add(name);
            }else{
                faculty.add(name);
            }

        }

        /*assigns students by grade or if they are faculty
    maps: keys: everyone playing, value: thier target (next on list)
    find as key on map. value will be the eliminated players value
    ex:
    Batres: itzel
    itzel: olivia
    olivia: bono
    bono: batres*/

        

    }
}
