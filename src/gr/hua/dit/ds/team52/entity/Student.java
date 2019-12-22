package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//@Entity
//@DiscriminatorValue("Student")  //What appears under user_type from the inherited class, user.java
//@Table(name = "student_second")
public class Student extends User {
    @Column(name = "dept")
    private String department;
    @Column(name = "year")
    private int year;
    @Column(name = "failed")
    private int failedClass;

//    @OneToMany(mappedBy="student_second", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    private List<Petition> petitionList;

    public Student() {
            // DEFAULT CONSTRUCTOR
    }

    //Χωρίς δημιουργία User
    public Student(String username, String password, String fname, String lname, String department, int year, int failedClass) {
        super(username, password, fname, lname);
        this.department = department;
        this.year = year;
        this.failedClass = failedClass;
    }

    //Με τη δημιουργία User
    public Student(String department, int year, int failedClass) {
        this.department = department;
        this.year = year;
        this.failedClass = failedClass;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFailedClass() {
        return failedClass;
    }

    public void setFailedClass(int failedClass) {
        this.failedClass = failedClass;
    }

//    public List<Petition> getPetitionList() {
//        return petitionList;
//    }

//    public void setPetitionList(List<Petition> petitionList) {
//        this.petitionList = petitionList;
//    }

        // add convenience methods for bi-directional relation
//    public void add(Petition apetition) {
//        if(petitionList == null) {
//            petitionList = new ArrayList<>();
//        }
//        petitionList.add(apetition);
//        apetition.setStudent(this);
//    }

    public boolean canSubmit() {
        if (year >= 3 && failedClass <= 3) {
            return true;
        }
        return false;
    }

}