package br.com.concrete.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GenericGenerator(name = "uuid_id", strategy = "br.com.concrete.util.UUIDGenerator")
    @GeneratedValue(generator = "uuid_id")
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Phone> phones;

    @Column
    private Date created;

    @Column
    private Date modified;

    @Column
    private Date last_login;

    @Column
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean validateSession(){
        Date now = new Date();
        long duration = now.getTime() - this.last_login.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        if(diffInMinutes < 30)
            return true;
        return false;
    }
}
