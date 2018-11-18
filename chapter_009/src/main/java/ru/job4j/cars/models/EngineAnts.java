package ru.job4j.cars.models;

import javax.persistence.*;

/**
 * @author Alexander Belov (whiterabbit.nsk@gmail.com)
 * @since 07.10.18
 */
@javax.persistence.Entity
@Table(name = "engines")
public class EngineAnts implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public EngineAnts() {

    }

    public EngineAnts(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
