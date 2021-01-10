/* User class was built to store user information and update user's information when calling for borrow/return books in menu*/
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class User{
    private String firstName;
    private String surname;
    private int bookNumber;

    User(String surname, String firstName, int bookNumber) {
        this.surname = surname;
        this.firstName = firstName;
        this.bookNumber = bookNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    /*read and store user information from text*/
    public void record(String[] surnames, String[] firstNames) throws FileNotFoundException {
        int i = 0;
        int a = 0;
        Scanner s = new Scanner(new FileReader("booklist and userlist.txt"));
        while (s.hasNextLine()) {
            i++;
            s.nextLine();
            if (i >= 12) { //start to record user information from line 13 to the end line of provided text file
                firstNames[a] = s.next(); //separate user's firstname and lastname and store them in arrays
                surnames[a] = s.next();
                a++;
            } else continue;
        }
    }

    /*store user information in a SortedArrayList*/
    public static SortedArrayList<User> userIutput(SortedArrayList<User> user) throws FileNotFoundException {
        String[] surnames = new String[4];
        String[] firstNames = new String[4];
        Integer[] hold = new Integer[4];
        hold[0] = 0;//the number of books each user already borrowed
        hold[1] = 0;
        hold[2] = 0;
        hold[3] = 0;

        new User("surname", "firstname", 0).record(surnames, firstNames);
        user.insertionSort(surnames, firstNames, null); //sort users by their surnames, if users have common surname, then sort them by their firstnames

        User u1 = new User(surnames[0], firstNames[0], hold[0]);
        User u2 = new User(surnames[1], firstNames[1], hold[1]);
        User u3 = new User(surnames[2], firstNames[2], hold[2]);
        User u4 = new User(surnames[3], firstNames[3], hold[3]);
        user.add(u1);//add users to an arraylist
        user.add(u2);
        user.add(u3);
        user.add(u4);

        return user;
    }

    /*user borrow book*/
    public void uBorrowBook(SortedArrayList<User> user, String userFirstName, String userSurname) {
        for (int i = 0; i < user.size(); i++) {
            User u = (User) user.get(i);
            if (userFirstName.equals(u.getFirstName()) && userSurname.equals(u.getSurname())) {
                u.bookNumber = u.getBookNumber() + 1;
                user.set(i, u);
            }
        }
    }

    /*user return book*/
    public void uReturnBook(SortedArrayList<User> user, String userFirstName, String userSurname) {
        for (int i = 0; i < user.size(); i++) {
            User u = (User) user.get(i);
            if (userFirstName.equals(u.getFirstName()) && userSurname.equals(u.getSurname()) && u.getBookNumber() > 0) {
                u.bookNumber = u.getBookNumber() - 1;
                user.set(i, u);
            }
        }
    }

    public static void main(String[] args){

    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", bookNumber=" + bookNumber +
                '}';
    }
}

