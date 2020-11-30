package accounts;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class person {
    public String name;
    public String username;
    public String password;
    public boolean isManager = true;
}

public class customer extends person{
    public customer(String name,String username,String password, boolean isManager){
        this.name = name;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }
    public static customer get(String username) throws FileNotFoundException {
        File myObj = new File("src/database/user.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String onUser = data.split(",")[0];
            if (onUser.equals(username)){
                String pass = data.split(",")[2];
                String name = data.split(",")[1];
                boolean isMan = Boolean.parseBoolean(data.split(",")[3]);
                return new customer(name,username,pass,isMan);
            }
        }
        return null;
    }
    public static customer login(String username,String password) throws FileNotFoundException {
        File myObj = new File("src/database/user.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String onUser = data.split(",")[0];
            if (onUser.equals(username)){
                String pass = data.split(",")[2];
                String name = data.split(",")[1];
                boolean isMan = Boolean.parseBoolean(data.split(",")[3]);
                if(pass.equals(password)){
                    return new customer(name,username,pass,isMan);
                }
                break;
            }
        }
        return null;
    }
    public static customer signup(String name,String username,String password) throws IOException {
        customer obj = new customer(name, username,password,false);

        File myObj = new File("src/database/user.txt");
        Scanner myReader = new Scanner(myObj);
        boolean flag = true;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String onUser = data.split(",")[0];
            if (onUser.equals(username)){
                flag = false;
                break;
            }
        }
        if(flag) {
            FileWriter writer = new FileWriter("src/database/user.txt", true);
            writer.write(obj.username + "," + obj.name + "," + obj.password + ",false\n");
            writer.close();
            return obj;
        }
        else{
            return null;
        }
    }
}

