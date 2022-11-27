import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Variables defining the max sizes for each list of books.
        final int bookSize = 100;

        // All the necessary variables for the code to function.
        String response;
        String names;
        double price;
        Book book1;
        BookList bookList = new BookList(bookSize);
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the book program!");

        // The loop begins.
        while (true) {

            System.out.print("Would you like to create a book object? (yes/no): ");
            response = scan.nextLine();

            // Ensures that the only valid responses are in the form of yes or no.
            while (!(response.equalsIgnoreCase("no") || response.equalsIgnoreCase("yes"))) {

                System.out.print("I'm sorry but " + response + " isn't a valid answer. Please enter either yes or no: ");
                response = scan.nextLine();

            }

            // Saying no will stop the code.
            if (response.equalsIgnoreCase("no")) {

                System.out.print("Sure!\n\n");
                break;

            }

            // If yes, then continue on.
            else {

                System.out.print("Please enter the author, title, and the isbn of the book separated by /: ");
                names = scan.nextLine();
                String[] words = names.split("/");

                System.out.print("Got it!\nNow, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
                response = scan.nextLine();

                // Doesn't accept any response that isn't in the form of BB or LB.
                while (!(response.equalsIgnoreCase("BB") || response.equalsIgnoreCase("LB"))) {

                    System.out.print("Oops! That's not a valid entry. Please try again: ");
                    response = scan.nextLine();

                }

                // If BB is chosen and the list isn't full then create that BB object.
                if (response.equalsIgnoreCase("BB") && BookstoreBook1.getNumBook() + LibraryBook1.getNumBook() < bookSize) {

                    book1 = new BookstoreBook1(words[0], words[1], words[2]);
                    System.out.print("Got it!\nPlease enter the list price of " + book1.getTitle() + " by " + book1.getAuthor() + ": $");
                    price = Double.parseDouble(scan.nextLine());
                    ((BookstoreBook1) book1).setPrice(price);

                    System.out.print("Is it on sale? (y/n): ");
                    response = scan.nextLine();

                    // Will not accept responses that aren't in the form of y or n.
                    while (!(response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n"))) {

                        System.out.print("Not a valid entry! Please try again: ");
                        response = scan.nextLine();

                    }

                    if (response.equalsIgnoreCase("y")) {

                        System.out.print("Deduction percentage: ");
                        response = scan.nextLine();
                        ((BookstoreBook1) book1).setDiscount(Double.parseDouble(response.substring(0, response.length()-1))/100);

                    }

                    // Adds the book to the list.
                    System.out.println("Got it!\n\nHere is your bookstore book information\n" + book1 + "\n");
                    bookList.addBook(book1, BookstoreBook1.getNumBook() + LibraryBook1.getNumBook()-1);

                }

                // If LB was chosen.
                else if (BookstoreBook1.getNumBook() + LibraryBook1.getNumBook() < bookSize) {

                    // Adds the book to the LB list if the list isn't full.
                    book1 = new LibraryBook1(words[0], words[1], words[2]);
                    System.out.println("Got it!\n\nHere is your library book information\n" + book1 + "\n");
                    bookList.addBook(book1, BookstoreBook1.getNumBook() + LibraryBook1.getNumBook()-1);

                }

                else {

                    break;

                }

            }

        }

        System.out.println("Here are all your books...");
        System.out.println("Library Books (" + LibraryBook1.getNumBook() + ")");

        // Prints out the format of all of the books contained in each list from toString().
        for (int i = 0; i < BookstoreBook1.getNumBook() + LibraryBook1.getNumBook(); i++) {

            if (bookList.getBook(i) instanceof LibraryBook1) {

                System.out.println("    " + bookList.getBook(i));

            }
        }

        System.out.println("\nBookstore Books (" + BookstoreBook1.getNumBook() + ")");

        for (int i = 0; i < BookstoreBook1.getNumBook() + LibraryBook1.getNumBook(); i++) {

            if (bookList.getBook(i) instanceof BookstoreBook1) {

                System.out.println("    " + bookList.getBook(i));

            }

        }

        System.out.println("\nTake care now!");

    }

}
abstract class Book {

    private String author;
    private String title;
    private String isbn;

    public Book(String author, String title, String isbn) {

        this.author = author;
        this.title = title;
        this.isbn = isbn;

    }

    public void setAuthor(String newAuthor) {

        author = newAuthor;

    }

    public void setTitle(String newTitle) {

        title = newTitle;

    }

    public void setIsbn(String newIsbn) {

        isbn = newIsbn;

    }

    public String getAuthor() {

        return author;

    }

    public String getTitle() {

        return title;

    }

    public String getIsbn() {

        return isbn;

    }

    public String toString() {

        return "[" + isbn + "-" + title.toUpperCase() + " by " + author.toUpperCase();

    }

}
class BookstoreBook1 extends Book {

    // All the variables being the attributes of this class
    private double price;
    private boolean isOnSale;
    private double discount = 0;

    // A static int in order to maintain count of books of BB type.
    private static int numBook = 0;

    public BookstoreBook1(String author, String title, String isbn, double price, boolean isOnSale) {

        super(author, title, isbn);
        this.price = price;
        this.isOnSale = isOnSale;

    }

    // Constructor that is used to initialize the necessary attributes.
    public BookstoreBook1(String author, String title, String isbn) {

        super(author, title, isbn);
        numBook++;

    }

    public BookstoreBook1(String author, String title, String isbn, double random) {

        super(author, title, isbn);

    }

    // Sets the price of a BB book.
    public void setPrice(double newPrice) {

        price = newPrice;

    }

    // Sets the sale status of a BB book.
    public void setSaleStatus(boolean newStatus) {

        isOnSale = newStatus;

    }

    // Sets the discount percentage (in decimal) for the BB book.
    public void setDiscount(double newDiscount) {

        discount = newDiscount;

    }

    // Returns the price for the BB book.
    public double getPrice() {

        return price;

    }

    public boolean getSaleStatus() {

        return isOnSale;

    }

    // Returns the discount (in decimal) for the BB book.
    public double getDiscount() {

        return discount;

    }

    // Returns the number of books of BB type.
    public static int getNumBook() {

        return numBook;

    }

    // Gives the formatting if object is printed.
    @Override
    public String toString() {

        return  super.toString() + ", $" + price + " listed for $" + Math.round(price*(1-discount) * 100.0)/100.0 + "]";

    }

}

class LibraryBook1 extends Book {

    // All the class attributes.
    private static int numBook = 0;
    private final int floor = (int)(Math.random()*99)+1;

    // The used constructor that initializes the necessary variables.
    public LibraryBook1 (String author, String title, String isbn) {

        super(author, title, isbn);
        numBook++;

    }

    public LibraryBook1 (String author, String title, String isbn, int floor) {

        super(author, title, isbn);

    }

    public LibraryBook1 (String author, String title, String isbn, String random) {

        super(author, title, isbn);

    }

    // Returns the number of books of LB type.
    public static int getNumBook() {

        return numBook;

    }

    public int getFloor() {

        return floor;

    }

    // Formats the string when the object is being printed.
    @Override
    public String toString() {

        return super.toString() + "-" + floor + "." + getAuthor().substring(0, 3).toUpperCase() + "." + (Integer.parseInt(getIsbn())%10) + "]";

    }

}

class BookList {

    private Book[] list;

    public BookList(int bookSize) {

        list = new Book[bookSize];

    }

    public void addBook(Book books, int index) {

        list[index] = books;

    }

    public Book getBook(int index) {

        return list[index];

    }

}