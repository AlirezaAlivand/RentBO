import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.awt.SystemColor.info;

public class Main {

    static List<Bike> BikeList = new ArrayList<>();
    static List<User> UserList = new ArrayList<>();

    public static void main(String[] args) {
        Menu();
    }

    private static void Menu() {
        Scanner in = new Scanner(System.in);
        ReadBikeFormFile();
        System.out.println("Menu-->");
        System.out.println("1.charkh haye dar dastres.");
        System.out.println("2.charkh haye keraye dade shode.");
        System.out.println("3.Bargardandan charkh.");
        System.out.println("4.keraye dadan charkh.");
        System.out.println("5.Ezafe kardan charkh.");
        System.out.println("6.Ezafe kardan karbar.");
        System.out.println("7.List charkhha");
        System.out.println("8.List karbarha");
        System.out.println("9.Exit");
        System.out.println("10.Load Data.");
        System.out.println("Adad gozine mored nazar ra entekhab konid : ");
        int num = in.nextInt();
        switch (num){
            case 1 :
                AvailableBikes();
                break;
            case 2 :
                RentedBikes();
                break;
            case 3 :
                ReturnBike();
                break;
            case 4:
                RentBike();
                break;
            case 5 :
                AddBike();
                break;
            case 6 :
                AddUser();
                break;
            case 7 :
                BikeList();
                break;
            case 8 :
                UserList();
                break;
            case 9 :
                Exit();
                break;
            case 10 :
                LoadData();
                break;
            default:
                break;
        }

    }

    private static void ReadBikeFormFile() {

    }

    private static void LoadData() {
        ReadBikeFromFile();
        ReadUserFromFile();
        Menu();
    }

    private static void Exit() {
        SaveBikeToFile();
        SaveUserToFile();
    }

    private static void UserList() {
        int count=1;
        for(User user : UserList){
            System.out.println(count+"."+user.toString());
            count++;
        }
        Menu();
    }

    private static void BikeList() {
        int count=1;
        for(Bike bike : BikeList){
            System.out.println(count+"."+bike.toString());
            count++;
        }
        Menu();
    }

    private static void AddUser() {
        Scanner in = new Scanner(System.in);
        System.out.print("Username ra vared konid : ");
        String username = in.next();
        User user = new User();
        user.setUsername(username);
        UserList.add(user);
        System.out.println("User ba movafaqiat sakhte shod.");
        Menu();
    }

    private static void AddBike() {
        Scanner in = new Scanner(System.in);
        System.out.println("Noe charkh ra entekhab konid :");
        System.out.println("1.Electric Bike");
        System.out.println("2.Road Bike");
        System.out.print("Adad gozine mored nazar ra vared konid : ");
        int number = in.nextInt();
        if(number == 1) BikeList.add(new ElectricBike());
        else BikeList.add(new RoadBike());
        System.out.println("Charkh ba movafaqiat ezafe shod.");
        Menu();
    }

    private static void RentBike() {
        Scanner in = new Scanner(System.in);
        System.out.print("Username ra vared konid : ");
        String username = in.nextLine();
        if(validateUsername(username)){
            int ElectricBikeCount = ElCounter();
            int RoadBikeCount = RoadCounter();
            System.out.println("1.ElectricBike (tedad mojod : "+ElectricBikeCount);
            System.out.println("2.RoadBike (tedad mojod : "+RoadBikeCount);
            System.out.print("Adad gozine mored nazar ra vared konid : ");
            int number = in.nextInt();
            if(number == 1) RentElectricBike(username);
            else RentRoadBike(username);
        }else{
            System.out.println("Username vared shode sahih nemibashad.");
        }
        Menu();
    }

    private static void RentRoadBike(String username) {
        boolean check = false;
        for(Bike bike : BikeList){
            if(bike instanceof RoadBike && !bike.isRented()){
                bike.RentBike();
                for(User user : UserList){
                    if(user.getUsername().equals(username)) {
                        if (user.AddBike(bike))
                            System.out.println("Charkh ba movafaqiat keraye dade shod.");
                        check = true;
                        break;

                    }
                }
            }
            if(check) break;
        }
    }

    private static void RentElectricBike(String username) {
        boolean check = false;
        for(Bike bike : BikeList){
            if(bike instanceof ElectricBike && !bike.isRented()){
                bike.RentBike();
                for(User user : UserList){
                    if(user.getUsername().equals(username)) {
                        if (user.AddBike(bike))
                            System.out.println("Charkh ba movafaqiat keraye dade shod.");
                        check = true;
                        break;
                    }
                }
            }
            if(check) break;
        }
    }

    private static int RoadCounter() {
        int counter=0;
        for(Bike bike : BikeList)
            if(bike instanceof RoadBike && !bike.isRented()) counter++;
        return counter;
    }

