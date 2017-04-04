import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    private int[][] matrix;
    private int[][] parent;
    private int[][] size;
    private int count;
    private int matrix_n;

    Percolation (int n) {

	matrix = new int[n][n];
	parent = new int[n][n];
	size = new int[n][n];
	count = n*n;
	matrix_n = n;

	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		matrix[i][j] = 0;
		parent[i][j] = 10*i + j;
		size[i][j] = 1;
	    }
	}
    }

    public int count () {
	return count;
    }

    public int find (int row, int col) {
	while (parent[row][col] != 10*row + col) {
	    col = parent[row][col] % 10;
	    row = (parent[row][col] - col)/10;
	}

	return(10*row + col);
    }
    
    public boolean connected (int row1, int col1, int row2, int col2) {
	return find(row1, col1) == find(row2, col2);
    }

    public void union (int row1, int col1, int row2, int col2) {
	int root1 = find(row1, col1);
	int root2 = find(row2, col2);

	if (root1 == root2) return;
	
	if (size[row1][col1] < size[row2][col2]) {
	    parent[row1][col1] = root2;
	    size[row1][col1] += size[row2][col2];
	}

	else {
	    parent[row2][col2] = root1;
	    size[row2][col2] += size[row1][col1];
	}
	count--;
    }

    public boolean validate (int row, int col) {
	if (row < 0 || row > matrix_n - 1 || col < 0 || col > matrix_n - 1)
	    return false;
	else
	    return true;
    }

    public void open (int row, int col) {

	boolean full = false;
	
	if (!validate(row, col)) return;

	if (matrix[row][col] == 0) {
	    if (row == 0)
		matrix [row][col] = 2;
	    else
		matrix [row][col] = 1;
	}
	
	if (isOpen(row, col + 1) || (full = isFull(row, col + 1))) {
	    union(row, col + 1, row, col);
	    if (full)
		matrix[row][col] = 2;
	}

	if (isOpen(row, col - 1) || (full = isFull(row, col - 1))) {
	    union(row, col - 1, row, col);
	    if (full)
		matrix[row][col] = 2;
	}

	if (isOpen(row + 1, col) || (full = isFull(row + 1, col))) {
	    union(row + 1, col, row, col);
	    if (full)
		matrix[row][col] = 2;
	}

	if (isOpen(row - 1, col) || (full = isFull(row - 1, col))) {
	    union(row - 1, col, row, col);
	    if (full)
		matrix[row][col] = 2;
	}
    }

    public boolean isFull (int row, int col) {

	if (!validate(row, col)) return false;
	return matrix[row][col] == 2;
    }

    public boolean isOpen (int row, int col) {

	if (!validate(row, col)) return false;
	return matrix[row][col] == 1;
    }

    public void printa(){
	for (int i = 0; i < matrix_n; i++) {
	    for (int j = 0; j < matrix_n; j++) {
		StdOut.print(parent[i][j] + " ");
	    }
	    StdOut.println();
	}
	StdOut.println();

	for (int i = 0; i < matrix_n; i++) {
	    for (int j = 0; j < matrix_n; j++) {
		StdOut.print(matrix[i][j] + " ");
	    }
	    StdOut.println();
	}
    }

    public static void main (String[] args) {

	Percolation perc = new Percolation(StdIn.readInt());
	int i, j;
	
	while (!StdIn.isEmpty()) {
	    StdOut.println();
	    i = StdIn.readInt();
	    j = StdIn.readInt();
	    perc.open(i,j);
	    perc.printa();
	    
	}

    }

}


