package gr.hua.dit.ds.team52.entity;

import javax.persistence.*;

@Entity
@Table(name = "supported_services")
public class Service {

    @Id
    @Column(name ="id",nullable = false, updatable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = false)
    private String title;

    @Basic
    @Column(name = "description", nullable = false)
    private String description;


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

    public int getId() {
        return id;
    }
}
