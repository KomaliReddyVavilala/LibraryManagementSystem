import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();

        // Add some initial books
        library.addBook(new Book("Java Basics", "James Gosling"));
        library.addBook(new Book("Effective Java", "Joshua Bloch"));
        library.addBook(new Book("Clean Code", "Robert C. Martin"));
        library.addBook(new Book("Head First Java", "Kathy Sierra"));
        library.addBook(new Book("Java Concurrency in Practice", "Brian Goetz"));
        library.addBook(new Book("Design Patterns", "Erich Gamma"));
        library.addBook(new Book("Algorithms", "Robert Sedgewick"));
        library.addBook(new Book("Introduction to Algorithms", "Cormen, Leiserson, Rivest, Stein"));
        library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt"));
        library.addBook(new Book("Refactoring", "Martin Fowler"));

        // No initial members needed; users will register on the fly

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Library Menu ====");
            System.out.println("1. Show All Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (choice == 1) {
                library.showBooks();

            } else if (choice == 2) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                Member member = library.getMember(name);

                if (member == null) {
                    System.out.println("Member not found. Registering new member...");
                    member = new Member(name);
                    library.addMember(member);
                }

                if (member.hasBorrowedBook()) {
                    System.out.println("You already have a book: " + member.getBorrowedBookTitle());
                } else {
                    System.out.print("Enter book title to issue: ");
                    String title = scanner.nextLine();
                    Book book = library.getAvailableBook(title);

                    if (book == null) {
                        System.out.println("Book not available or does not exist.");
                        System.out.println("Here are the books currently in the library:");
                        library.showBooks();
                    } else {
                        member.borrowBook(book);
                        System.out.println("Book issued successfully.");
                    }
                }

            } else if (choice == 3) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                Member member = library.getMember(name);

                if (member == null) {
                    System.out.println("Member not found.");
                } else if (!member.hasBorrowedBook()) {
                    System.out.println("You have no books to return.");
                } else {
                    member.returnBook();
                    System.out.println("Book returned successfully.");
                }

            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
