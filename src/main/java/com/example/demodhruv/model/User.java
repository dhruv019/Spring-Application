package com.example.demodhruv.model;
import javax.persistence.*;

@Entity
// @Table(name="myuser")    Creates table with given name but dont use
public class User {
    @Id
    @GeneratedValue //Used to give auto increment to column field
    public int id;

    @Column
    public String name;

    @Column
    public String pass;

    @Column
    public int age;

    public User(){
    }

    public User(String name, String pass, int age) {
        this.name = name;
        this.pass = pass;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
