package MacEachern_Batres_Llamas_Repo;
public class Students {
    /*contains a constructor that creates a Student object and
    a 2 methods to get said student's name and grade. This makes the code safer. */
    private String name;
    private int grade;
    public Students(String name, int grade) {
    this.name = name;
    this.grade = grade;
    }
    public String getName() {
    return name;
    }
    public int getGrade() {
    return grade;
    }
}