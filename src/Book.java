/* Book class was built to store book information and update books' information when calling for borrow/return books in menu*/
import java.io.*;
import java.util.Scanner;

public class Book implements Comparable<Book> {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private int status;
    private String firstName;
    private String lastName;

    Book(String title,String authorFirstName,String authorLastName,int status,String firstName,String lastName){
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public int getStatus() {
        return status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /*read and store book information from text*/
    public static void read(String[] titles,String[] authorFirstNames,String[] authorLastNames) throws IOException {
        int i = 1;
        int a = 0;
        int b = 0;
        Scanner s = new Scanner(new FileReader("booklist and userlist.txt"));
        s.nextLine();
        while (s.hasNextLine()) {
            i++;
            if (i < 12) { //record the book information from the first line to line 11 in the text file
                if(i % 2 == 1){ //read odd rows
                    authorFirstNames[a] = s.next(); //separate author's firstname and lastname and store them in arrays
                    authorLastNames[a] = s.nextLine();
                    a++;
                }
                else if(i % 2 == 0) { //read even rows
                    titles[b] = s.nextLine(); //store book titles in an array
                    b++;
                }
                continue;
            }
            else break;
        }
    }

    /*store book information in a SortedArrayList*/
    public SortedArrayList<Book> bookIutput(SortedArrayList<Book> book) throws IOException {
        String[] titles = new String[5];
        String[] authorFirstNames = new String[5];
        String[] authorLastNames = new String[5];
        Integer[] statusAll = new Integer[5];
        String[] lastNames = new String[5]; //store information that which user has borrowed this book
        String[] firstNames = new String[5];

        statusAll[0] = 1; //1 means this book is not be issued, while 0 means it's being borrowed at the moment.
        statusAll[1] = 1;
        statusAll[2] = 1;
        statusAll[3] = 1;
        statusAll[4] = 1;

        lastNames[0] = null; //means books are not been borrowed at the beginning
        lastNames[1] = null;
        lastNames[2] = null;
        lastNames[3] = null;
        lastNames[4] = null;

        firstNames[0] = null;
        firstNames[1] = null;
        firstNames[2] = null;
        firstNames[3] = null;
        firstNames[4] = null;

        File name = new File("booklist and userlist.txt");
        FileReader fileReader = new FileReader(name);
        LineNumberReader reader = new LineNumberReader(fileReader);

        new Book("title","authorF","authorL",1,"lastname","firstname").read(titles,authorFirstNames,authorLastNames);

        Book b1 = new Book(titles[0],authorFirstNames[0],authorLastNames[0],statusAll[0],firstNames[0], lastNames[0]);
        Book b2 = new Book(titles[1],authorFirstNames[1],authorLastNames[1],statusAll[1],firstNames[1], lastNames[1]);
        Book b3 = new Book(titles[2],authorFirstNames[2],authorLastNames[2],statusAll[2],firstNames[2], lastNames[2]);
        Book b4 = new Book(titles[3],authorFirstNames[3],authorLastNames[3],statusAll[3],firstNames[3], lastNames[3]);
        Book b5 = new Book(titles[4],authorFirstNames[4],authorLastNames[4],statusAll[4],firstNames[4], lastNames[4]);

        /*add books to an arraylist*/
        book.insertionSort(book,b1); //insert a book and put it in a right place
        book.insertionSort(book,b2);
        book.insertionSort(book,b3);
        book.insertionSort(book,b4);
        book.insertionSort(book,b5);

        return book;
    }

    /*Return Book*/
    public void bAddBook(SortedArrayList<Book> book,String bookTitle, String bookAuthor,String userFirstName,String userSurname) {
        for (int i = 0; i < book.size(); i++) {
            Book b = (Book)book.get(i);
            String author = b.getAuthorFirstName()+b.getAuthorLastName();
            if (bookTitle.equals(b.getTitle()) && bookAuthor.equals(author)) { //find the book that user wants to return
                if (userFirstName.equals(b.getFirstName()) && userSurname.equals(b.getLastName())) { //make sure the user is the one who borrowed this book
                    b.status = b.getStatus() - 1; //add book amount
                    b.lastName = null;
                    b.firstName = null;
                    book.set(i, b);
                    System.out.println("[return book '" + b.getTitle() + "' successfully]");
                } else {
                    System.out.println("Sorry, you cannot return books for others.");
                }
            }
        }
    }

    /*Borrow Book*/
    public void bBorrowBook(SortedArrayList<Book> book,String bookTitle, String bookAuthor,String userFirstName,String userSurname){
        for (int i = 0; i < book.size(); i++) {
            Book b = (Book)book.get(i);
            String author = b.getAuthorFirstName()+b.getAuthorLastName();
            if (bookTitle.equals(b.getTitle()) && bookAuthor.equals(author)) { //find the book that user wants to borrow
                b.status = b.getStatus() - 1; //reduce book amount
                b.lastName = userSurname;
                b.firstName = userFirstName;
                book.set(i, b);
            }
        }
    }

    public static void main(String[] args){

    }

    @Override
    public int compareTo(Book b) { //sort books by their authors' surnames
        int d = authorLastName.compareTo(b.authorLastName);
        if(d != 0) return d;
        else return 0;
    }
}
