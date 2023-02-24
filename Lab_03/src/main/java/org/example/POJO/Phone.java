package org.example.POJO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MobilePhone")
public class Phone implements Serializable {
    @Id
    @Column(name = "ID")
    private String ID;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Price")
    private  int Price;
    @Column(name = "Color")
    private  String Color;
    @Column(name = "Country")
    private String Country;
    @Column(name = "Quantity")
    private int Quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manufacture_id")
    private Manufacture manufacture;

    public  Phone(){
    };
    public  Phone(String ID){
        this.ID = ID;
    }
    public Phone(String ID, String name, int price, String color, String country, int quantity,Manufacture manufacture) {
        this.ID = ID;
        this.Name = name;
        this.Price = price;
        this.Color = color;
        this.Country = country;
        this.Quantity = quantity;
        this.manufacture = manufacture;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public int getPrice() {
        return Price;
    }

    public String getColor() {
        return Color;
    }

    public String getCountry() {
        return Country;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Color='" + Color + '\'' +
                ", Country='" + Country + '\'' +
                ", Quantity='" + Quantity + '\'' +
                '}' + '\n';
    }
}
