package edu.ksmith10.greetingcloudapp;

import javax.persistence.*;

/**
 * CLASS: Greeting
 * PURPOSE: Construct a Greeting object to be used in our Spring Boot REST application.
 * The Entity annotation indicates that this class is a JPA entity
 * The Table annotation specifies the name for the table in the db
 */
@Entity
@Table(name="greetings")
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;

    public Greeting() {
        this.id = id;
        this.content = content;

    }

    public Greeting(String content) {
        this.content = content;
    }

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }
}