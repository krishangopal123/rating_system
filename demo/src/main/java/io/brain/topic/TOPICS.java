package io.brain.topic;

public class TOPICS {
    private int id;

    public int getId() {
        return id;
    }

    public TOPICS() {
    }

    public TOPICS(int id, String name) {
        super();
        this.id = id;
        this.name = name;
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

    private String name;


}
