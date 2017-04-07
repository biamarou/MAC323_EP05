import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private int[][] matrix;
    private int n;
    private WeightedQuickUnionUF uf;
    
    Percolation (int matrix_n) {

        uf = new WeightedQuickUnionUF(matrix_n*matrix_n + 1);
	matrix = new int[matrix_n][matrix_n];
	n = matrix_n;

	for (int i = 0; i < n; i++) 
	    for (int j = 0; j < n; j++)
		matrix[i][j] = 0;

	for (int i = 1; i < n + 1; i++)
	    uf.union(0, i);
      	
    }

    public boolean validate (int row, int col) {

	if (row < 0 || row > n - 1 || col < 0 || col > n - 1)
	    return false;
	else
	    return true;
    }

    public void open (int row, int col) {
	   
       	matrix [row][col] = 1;
	
	if (isOpen(row, col + 1))
	    uf.union(n*row + col + 2, n*row + col + 1);
	    

	if (isOpen(row, col - 1))
	    uf.union(n*row + col, n*row + col + 1);
	   

	if (isOpen(row + 1, col)) 
	    uf.union(n*(row + 1) + col + 1, n*row + col + 1);
	    

	if (isOpen(row - 1, col))
	    uf.union(n*(row - 1) + col + 1, n*row + col + 1);	    
	
    }
    
    public boolean percolates () {
	for (int i = n*n - n + 1 ; i < n*n + 1; i++) {
	    if (uf.connected(0, i)) return true;
	}
	return false;
    }

    public boolean isFull (int row, int col) {
	StdOut.print(row);
	StdOut.print(" ");
	StdOut.print(col);
	StdOut.println();
	if (uf.connected(0, n*row - (n - col))) return true;
	return false;
    }

    public boolean isOpen (int row, int col) {

	if (!validate(row, col)) return false;
	return matrix[row][col] == 1;
    }

}

