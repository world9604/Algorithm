package etc;

public class PowerSet {

    public static void main(String[] args) {
        powerSet(0);
    }

    private static char[] data = {'a', 'b', 'c', 'd', 'e'};
    private static int n = data.length;
    private static boolean[] include = new boolean[n];

    public static void powerSet( int k ) {
        if ( k == n ) {
            for (int i = 0; i < n; i++)
                if (include[ i ]) System.out.print( data[i] + " " );
            System.out.println();
            return;
        }
        include[ k ] = false;  // powerSet( {a}, {d, e, f} );
        powerSet( k + 1 );
        include[ k ] = true;  // powerSet( {a, c}, {d, e, f} );
        powerSet( k + 1 );
    }

}
