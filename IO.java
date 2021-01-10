/* IO class was built to run the program*/
import java.util.Scanner;
import java.io.*;
public class IO {
    private final SortedArrayList<User> user;
    private final SortedArrayList<Book> book;
    User uu = new User("surname", "firstname", 0);
    Book bb = new Book("title", "author", 0,"firstName","lastName");

    IO(SortedArrayList<User> user, SortedArrayList<Book> book) throws IOException {
        this.user = uu.userIutput(user);
        this.book = bb.bookIutput(book);
    }

    public SortedArrayList<User> getUser() {
        return user;
    }

    public SortedArrayList<Book> getBook() {
        return book;
    }

    /*print user information*/
    private void printUser(){
        System.out.println("-- User List ["+user.size()+"] --");
        for (int i = 0; i < user.size(); i++) { //Traverse the user array
            User u = (User)user.get(i);
            System.out.println((i + 1) + "." + u.getFirstName() + " " + u.getSurname()+" (Own "+u.getBookNumber()+" books now)");
        }
    }

    /*print book information*/
    public void printBook() {
        System.out.println("-- Book List [" + book.size() + "] --");
        for (int i = 0; i < book.size(); i++) { //Traverse the book array
            Book b = (Book) book.get(i);
            if (b.getStatus() == 0) { //output whether the book is on loan or not
                System.out.println((i + 1) + ". '" + b.getTitle() + "' " + b.getAuthor() + " [borrowed by " + b.getFirstName() + " " + b.getLastName()+"]");
            } else {
                System.out.println((i + 1) + ". '" + b.getTitle() + "' " + b.getAuthor() + " [Available Now]");
            }
        }
    }

    /*check valid user*/
    private boolean checkUser(String userFirstName, String userSurname,String active) {
        boolean a = true;
        boolean b = true;
        for (int i = 0; i < user.size(); i++) { //Traverse the user array
            User u = (User)user.get(i);
            if (userFirstName.equals(u.getFirstName()) && userSurname.equals(u.getSurname())) { //check if it's valid
                System.out.println("Welcome User " + u.getFirstName() + " " + u.getSurname() + "!");
                if (active.equals("borrow")) {
                    if (u.getBookNumber() < 3) { //make sure one user can only hold no more than 3 books
                        b = false;
                    } else {
                        System.out.println("You can only hold 3 books at the same time!");
                    }
                    a = false;
                    break;
                }

                else {
                    a = false;
                    b = false;
                    break;
                }
            }
        }
        if (a == true) { //if it's invalid
            System.out.println("User doesn't exist");
        }
        return b;
    }

    /*check for valid book and user, if they both are valid, then borrow book. If book has been borrowed by others, send message to user who hold it*/
    private void borrowBook() throws IOException {
        boolean a = true;
        boolean a1 = true;
        Scanner x = new Scanner(System.in);
        System.out.println("You have selected option i");
        System.out.println("Please input your name");
        String userFirstName = x.next();
        String userSurname = x.next();
        boolean c1 = checkUser(userFirstName,userSurname,"borrow"); //check valid user
        if(c1 == false) {
            x.nextLine();
            System.out.println("Please enter the title of the book you want to borrow");
            String bookTitle = x.nextLine();
            System.out.println("Please enter the author name of the book you want to borrow");
            String bookAuthor = x.nextLine();
            for (int i = 0; i < book.size(); i++) { //Traverse the book array
                Book b = (Book)book.get(i);
                int su = bookTitle.compareTo(b.getTitle()); //check if the book is valid
                int fu = bookAuthor.compareTo(b.getAuthor());
                if (su == 0 && fu == 0) {
                    System.out.println("Valid Book '"+bookTitle+"'");
                    if (b.getStatus()== 1) { //if the book is not on loan, then the user can borrow it
                        a1 = false;
                    } else {
                        output(b.getFirstName(),b.getTitle()); //if it's on loan, send reminder to the person who borrowed it
                        System.out.println("Sorry, this book is being used by others, and we will tell him/her to return it as soon as possible.");
                    }
                    a = false;
                    break;
                }
            }
            if (a == true) { //if it's not valid
                System.out.println("Book doesn't exist");
            }
            if (a1 == false) { //it can be borrowed by user now
                uu.uBorrowBook(user,userFirstName,userSurname);
                bb.bBorrowBook(book,bookTitle,bookAuthor,userFirstName,userSurname);
                System.out.println("[borrow book '" + bookTitle + "' successfully]");
            }
        }
    }

    /*check valid book and user, then return book if fulfill all the requirements*/
    private void returnBook() {
        Scanner x = new Scanner(System.in);
        System.out.println("You have selected option r");
        System.out.println("Please input your name");
        String userFirstName1 = x.next();
        String userSurname1 = x.next();
        boolean c3 = checkUser(userFirstName1,userSurname1,"return"); //check valid user
        if(c3 == false) {
            x.nextLine();
            System.out.println("Please enter the title of the book you want to return");
            String bookTitle1 = x.nextLine();
            System.out.println("Please enter the author name of the book you want to return");
            String bookAuthor1 = x.nextLine();
            boolean a = true;
            boolean a1 = true;
            for (int i = 0; i < book.size(); i++) { //Traverse the book array
                Book b = (Book)book.get(i);
                int su = bookTitle1.compareTo(b.getTitle());
                int fu = bookAuthor1.compareTo(b.getAuthor());
                if (su == 0 && fu == 0) {
                    if (b.getStatus() == 0) { //check whether the book is on loan or not
                        a1 = false;
                    } else {
                        System.out.println("The book has not been borrowed at the moment, you can't return it.");
                    }
                    a = false;
                    break;
                }
            }

            if (a == true) {
                System.out.println("Book doesn't exist");
            }
            if (a1 == false) {
                uu.uReturnBook(user,userFirstName1,userSurname1); //update book array if meet all requirements
                bb.bAddBook(book,bookTitle1,bookAuthor1,userFirstName1,userSurname1); //update user array if meet all requirements
            }
        }
}

    /*a reminder to whom holds a book that others request*/
    private void output(String userFirstName, String bookTitle) throws FileNotFoundException {
        PrintWriter outFile = new PrintWriter("results.txt");
        outFile.println("Dear " + userFirstName+ ", "+"\n"+
                "   Another library member is requesting book '"+bookTitle+"', please return it in time."+"\n"+
                "Fake Library"); //write a message to whom hold the book that others request
        outFile.close();
    }

    private void menu() throws IOException {
        Scanner x = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Menu");
            System.out.println("f. to finish running the program");
            System.out.println("b. to display on the screen the information about all the books in the library");
            System.out.println("u. to display on the screen the information about all the users");
            System.out.println("i. to update the stored data when a book is issued to a user");
            System.out.println("r. to update the stored data when a user returns a book to the library");
            System.out.println("Please choose an option");
            String a = x.next();
        switch (a) {
            case "f":
                System.out.println("You have selected option f");
                System.out.println("Exiting now...");
                flag = false;
                break;
                    
            case "b":
                System.out.println("You have selected option b");
                printBook();
                continue;

            case "u":
                System.out.println("You have selected option u");
                printUser();
                continue;

            case "i":
                    borrowBook();
                    continue;
                
            case "r":
                    returnBook();
                    continue;

            default:
                System.out.println("Wrong input, please input an option gain!");
                continue;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        SortedArrayList<User> user = new SortedArrayList<User>();
        SortedArrayList<Book> book = new SortedArrayList<Book>();
        
        new IO(user,book).menu();//run program
    }
}
