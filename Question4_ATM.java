import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Question4_ATM {
    
    private static Scanner scanner = new Scanner(System.in);
    private static String userName;
    private static String userAddress;
    private static double accountBalance;
    
    public static void main(String[] args) {
        System.out.println("=== ATM with Multiple Languages ===\n");
        
        System.out.print("Enter your name: ");
        userName = scanner.nextLine();
        
        System.out.print("Enter your address: ");
        userAddress = scanner.nextLine();
        
        System.out.print("Enter your account balance: ");
        accountBalance = Double.parseDouble(scanner.nextLine().trim());
        
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());
            
            switch (choice) {
                case 1:
                    displayInEnglish();
                    break;
                case 2:
                    displayInVietnamese();
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye! / Tam biet!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n========== LANGUAGE MENU ==========");
        System.out.println("1. English");
        System.out.println("2. VietNam");
        System.out.println("3. Exit");
        System.out.println("====================================");
    }
    
    private static void displayInEnglish() {
        System.out.println("\n--- Output when locale is English: ---");
        
        Locale localeEN = Locale.US;
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", localeEN);
        
        String nameLabel = bundle.getString("name");
        String addressLabel = bundle.getString("address");
        String balanceLabel = bundle.getString("balance");
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeEN);
        String formattedBalance = currencyFormat.format(accountBalance);
        
        System.out.println(nameLabel + ": " + userName);
        System.out.println(addressLabel + ": " + userAddress);
        System.out.println(balanceLabel + ": " + formattedBalance);
    }
    
    private static void displayInVietnamese() {
        System.out.println("\n--- Output when locale is Vietnamese: ---");
        
        Locale localeVN = Locale.of("vi", "VN");
        ResourceBundle bundle = ResourceBundle.getBundle("Messages", localeVN);
        
        String nameLabel = bundle.getString("name");
        String addressLabel = bundle.getString("address");
        String balanceLabel = bundle.getString("balance");
        
        NumberFormat numberFormat = NumberFormat.getNumberInstance(localeVN);
        String formattedBalance = numberFormat.format(accountBalance) + " VND";
        
        System.out.println(nameLabel + ": " + userName);
        System.out.println(addressLabel + ": " + userAddress);
        System.out.println(balanceLabel + ": " + formattedBalance);
    }
}
