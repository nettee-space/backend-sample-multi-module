package nettee.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
    
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
}
