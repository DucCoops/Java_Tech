package org.example;

import com.google.protobuf.RpcUtil;
import org.example.Implement.ManufactureDAO;
import org.example.Implement.PhoneDAO;
import org.example.POJO.Manufacture;
import org.example.POJO.Phone;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public  static  void menu(){
        System.out.println("\n------Phone------\n" +
                "1. Add\n" +
                "2. Get by id\n" +
                "3. Get all\n" +
                "4. Remove\n" +
                "5. Highest Price Phone\n" +
                "6. Sort \n" +
                "7. Check if there is a phone priced above 50 million VND.\n" +
                "8. The first phone in the list that meets the criteria: has the color 'Pink' and costs over 15 million\n" +
                "------Manufacture------\n" +
                "9. Add\n" +
                "10. Get by id\n" +
                "11. Get all\n" +
                "12. Remove by id\n" +
                "13. Check whether all manufacturers have more than 100 employees.\n" +
                "14. Sum of all employees of the manufactures.\n" +
                "15. The last manufacturer in the list of manufacturers that meet the criteria: based in the US\n" +
                "0. Exit\n" );


    }

    public static void main( String[] args )
    {
        Scanner  sc = new Scanner(System.in);
        PhoneDAO phoneDAO = new PhoneDAO(Phone.class);
        ManufactureDAO manufactureDAO = new ManufactureDAO(Manufacture.class);
        while (true){
            menu();
            System.out.print("Your choice : ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Add new Phone : \n");
                    System.out.print("\t ID : ");String ID = sc.next();
                    System.out.print("\t Name : ");String Name = sc.next();
                    System.out.print("\t Price : ");int Price = sc.nextInt();
                    System.out.print("\t Color : ");String color = sc.next();
                    System.out.print("\t Country : ");String Country = sc.next();
                    System.out.print("\t Quantity : ");int Quantity = sc.nextInt();
                    System.out.print("\t Manufacture Id : ");String manu_id = sc.next();
                    boolean check =  phoneDAO.add(new Phone(ID,Name,Price,color,Country,Quantity,manufactureDAO.get(manu_id)));
                    System.out.print( check ? "Add Complete" : "Add Fail");
                    break;
                case 2:
                    System.out.print("Enter ID : ");ID = sc.next();
                    Phone phone = phoneDAO.get(ID);
                    System.out.print(phone == null ? "Not found " : phone.toString());
                    break;
                case 3:
                    phoneDAO.getAll().forEach(p -> System.out.print(p.toString()));
                    break;
                case 4:
                    System.out.print("Input ID want to remove : ");ID = sc.next();
                    check = phoneDAO.remove(phoneDAO.get(ID));
                    System.out.print( check ? "Remove Complete" : "Add Fail");
                    break;
                case 5:
                    phone = phoneDAO.getHighestPrice();
                    System.out.print(phone == null ? "Not found " : phone.toString());
                    break;
                case 6:
                    phoneDAO.Sort().forEach(p -> System.out.print(p.toString()));
                    break;
                case 7:
                    System.out.print("Enter ID : ");ID = sc.next();
                    System.out.print(phoneDAO.Above50Millions(phoneDAO.get(ID)) ? "True" : "False");
                    break;
                case 8:
                    phone = phoneDAO.FirstPhone();
                    System.out.print(phoneDAO.FirstPhone() == null ? "Not Found" : phone.toString());
                    break;
                case 9:
                    System.out.print("Add new  manufacture : \n");
                    System.out.print("\t ID : "); ID = sc.next();
                    System.out.print("\t Name : "); Name = sc.next();
                    System.out.print("\t Location : ");String Location = sc.next();
                    System.out.print("\t Employee : "); int Employee = sc.nextInt();

                    check =  manufactureDAO.add(new Manufacture(ID,Name,Location,Employee));
                    System.out.print( check ? "Add Complete" : "Add Fail");
                    break;
                case 10:
                    System.out.print("Enter ID : ");ID = sc.next();
                    Manufacture manufacture = manufactureDAO.get(ID);
                    System.out.print(manufacture == null ? "Not found " : manufacture.toString());
                    break;
                case 11:
                    manufactureDAO.getAll().forEach(p -> System.out.print(p.toString()));
                    break;
                case 12 :
                    System.out.print("Input ID want to remove : ");ID = sc.next();
                    check = manufactureDAO.remove(manufactureDAO.get(ID));
                    System.out.print( check ? "Remove Complete" : "Add Fail");
                    break;
                case 13:
                    System.out.print(manufactureDAO.over100emp() ? "True" : "False");
                    break;
                case 14:
                    System.out.print(manufactureDAO.sumEmp());
                    break;
                case 15:
                    manufacture = manufactureDAO.lastInUSA();
                    System.out.print(manufacture == null ? "Not Found" : manufacture.toString());
                    break;
                case  0:
                    System.exit(0);
                    break;
            }

        }
    }
}
