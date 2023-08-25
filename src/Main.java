import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Main obj = new Main();
        obj.greeting();
        obj.mainFlow();
    }
    public void greeting(){
        String str = "Welcome to Muhammad Daniyal Khan Bank!";
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            try {
                Thread.sleep(200); // Add a delay of 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mainFlow(){
        startingInput();

        int option = in.nextInt();
        switch (option) {
            case 1:
                transaction();
                break;
            case 2:
                checkDetail();
                break;
            case 3:
                updateInformation();
                break;
            case 4:
                createAccount();
                break;
            case 5:
                deleteAccount();
                break;
            case 6:
                break;
            default:
                mainFlow();
                break;
        }

    }
    public void transaction() {
        System.out.println("1 - credit");
        System.out.println("2 - debit");
        int option = in.nextInt();
        switch (option){
            case 1:
                credit();
                break;
            case 2:
                debit();
                break;
            default:
                mainFlow();
                break;
        }
    }
    public void startingInput(){

        File accountsDir = new File("accounts");
        if (!accountsDir.exists()) {
            accountsDir.mkdir();
        } else {
        }

        System.out.println("\n\nEnter a operation that you want to perform");
        System.out.println("1 - Transaction");
        System.out.println("2 - Check Detail");
        System.out.println("3 - Update Information");
        System.out.println("4 - Create Account");
        System.out.println("5 - Delete Account");
        System.out.println("6 - Exit");
    }
    public void credit(){
        System.out.print("Enter Account Number for credit : ");
        String accountNo = in.next();
        try {
            Scanner balance = new Scanner(new File("accounts/" + accountNo + "/balance.txt"));
            String balanceForDetail = balance.next();
            System.out.println("Your Current Balance is : " + balanceForDetail);
            int curr = Integer.parseInt(balanceForDetail);
            System.out.print("Enter Amount : ");
            int temp = in.nextInt();
            temp += curr;
            String updatedBalance = Integer.toString(temp);
            FileWriter updBalance = null;
            try {
                updBalance = new FileWriter("accounts/" + accountNo + "/balance.txt");
                updBalance.write(updatedBalance);
                updBalance.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        catch (Exception e){
            System.out.println("Account Doesn't exist");
        }
        try{
            Scanner balance = new Scanner(new File("accounts/" + accountNo + "/balance.txt"));
            String balanceForDetail = balance.next();
            System.out.println("Your Updated Balance is : "+balanceForDetail);
        }
        catch(Exception e){
            System.out.println("error");
        }

        mainFlow();
    }

    public void debit(){
        System.out.println("Enter Account Number for debit");
        String accountNo = in.next();

        try {
            Scanner balance = new Scanner(new File("accounts/" + accountNo + "/balance.txt"));
            String balanceForDetail = balance.next();
            System.out.println("Your Current Balance is : " + balanceForDetail);
            int curr = Integer.parseInt(balanceForDetail);
            System.out.print("Enter Amount : ");
            int temp = in.nextInt();
            temp = curr - temp;
            if(temp < 0){
                System.out.println("This transaction not performed due to Insufficient Balance");
                return;
            }
            String updatedBalance = Integer.toString(temp);
            FileWriter updBalance = null;
            try {
                updBalance = new FileWriter("accounts/" + accountNo + "/balance.txt");
                updBalance.write(updatedBalance);
                updBalance.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        catch (Exception e){
            System.out.println("Account Doesn't exist");
        }
        try{
            Scanner balance = new Scanner(new File("accounts/" + accountNo + "/balance.txt"));
            String balanceForDetail = balance.next();
            System.out.println("Your Updated Balance is : "+balanceForDetail);
        }
        catch(Exception e){
            System.out.println("error");
        }

        mainFlow();
    }
    public void updateInformation(){
        System.out.println("What do you want to update ?");
        System.out.println("1 - Phone");
        System.out.println("2 - Address");
        int option = in.nextInt();
        switch ( option ){
            case 1:
                updatePhone();
                break;
            case 2:
                updateAddress();
                break;
            default:
                mainFlow();
                break;
        }
    }
    public void updateAddress(){
        System.out.println("Enter Your Account Number");
        int accountNumber = in.nextInt();

        System.out.println("Enter Your Address");
        String address = in.next();

        FileWriter addressNo = null;
        try {
            addressNo = new FileWriter("accounts/"+accountNumber + "/address.txt");
            addressNo.write(address);
            addressNo.close();
            System.out.println("Address has been updated successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainFlow();
    }
    public void updatePhone(){
        System.out.println("Enter Your Account Number");
        int accountNumber = in.nextInt();

        System.out.println("Enter Your Phone");
        String address = in.next();

        FileWriter addressNo = null;
        try {
            addressNo = new FileWriter("accounts/"+accountNumber + "/phone.txt");
            addressNo.write(address);
            addressNo.close();
            System.out.println("Your Phone has been updated successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainFlow();
    }
    public void checkDetail(){
        System.out.println("Enter Account Number: ");
        String accountNumberInputForCheckingDetail = in.next();

        try {
            Scanner name = new Scanner(new File("accounts/"+accountNumberInputForCheckingDetail+"/name.txt"));
            Scanner phone = new Scanner(new File("accounts/"+accountNumberInputForCheckingDetail+"/phone.txt"));
            Scanner address = new Scanner(new File("accounts/"+accountNumberInputForCheckingDetail+"/address.txt"));
            Scanner balance = new Scanner(new File("accounts/"+accountNumberInputForCheckingDetail+"/balance.txt"));
            // this loop is for reading multiple line
//            while (scanner.hasNextLine()) {
//                // Read the next line
//                String line = scanner.nextLine();
//
//                // Print the current line to the console
//                System.out.println(line);
//            }
                String nameForDetail = name.next();
                String phoneForDetail = phone.next();
                String addressForDetail = address.next();
                String balanceForDetail = balance.next();

            System.out.println("Name    : "+nameForDetail);
            System.out.println("Phone   : "+phoneForDetail);
            System.out.println("Address : "+addressForDetail);
            System.out.println("Balance : "+balanceForDetail);

        } catch (Exception e) {
            System.out.println("Account Doesn't Exist");
        }

        mainFlow();
    }
    public void createAccount(){
        System.out.print("Enter Your Name: ");
        String gar = in.nextLine();
        String name = in.nextLine();
        System.out.print("Enter Your Account Number: ");
        String accountNumber = in.next();
        System.out.print("Enter Your Phone Number: ");
        String phone = in.next();
        System.out.print("Enter Your Address: ");
        String garbage = in.nextLine();
        String address = in.nextLine();;

        try {

            String folderName = accountNumber;
            File folder = new File("accounts/"+folderName);
            if (!folder.exists()) {
                if (folder.mkdir()) {
                } else {
                    System.out.println("Error creating folder.");
                }
            } else {
                System.out.println("Account Already Exist");
                mainFlow();
                return;
            }

            FileWriter Name = new FileWriter("accounts/"+accountNumber + "/name.txt");
            Name.write(name);
            Name.close();

            FileWriter phoneNo = new FileWriter("accounts/"+accountNumber + "/phone.txt");
            phoneNo.write(phone);
            phoneNo.close();

            FileWriter addressNo = new FileWriter("accounts/"+accountNumber + "/address.txt");
            addressNo.write(address);
            addressNo.close();

            FileWriter balance = new FileWriter("accounts/"+accountNumber + "/balance.txt");
            balance.write("100");
            balance.close();

            System.out.println("Account Created Succefully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainFlow();
    }
    public void deleteAccount(){
        System.out.println("Enter Account Number That your want to delete");
        int toDelete = in.nextInt();

        File balance = new File("accounts/"+ toDelete + "/balance.txt");
        File address = new File("accounts/"+ toDelete + "/address.txt");
        File phone = new File("accounts/"+ toDelete + "/phone.txt");
        File name = new File("accounts/"+ toDelete + "/name.txt");
        balance.delete();
        address.delete();
        phone.delete();
        name.delete();

        String folderPath = "accounts/"+toDelete;
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            boolean success = folder.delete();
            if (success) {
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Error deleting account.");
            }
        } else {
            System.out.println("Account does not exist.");
        }
        mainFlow();
    }
}