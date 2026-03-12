import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/*
PROJECT: Secure Password Reset Using Email
SUBJECT: Data Structures

CO ATTAINMENTS

CO1 - Programming fundamentals (loops, conditions, methods)
CO2 - Implement Data Structures (HashMap)
CO3 - Searching techniques
CO4 - Data structure operations (Insert, Update, Delete)
CO5 - Problem solving using Data Structures
CO6 - Develop applications using Data Structures
*/

public class SecurePasswordReset{

    // CO2: HashMap used for storing user data
    // Key = Email , Value = Password
    static HashMap<String,String> users = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    // ---------------- PASSWORD RULES DISPLAY ----------------
    public static void displayPasswordRules()
    {
        System.out.println("\nPassword must contain:");
        System.out.println("1. Minimum 6 characters");
        System.out.println("2. At least one capital letter");
        System.out.println("3. At least one number");
    }

    // ---------------- PASSWORD STRENGTH CHECK ----------------
    public static boolean checkPasswordStrength(String password)
    {
        if(password.length() < 6)
        {
            System.out.println("Weak Password! Too short.");
            return false;
        }

        if(!password.matches(".*[A-Z].*"))
        {
            System.out.println("Password must contain a capital letter.");
            return false;
        }

        if(!password.matches(".*[0-9].*"))
        {
            System.out.println("Password must contain a number.");
            return false;
        }

        System.out.println("Strong Password!");
        return true;
    }

    // ---------------- REGISTER USER ----------------
    // DSA Topic: INSERTION
    public static void registerUser()
    {
        System.out.print("Enter Gmail: ");
        String email = sc.next();

        // DSA Topic: SEARCHING
        if(users.containsKey(email))
        {
            System.out.println("Account already exists.");
            return;
        }

        displayPasswordRules();

        String password;

        do
        {
            System.out.print("Create Password: ");
            password = sc.next();
        }
        while(!checkPasswordStrength(password));

        // HashMap insertion
        users.put(email,password);

        System.out.println("Account Registered Successfully!");
    }

    // ---------------- LOGIN ----------------
    // DSA Topic: SEARCHING
    public static void loginUser()
    {
        System.out.print("Enter Email: ");
        String email = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        if(users.containsKey(email) && users.get(email).equals(password))
        {
            System.out.println("Login Successful!");
        }
        else
        {
            System.out.println("Invalid Email or Password.");
        }
    }

    // ---------------- RESET PASSWORD ----------------
    // DSA Topic: UPDATE
    public static void resetPassword()
    {
        System.out.print("Enter Gmail: ");
        String email = sc.next();

        if(!users.containsKey(email))
        {
            System.out.println("Account not found.");
            return;
        }

        // OTP Simulation
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);

        System.out.println("OTP sent to Gmail (simulation): " + otp);

        System.out.print("Enter OTP: ");
        int enteredOtp = sc.nextInt();

        if(enteredOtp != otp)
        {
            System.out.println("Invalid OTP.");
            return;
        }

        displayPasswordRules();

        String newPassword;

        do
        {
            System.out.print("Enter New Password: ");
            newPassword = sc.next();
        }
        while(!checkPasswordStrength(newPassword));

        // HashMap update
        users.put(email,newPassword);

        System.out.println("Password Reset Successful!");
    }

    // ---------------- DELETE ACCOUNT ----------------
    // DSA Topic: DELETION
    public static void deleteAccount()
    {
        System.out.print("Enter Email to delete: ");
        String email = sc.next();

        if(users.containsKey(email))
        {
            users.remove(email);

            System.out.println("Account Deleted Successfully!");
        }
        else
        {
            System.out.println("Account not found.");
        }
    }

    // ---------------- DISPLAY USERS ----------------
    // DSA Topic: TRAVERSAL
    public static void displayUsers()
    {
        if(users.isEmpty())
        {
            System.out.println("No registered accounts.");
            return;
        }

        System.out.println("\nRegistered Gmail Accounts:");

        for(String email : users.keySet())
        {
            System.out.println(email);
        }
    }

    // ---------------- MAIN METHOD ----------------
    // CO1: Control structures (Loop + Switch)
    public static void main(String[] args)
    {

        int choice;

        do
        {
            System.out.println("\n===== SECURE PASSWORD RESET SYSTEM =====");

            System.out.println("1 Register Account");
            System.out.println("2 Login");
            System.out.println("3 Reset Password");
            System.out.println("4 Delete Account");
            System.out.println("5 Show Registered Emails");
            System.out.println("6 Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    registerUser();
                    break;

                case 2:
                    loginUser();
                    break;

                case 3:
                    resetPassword();
                    break;

                case 4:
                    deleteAccount();
                    break;

                case 5:
                    displayUsers();
                    break;

                case 6:
                    System.out.println("Exiting System...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        }while(choice != 6);

        // CO6: Complete application using Data Structures
    }
}