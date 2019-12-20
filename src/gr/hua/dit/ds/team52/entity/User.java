package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;

@MappedSuperclass       //Need this for Hibernate to work with "extend"
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //"Single Table" is the default inheritance strategy
//@DiscriminatorColumn(name="user_type") //Since there will be table merging, we can give a different name to the default D_TYPE that has the
// class that inherits the superclass
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String fname;
    @Column(name = "last_name")
    private String lname;

    public User() {

    }

    public User(String username, String password, String fname, String lname) {
        //autoGenerate();               using auto increment in db, not needed
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
