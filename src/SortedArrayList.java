/* SortedArrayList class was built to sort arrays as required*/
import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<? super E>> extends ArrayList {

    /*book or user array sorting in an ascending order*/
    public void insertionSort(SortedArrayList<E> a, E e) {
        a.add(e); //firstly put it in the last position of array
        for (int i = a.size()-1; i >=1; i--) {
            E x = (E) a.get(i-1);
                if (x.compareTo(e)<0) { //e is already larger than the biggest value by now, so no need to move it
                    break;
                }
                else {
                    a.set(i,x); //if e is smaller than the biggest value by now, exchange their positions
                    a.set(i-1,e);
                }

        }
    }


    public static void main(String[] args){
    }

}
