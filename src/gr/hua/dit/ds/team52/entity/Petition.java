package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;


//@Entity
//@Table(name="petition")
public class Petition {
    // define fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name="status")
    private String status;

//    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinColumn(name="student_id")
//    private Student student;

    // define constructors
    public Petition() {

    }

    public Petition(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "pending";
    }

    // define getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void Accept (){
        this.status = "accepted";
    }

    public void Reject(){
        this.status = "rejected";
    }

//    public Student getStudent() {
//        return student;
//    }

//    public void setStudent(Student student) {
//        this.student = student;
//    }

    // define toString
    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }

}