    private static int ElCounter() {
        int counter=0;
        for(Bike bike : BikeList)
            if(bike instanceof ElectricBike && !bike.isRented()) counter++;
        return counter;
    }

    private static boolean validateUsername(String username) {
        for(User user : UserList){
            if(user.getUsername().equals(username)) return true;
        }
        return false;
    }

    private static void ReturnBike() {
        Scanner in = new Scanner(System.in);
        System.out.print("Username ra vared konid : ");
        String username = in.nextLine();
        if(validateUsername(username)){
            ShowUserBikes(username);
            System.out.println("Adad charkh mored nazar ra vareed konid : ");
            int number = in.nextInt();
            Bike RemovedBike = RemoveBikeFromUser(username,number);
            System.out.println("charkh ba movafaqiat bazgardande shod: ");
            for(Bike bike : BikeList){
                if(bike.getSerialNumber().equals(RemovedBike.getSerialNumber()) && bike.isRented()){
                    bike.ReturnBike();
                    break;
                }
            }
            System.out.println("charkh ba movafaqiat bazgardande shod");
        }else System.out.println("username vared shode sahih nemibashad");
        Menu();
    }

    private static Bike RemoveBikeFromUser(String username, int number) {
        Bike bike = new RoadBike();
        for(User user : UserList){
            if(user.getUsername().equals(username)) bike = user.RemoveBike(number);
        }
        return bike;
    }

    private static void ShowUserBikes(String username) {
        for(User user : UserList){
            if(user.getUsername().equals(username)){
                int counter=1;
                for(Bike bike : user.getRentedList()){
                    System.out.println(counter+". Serial Number : "+bike.getSerialNumber());
                    counter++;
                }
                break;
            }
        }
    }

    private static void RentedBikes() {
        System.out.println("Charkh haye keraye dade shode : ");
        for(Bike bike : BikeList){
            if(bike.isRented()){
                if(bike instanceof ElectricBike)
                    System.out.println("Electric Bike - "+bike.getSerialNumber());
                else  System.out.println("Road Bike - "+bike.getSerialNumber());
            }
        }
        Menu();
    }

    private static void AvailableBikes() {
        System.out.println("Charkh haye dar dastress : ");
        for(Bike bike : BikeList){
            if(!bike.isRented()){
                if(bike instanceof ElectricBike)
                    System.out.println("Electric Bike - "+bike.getSerialNumber());
                else  System.out.println("Road Bike - "+bike.getSerialNumber());
            }
        }
        Menu();
    }

    private static void SaveBikeToFile(){
        try {
            FileWriter file = new FileWriter("BikeInfo.txt");
            for(Bike bike : BikeList){

                file.write(info+"\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void SaveUserToFile(){
        try {
            FileWriter file = new FileWriter("UserInfo.txt");
            for(User user : UserList){
                String info = user.getUsername()+";";
                for(Bike bike : user.getRentedList()){
                    info += bike.getSerialNumber()+";"+bike.isRented()+";";
                }
                file.write(info+"\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void ReadBikeFromFile(){
        try {
            FileReader file = new FileReader("BikeInfo.txt");
            BufferedReader bf = new BufferedReader(file);
            String line = "";
            while((line=bf.readLine())!=null){
                StringTokenizer tokens = new StringTokenizer(line,";");
                String serialNumber = tokens.nextToken();
                if(serialNumber.equals("R-B")){
                    Bike bike = new RoadBike();
                    bike.setRented(Boolean.parseBoolean(tokens.nextToken()));
                    BikeList.add(bike);
                }else{
                    Bike bike = new ElectricBike();
                    bike.setRented(Boolean.parseBoolean(tokens.nextToken()));
                    BikeList.add(bike);
                }
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void ReadUserFromFile(){
        try {
            FileReader file = new FileReader("UserInfo.txt");
            BufferedReader bf = new BufferedReader(file);
            String line = "";
            while((line=bf.readLine())!=null){
                StringTokenizer tokens = new StringTokenizer(line,";");
                User user = new User();
                user.setUsername(tokens.nextToken());
                List<Bike> userBikeList = new ArrayList<>();
                while(tokens.hasMoreTokens()){
                    String serialNumber = tokens.nextToken();
                    if(serialNumber.equals("R-B")){
                        Bike bike = new RoadBike();
                        bike.setRented(Boolean.parseBoolean(tokens.nextToken()));
                        userBikeList.add(bike);
                    }else{
                        Bike bike = new ElectricBike();
                        bike.setRented(Boolean.parseBoolean(tokens.nextToken()));
                        userBikeList.add(bike);
                    }
                }
                user.setRentedList(userBikeList);
                UserList.add(user);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
