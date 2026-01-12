package MacEachern_Batres_Llamas_Repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<String, Integer> statusMap;  // Tracks eliminated status

    private ArrayList<String> nines;
    private ArrayList<String> tens;
    private ArrayList<String> elevens;
    private ArrayList<String> twelves;
    private ArrayList<String> faculty;

    private Map<String, String> targets9;
    private Map<String, String> targets10;
    private Map<String, String> targets11;
    private Map<String, String> targets12;
    private Map<String, String> targetsFaculty;

    public Spoons(String pathname) throws FileNotFoundException{
        File f = new File (pathname);
        Scanner sc = new Scanner (f);
        String [] header = sc.nextLine().split(",");
        int name_idx=0;
        int grade_idx=1;
        int status_idx=2;

        // ArrayList <String> nines = new ArrayList<>();
        // ArrayList <String> tens = new ArrayList<>();
        // ArrayList <String> elevens = new ArrayList<>();
        // ArrayList <String> twelves = new ArrayList<>();
        // ArrayList <String> faculty = new ArrayList<>();
        
        statusMap = new HashMap<>();
        nines = new ArrayList<>();
        tens = new ArrayList<>();
        elevens = new ArrayList<>();
        twelves = new ArrayList<>();
        faculty = new ArrayList<>();


        while (sc.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
            String name = line.get(name_idx);
            Integer grade = Integer.parseInt(line.get(grade_idx));
            Integer status=Integer.parseInt(line.get(status_idx));

            statusMap.put(name, status);

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
        //initialize target maps for each group
        targets9 = initializeTargets(nines);
        targets10 = initializeTargets(tens);
        targets11 = initializeTargets(elevens);
        targets12 = initializeTargets(twelves);
        targetsFaculty = initializeTargets(faculty);
    }

    // create a map for a given list of active players
    private Map<String, String> initializeTargets(ArrayList<String> group) {
        Map<String, String> map = new HashMap<>();
        List<String> activePlayers = new ArrayList<>();
        for (String player : group) {
            if (statusMap.get(player) == 1) {
                activePlayers.add(player);
            }
        }

        for (int i = 0; i < activePlayers.size(); i++) {
            String player = activePlayers.get(i);
            String target = activePlayers.get((i + 1) % activePlayers.size());
            map.put(player, target);
        }
        return map;
    }

    public int getStatus(String name){
        return statusMap.get(name);
    }

        //Returns the target for a player based on their group
        public String getTarget(String name) {
            if (nines.contains(name)) return targets9.get(name);
            if (tens.contains(name)) return targets10.get(name);
            if (elevens.contains(name)) return targets11.get(name);
            if (twelves.contains(name)) return targets12.get(name);
            if (faculty.contains(name)) return targetsFaculty.get(name);
            return null;
        }
    
        //Eliminate a player and update only their group map
        public void eliminatePlayer(String name) {
            if (statusMap.get(name) != 1) return; // already eliminated
            statusMap.put(name, 0);
    
            if (nines.contains(name)) targets9 = initializeTargets(nines);
            else if (tens.contains(name)) targets10 = initializeTargets(tens);
            else if (elevens.contains(name)) targets11 = initializeTargets(elevens);
            else if (twelves.contains(name)) targets12 = initializeTargets(twelves);
            else if (faculty.contains(name)) targetsFaculty = initializeTargets(faculty);
        }
}
