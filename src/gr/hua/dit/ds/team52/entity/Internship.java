package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internship")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "salary")
    private int salary;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;

    //Company of internship
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    //Petitions for the position
    @OneToMany(mappedBy = "internship",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Petition> petitions;

    public Internship(){};
    public Internship(String title, String description, String status, int salary) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Petition> getPetitions() {
        return petitions;
    }

    public void addPetition(Petition petition) {
        if (petitions == null){
            petitions = new ArrayList<>();
        }
        this.petitions.add(petition);
    }

    //    public boolean getApproved() {
//        return approved;
//    }
//
//    public void setApproved(boolean approved) {
//        this.approved = approved;
//    }
}
