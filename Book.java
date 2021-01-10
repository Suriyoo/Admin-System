/* Book class was built to store book information and update book's information when calling for borrow/return books in menu*/
import java.io.*;
import java.util.Scanner;

public class Book {
    private String title;
    private String author;
    private int status;
    private String firstName;
    private String lastName;
    

    Book(String title,String author,int status,String fistName,String lastName){
        this.title = title;
        this.author = author;
        this.status = status;
        this.firstName = fistName;
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
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
    public static void read(String[] titles,String[] authorFirstNames,String[] authorSurames) throws IOException {
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
                    authorSurames[a] = s.nextLine();
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
    public static SortedArrayList<Book> bookIutput(SortedArrayList<Book> book) throws IOException {
        String[] titles = new String[5];
        String[] authors = new String[5];
        String[] authorFirstNames = new String[5];
        String[] authorSurames = new String[5];
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

        new Book("title","author",1,"lastname","firstname").read(titles,authorFirstNames,authorSurames);
        
        book.insertionSort(authorSurames,authorFirstNames,titles);////sort books by their titles, if books have common title, then sort them by their authors' surnames
        for(int i = 0; i < authors.length; i++) {
            authors[i] = authorFirstNames[i]+authorSurames[i]; //store authors' firstnames and lastnames together as an array
        }
        Book b1 = new Book(titles[0],authors[0],statusAll[0],firstNames[0], lastNames[0]);
        Book b2 = new Book(titles[1],authors[1],statusAll[1],firstNames[1], lastNames[1]);
        Book b3 = new Book(titles[2],authors[2],statusAll[2],firstNames[2], lastNames[2]);
        Book b4 = new Book(titles[3],authors[3],statusAll[3],firstNames[3], lastNames[3]);
        Book b5 = new Book(titles[4],authors[4],statusAll[4],firstNames[4], lastNames[4]);
        
        book.add(b1);
        book.add(b2);
        book.add(b3);
        book.add(b4);
        book.add(b5);
        
        return book;
    }

    /*Return Book*/
    public void bAddBook(SortedArrayList<Book> book,String bookTitle, String bookAuthor,String userFirstName,String userSurname) {
        for (int i = 0; i < book.size(); i++) {
            Book b = (Book)book.get(i);
            if (bookTitle.equals(b.getTitle()) && bookAuthor.equals(b.getAuthor())) { //find the book that user wants to return
                if (userFirstName.equals(b.getFirstName()) && userSurname.equals(b.getLastName())) { //make sure the user is the one who borrowed this book
                    b.status = b.getStatus() - 1;
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
            if (bookTitle.equals(b.getTitle()) && bookAuthor.equals(b.getAuthor())) {
                b.status = b.getStatus() - 1;
                b.lastName = userSurname;
                b.firstName = userFirstName;
                book.set(i, b);
            }
        }
    }

    public static void main(String[] args){
        
        
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
