package org.example.POJO;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "manufacture")
public class Manufacture implements Serializable {
    @Id
    @Column(name = "ID")
    private String ID;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Location")
    private String Location;
    @Column(name = "Employee")
    private int Employee;
    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phones;

    public Manufacture(){

    }
    public Manufacture(String ID, String name, String location, int employee) {
        this.ID = ID;
        Name = name;
        Location = location;
        Employee = employee;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setEmployee(int employee) {
        Employee = employee;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }

    public int getEmployee() {
        return Employee;
    }

    public List<Phone> getPhones() {
        return phones;
    }


    @Override
    public String toString() {
        return "Manufacture{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Location='" + Location + '\'' +
                ", Employee=" + Employee +
                ", phones=" + phones +
                '}' + '\n';
    }

}