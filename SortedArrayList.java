/* SortedArrayList class was built to sort arrays as required*/
import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList {

    /*book or user array sorting in the ascending order*/
    public  <E extends Comparable<? super E>> void insertionSort(E[] a, E[] b, E[] c) {
        if(c == null){//if array C is null, there's no need to use array c when sorting array a and b
            for (int k = 1; k < a.length; k++) {
                E x = a[k];
                E y = b[k];
                int l;
                for (l = k; l > 0; l--) {
                    if (a[l - 1].compareTo(x) > 0) { //compare the adjacent elements in array a, and replace the smaller value to left side
                        a[l] = a[l - 1];
                        b[l] = b[l - 1];
                    } else if (a[l - 1].compareTo(x) < 0) { //compare the adjacent elements, if the smaller element is already at left side,just keep it
                        break;
                    } else {
                        if (b[l - 1].compareTo(y) >= 0) {  //compare the adjacent elements,if they're are equal, compare their corresponding elements in array b
                            a[l] = a[l - 1];
                            b[l] = b[l - 1];
                            c[l] = c[l - 1];
                        } else break;
                    }
                }
                a[l] = x;
                b[l] = y;
            }
        }
        else { //if array c is not null, need to associate each item in c with items in array a and b while doing sorting
            for (int i = 1; i < a.length; i++) {
                E x = a[i];
                E y = b[i];
                E z = c[i];
                int j;
                for (j = i; j > 0; j--) {
                    if (a[j - 1].compareTo(x) > 0) {
                        a[j] = a[j - 1];
                        b[j] = b[j - 1];
                        c[j] = c[j - 1];
                    } else if (a[j - 1].compareTo(x) < 0) {
                        break;
                    } else {
                        if (b[j - 1].compareTo(y) >= 0) {
                            a[j] = a[j - 1];
                            b[j] = b[j - 1];
                            c[j] = c[j - 1];
                        } else break;
                    }
                }
                a[j] = x;
                b[j] = y;
                c[j] = z;
            }
        }
    }

    public static void main(String[] args){


    }
}
