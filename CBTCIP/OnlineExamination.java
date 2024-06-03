import java.util.Scanner;
import java.util.HashMap;

class User {
    String username;
    String password;
    String profile;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.profile = "Default Profile";
    }
}

class OnlineExaminationSystem {
    private HashMap<String, User> users = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public void start() {
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
			System.out.println("Choose an Option ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                break;
            }
        }
    }

    private void login() {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            currentUser = users.get(username);
            System.out.println("Login successful!");
            userMenu();
        } else {
            System.out.println("Invalid username or password!");
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
        } else {
            users.put(username, new User(username, password));
            System.out.println("Registration successful!");
        }
    }

    private void userMenu() {
        while (true) {
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");
			System.out.println("Choose an Option");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                updateProfile();
            } else if (choice == 2) {
                updatePassword();
            } else if (choice == 3) {
                startExam();
            } else {
                currentUser = null;
                break;
            }
        }
    }

    private void updateProfile() {
        System.out.print("Enter new profile information: ");
        String newProfile = scanner.nextLine();
        currentUser.profile = newProfile;
        System.out.println("Profile updated successfully!");
    }

    private void updatePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        currentUser.password = newPassword;
        System.out.println("Password updated successfully!");
    }

    private void startExam() {
        String[] questions = {
            "Question 1: What is 2 + 2? \n a) 3 \n b) 4 \n c) 5 \n d) 6",
            "Question 2: What is the capital of France? \n a) London \n b) Berlin \n c) Paris \n d) Madrid"
        };
        char[] correctAnswers = {'b', 'c'};
        char[] userAnswers = new char[questions.length];

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            userAnswers[i] = scanner.nextLine().charAt(0);
        }

        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }

        System.out.println("You scored: " + score + "/" + questions.length);
    }
}

public class OnlineExamination {
    
    public static void main(String[] args) {
        OnlineExaminationSystem system = new OnlineExaminationSystem();
        system.start();
    }
}

