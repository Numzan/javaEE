package edu.whu.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    long id;
    String name;
}
