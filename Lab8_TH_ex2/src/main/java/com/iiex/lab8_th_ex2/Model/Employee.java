package com.iiex.lab8_th_ex2.Model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "employees")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String  Name;
    private String Email;
    private String Address;
    private  String	Phone;
}
