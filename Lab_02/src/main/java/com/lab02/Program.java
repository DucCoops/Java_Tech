package com.lab02;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void print(List<Product> list) {
        System.out.println("\n============ BEGIN ============ ");
        try {
            list.forEach(System.out::println);
        }
        catch (NullPointerException e)
        {
            System.out.println("Null");
        }
        System.out.println("============ END ============ \n");
    }
    public  static void menu(){
        ProductDAO productDAO = new ProductDAO();
        int choice = 1;
        do
        {
            System.out.println ("-----------MENU---------------n"); ;
            System.out.println("1. Read all products");
            System.out.println("2. Read detail product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            String id, name;
            String buffer;
            int price;
            Product product;
            switch (choice){
                case 1:
                    List<Product> list_product = productDAO.readAll();
                    print(list_product);
                    break;
                case 2:
                    System.out.println("Please enter id: ");
                    id = sc.next();
                    System.out.println("Detail: " + productDAO.read(id).toString());
                    break;
                case 3:
                    System.out.println("Please enter id: ");
                    id = sc.next();
                    System.out.println("Please enter name: ");
                    buffer = sc.next();
                    name =  sc.nextLine();
                    name = buffer + name;
                    System.out.println("Please enter price: ");
                    price = sc.nextInt();
                    productDAO.add(new Product(id,name,price));
                    break;
                case 4:
                    System.out.println("Please enter id need update:  ");
                    id = sc.next();
                    System.out.println("Please enter name: ");
                    buffer = sc.next();
                    name = sc.nextLine();
                    name = buffer + name;
                    System.out.println("Please enter price: ");
                    price = sc.nextInt();
                    productDAO.update(new Product(id,name,price));
                    break;
                case 5:
                    System.out.println("Please enter id: ");
                    id = sc.next();
                    if (productDAO.delete(id)){
                        System.out.println("Delete Successful!");
                    }
                    else {
                        System.out.println("Delete Fail!");
                    }
                    break;
                default:
                    break;
            }
        }
        while (choice != 6);
    }

    public static void main(String[] args) {
        ProductDAO.createTable();
        menu();
    }
}
