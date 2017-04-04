import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Random;

public class Percolation {

    private int[][] matrix;
    private int[][] parent;
    private int[][] size;
    private int count;

    Percolation (int n) {

	matrix = new int[n][n];
	parent = new int[n][n];
	size = new size[n][n];
	count = n*n;

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

    public boolean validateUnion (int row1, int col1, int row2, int col2) {
	int diff_row = row1 - row2;
	int diff_col = col1 - col2;

	if (isOpen(row1, col1) && isOpen(row2, col2)) {
	    if (diff_row == 0 && (diff_coll == 1 || diff_col == -1))
		return true;
	    if (diff_col == 0 && (diff_row == 1 || diff_row == -1))
		return true;
        }
        else
	    return false;
    }

    public void union (int row1, int col1, int row2, int col2) {
	int root1 = find(row1, col1);
	int root2 = find(row2, col2);

	if (root1 == root2) return;
	if (!validateUnion(row1, col1, row2, col2)) return;
	
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

    public void open (int row, int col) {
	if (matrix[row][col] == 0) {
	    if (row == 0)
		matrix [row][col] = 2;
	    else
		matrix [row][col] = 1;
	}
    }

    public boolean isFull (int row, int col) {
	
    }

    public boolean isOpen (int row, int col) {

    }
}
