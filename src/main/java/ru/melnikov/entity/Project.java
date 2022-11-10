package ru.melnikov.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    // @NotNull
    private long id;

    @Column(name = "name")
    private String name;
}
