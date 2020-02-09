package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name", nullable = false, length = 45)
    private String companyName;
    @Column(name = "username", nullable = false, length = 50)
    private String username;


    //LIST OF INTERNSHIPS
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Internship> internships;

    public Company() {
    }

    public Company(String username, String companyName ) {
        this.username= username;
        this.companyName = companyName;
    }
//
//    public int getId() {
//        return id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public void addInternship(Internship foo){
        if (this.internships == null){
            this.internships = new ArrayList<>();
        }
        this.internships.add(foo);
    }
}
