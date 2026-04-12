/*//<editor-fold defaultstate="collapsed" desc="comment">

//</editor-fold>
*/import java.util.Scanner;

public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    public Login() {
    }

    public Login(String firstName, String lastName,
                 String username, String password,
                 String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }



    public boolean checkUserName() {

        if (username == null) return false;

        // Must contain exactly one underscore
        int underscoreCount = 0;

        for (char c : username.toCharArray()) {
            if (c == '_') {
                underscoreCount++;
            }
        }

        return underscoreCount == 1 && username.length() <= 5;
    }

    
    public boolean checkPasswordComplexity() {

        if (password == null || password.length() < 8)
            return false;

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasDigit && hasSpecial;
    }



    public boolean checkCellPhoneNumber() {

        if (cellPhoneNumber == null)
            return false;

        // South African format example:
        // +27831234567
        return cellPhoneNumber.matches("^\\+27\\d{9}$");
    }


    public String registerUser() {

        if (!checkUserName()) {
            return "Username is not correctly formatted; "
                 + "must contain one underscore and be no more than five characters.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; "
                 + "must contain at least eight characters, "
                 + "a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted; "
                 + "must start with +27 followed by 9 digits.";
        }

        return "Registration successful.";
    }

    
    public boolean loginUser(String enteredUsername,
                             String enteredPassword) {

        return username.equals(enteredUsername)
            && password.equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUsername,
                                    String enteredPassword) {

        if (loginUser(enteredUsername, enteredPassword)) {

            return "Welcome "
                 + firstName + " "
                 + lastName
                 + ", it is great to see you.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }

   public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(" Chat Registration ");
            
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            
            System.out.print("Enter username ( _ required, max 5 chars): ");
            String username = scanner.nextLine();
            
            System.out.print("Enter password (8 characters, 1 capital letter, 1 number, 1 special character ): ");
            String password = scanner.nextLine();
            
            System.out.print("Enter cell phone (Begin with +27): ");
            String cellPhone = scanner.nextLine();
            
            Login user =
                    new Login(firstName, lastName,
                            username, password,
                            cellPhone);
            
            String registrationResult =
                    user.registerUser();
            
            System.out.println(" Registration Result ");
            System.out.println(registrationResult);
            
            /* Only allow login if registration passed */
            
            if (registrationResult.equals("Registration successful.")) {
                
                System.out.println("Chat Login");
                
                System.out.print(" Username: ");
                String loginUsername =
                        scanner.nextLine();
                
                System.out.print(" Password: ");
                String loginPassword =
                        scanner.nextLine();
                
                System.out.println("Login Result");
                
                System.out.println(
                        user.returnLoginStatus(
                                loginUsername,
                                loginPassword
                        )
                );
            }
        }
    }
}
