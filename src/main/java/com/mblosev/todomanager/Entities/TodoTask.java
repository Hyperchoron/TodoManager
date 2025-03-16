package com.mblosev.todomanager.Entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    int id;

    @Getter
    @Setter
    Date creationDate;

    @Getter
    @Setter
    String task;

    @Getter
    @Setter
    boolean isCompleted;
}
