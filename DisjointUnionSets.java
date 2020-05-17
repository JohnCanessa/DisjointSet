import java.util.Arrays;
import java.util.Scanner;

/**
 * Disjpoint Union Sets class
 */
public class DisjointUnionSets {

    // **** class members ****
    int[] rank;
    int[] parent;
    int n;


    /**
     * Constructor.
     */
    public DisjointUnionSets(int n) {

        // **** ****
        this.rank = new int[n];
        this.parent = new int[n];
        this.n = n;

        // **** make the set ****
        makeSet();
    }


    /**
     * Create n sets with a sungle item in each.
     */
    private void makeSet() {

        // **** all elements are in their own set ****
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }


    /**
     * Returns representative of x's set 
     */
    int find(int x) { 

        // **** ****
        if (parent[x] != x) { 
            parent[x] = find(parent[x]); 
        } 

        // **** ****
        return parent[x]; 
    } 

    
    /**
     * Unites the set that includes x and the set that includes x 
    */
    void union(int x, int y) {

        // **** find representatives of the two sets ****
        int xRoot = find(x);
        int yRoot = find(y); 

        // ???? ????
        System.out.println("union <<< xRoot: " + xRoot + " yRoot: " + yRoot);

        // **** if elements in the same set; nothing else to do ****
        if (xRoot == yRoot) 
            return; 
  
        // ???? ????
        System.out.println("union <<< rank[" + xRoot + "]: " + rank[xRoot]);
        System.out.println("union <<< rank[" + yRoot + "]: " + rank[yRoot]);

        // **** if x's rank is less than y's rank ****
        if (rank[xRoot] < rank[yRoot]) 
  
            // **** move x under y so that depth of tree remains less 
            parent[xRoot] = yRoot; 
  
        // **** if x's rank is greater than y's rank ****
        else if (rank[xRoot] > rank[yRoot]) 
  
            // **** move y under x so that depth of tree remains less ****
            parent[yRoot] = xRoot; 
        
        // **** if ranks are the same ****
        else { 

            // **** move y under x (doesn't matter which one goes where) ****
            parent[yRoot] = xRoot; 
  
            // **** increment the result tree's rank by 1 ****
            rank[xRoot] = rank[xRoot] + 1; 
        } 
    } 


    /**
     * Return a string representation of the object.
     */
    public String toString() {

        // **** ****
        StringBuilder sb = new StringBuilder();

        sb.append("\n     n: " + this.n);
        sb.append("\n  rank: " + Arrays.toString(this.rank));
        sb.append("\nparent: " + Arrays.toString(this.parent));

        // **** ****
        return sb.toString();
    }


    /**
     * Test scaffolding
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read the number of elements ****
        int n = Integer.parseInt(sc.nextLine());

        // ??? ?????
        System.out.println("main <<< n: " + n);

        // **** instantiate the class ****
        DisjointUnionSets dus = new DisjointUnionSets(n);

        // ???? ????
        System.out.println("main <<< dus: " + dus.toString() + "\n");

        // **** loop reading friendships (list MUST end with empty line) ****
        String s;
        do {

            // **** read the next relationship ****
            s = sc.nextLine();

            // **** remove leading and trailing blanks characters ****
            s = s.trim();

            // **** process union (if present) ****
            if (!s.isEmpty()) {

                // **** extract x and y ****
                String[] ss = s.split(" ");
                int x = Integer.parseInt(ss[0]);
                int y = Integer.parseInt(ss[1]);

                // ???? ????
                System.out.println("main <<< x: " + x + " y: " + y);

                // **** ****
                dus.union(x, y);

                // ???? ????
                System.out.println();
            }
        } while(!s.isEmpty());

        // ???? ????
        System.out.println("main <<< dus: " + dus.toString() + "\n");

        // **** loop reading and checking relationships (list MUST end with empty line) ****
        do {

            // **** read the next relationship ****
            s = sc.nextLine();

            // **** remove leading and trailing blanks characters ****
            s = s.trim();

            // **** process union (if present) ****
            if (!s.isEmpty()) {

              // **** extract x and y ****
              String[] ss = s.split(" ");
              int x = Integer.parseInt(ss[0]);
              int y = Integer.parseInt(ss[1]);

              // ???? ????
              System.out.println("main <<< x: " + x + " y: " + y);

              // **** check if x and y are friends ****
              if (dus.find(x) == dus.find(y)) {
                  System.out.println("x: " + x + " and y: " + y + " friends");
              } else {
                  System.out.println("x: " + x + " and y: " + y + " NOT friends");
              }

              // ???? ????
              System.out.println();
            }

        } while (!s.isEmpty());

        // **** close scanner ****
        sc.close();
    }
}