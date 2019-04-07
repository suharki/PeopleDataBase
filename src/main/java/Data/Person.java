package Data;

import Data.Address;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="person")

public class Person implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "height")
    private int height;

    @Column(name = "weight")
    private Double weight;

    @ManyToOne()
    @JoinColumn(name = "address")
//    @Column(name = "address",nullable = false)
    private Address address;



}
