package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name= "username")
    private String username;

    @Column(name = "dept")
    private String dept;

    @Column(name = "year")
    private Integer year;

    @Column(name = "failed")
    private Integer failed;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL) //if student gets deleted delete all his petitions
    private List<Petition> petitions;

    public Student() {

    }


    public Student(String firstName,String lastName,String username,String dept,int year,int failed) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.username=username;
        this.dept=dept;
        this.year=year;
        this.failed=failed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void add(Petition apetition) {
        if(petitions == null) {
            petitions = new ArrayList<>();
        }
        petitions.add(apetition);
        apetition.setStudent(this);
    }

    public boolean canSubmit(){
        return getYear() >= 3 && getFailed() <= 3;
    }
}
