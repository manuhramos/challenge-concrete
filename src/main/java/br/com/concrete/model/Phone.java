package br.com.concrete.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Expose(serialize = false, deserialize = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String number;

    @Column
    private String ddd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
