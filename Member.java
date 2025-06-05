public class Member {
    private String name;
    private Book borrowedBook;  // Assuming one book at a time

    public Member(String name) {
        this.name = name;
        this.borrowedBook = null;
    }

    public String getName() {
        return name;
    }

    public boolean hasBorrowedBook() {
        return borrowedBook != null;
    }

    public String getBorrowedBookTitle() {
        if (borrowedBook != null) {
            return borrowedBook.getTitle();
        }
        return null;
    }

    public void borrowBook(Book book) {
        if (borrowedBook == null && book.isAvailable()) {
            borrowedBook = book;
            book.setAvailable(false);
        } else {
            System.out.println("Cannot borrow book.");
        }
    }

    public void returnBook() {
        if (borrowedBook != null) {
            borrowedBook.setAvailable(true);
            borrowedBook = null;
        }
    }
}
