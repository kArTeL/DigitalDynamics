package com.soin.digitaldynamics.Util;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by neilgarciavargas on 21/6/16.
 */
public class ArrayUtil {

    // Implementing Fisher–Yates shuffle
    public static <E> void shuffleArray(ArrayList<E> ar)
    {
        // If running on Java 6 or older, use `c` on RHS here
        Random rnd = new Random();
        for (int i = ar.size() - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap

            //Object a = ar[index];
            E a = ar.get(index);
            ar.set(index,ar.get(i));

           // ar[index] = ar[i];
            ar.set(i, a);
            //ar[i] = a;
        }
    }
}
