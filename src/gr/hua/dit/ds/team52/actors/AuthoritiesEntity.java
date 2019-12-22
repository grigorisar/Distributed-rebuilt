package gr.hua.dit.ds.team52.actors;

import javax.persistence.*;

@Entity
@Table(name = "authorities", schema = "wM4YgjeZJi", catalog = "")
public class AuthoritiesEntity {
    private String authority;


    @Basic
    @Column(name = "authority", nullable = false, length = 50)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthoritiesEntity that = (AuthoritiesEntity) o;

        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return authority != null ? authority.hashCode() : 0;
    }

    private String username;

    @GeneratedValue
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
