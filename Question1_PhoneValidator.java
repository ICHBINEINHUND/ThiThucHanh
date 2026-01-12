import java.util.Scanner;

public class Question1_PhoneValidator {

    public static boolean isValidPhoneVietNam(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        
        String cleanPhone = phone.replaceAll("[\\s-]", "");
        
        if (!cleanPhone.matches("\\d+")) {
            return false;
        }
        
        if (cleanPhone.length() != 10) {
            return false;
        }
        
        if (!cleanPhone.startsWith("0")) {
            return false;
        }
        
        char secondDigit = cleanPhone.charAt(1);
        if (secondDigit != '3' && secondDigit != '5' && 
            secondDigit != '7' && secondDigit != '8' && secondDigit != '9') {
            return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Vietnamese Phone Number Validator ===");
        
        while (true) {
            System.out.print("\nEnter phone number (or 'exit' to quit): ");
            String phone = scanner.nextLine().trim();
            
            if (phone.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            
            boolean isValid = isValidPhoneVietNam(phone);
            String status = isValid ? "is valid" : "isn't valid";
            System.out.println("This phone number " + phone + " " + status);
        }
        
        scanner.close();
    }
}
