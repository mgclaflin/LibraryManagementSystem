import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength = 12
        String adminPassword = encoder.encode("admin123");
        String memberPassword = encoder.encode("password123");
        String librarianPassword = encoder.encode("librarian123");

        System.out.println("Admin: " + adminPassword);
        System.out.println("Member: " + memberPassword);
        System.out.println("Librarian: " + librarianPassword);
    }
}