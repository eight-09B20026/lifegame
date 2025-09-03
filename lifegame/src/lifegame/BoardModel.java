package lifegame;

public class BoardModel{
	private int sum;
	private int cols;
	private int rows;
	private int pastIndex;
	private int undoNum;
	private boolean[][] cells;
	private boolean[][] tmpCells;
	private boolean[][][] pastCells;

	
	public BoardModel(int c, int r) {
		this.cols = c;
		this.rows = r;
		this.pastIndex = 0;
		this.undoNum = 0;
		this.cells = new boolean[rows][cols];
		this.tmpCells = new boolean[rows][cols];
		this.pastCells = new boolean[rows][cols][32];
	}
	
	public int getCols() {
		return this.cols;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public synchronized void changeCellState(int c, int r) {
		this.cells[c][r] =! this.cells[c][r];
	}
	
	public synchronized void next() {
		for(int i=0; i < this.rows; i++) {
			System.arraycopy(this.cells[i], 0, this.tmpCells[i], 0, this.cols);
		}
		
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.cols; j++) {
				sum = 0;
				
				if(i-1 >= 0) {
					if(j-1 >= 0) {
						if(this.tmpCells[i-1][j-1]==true) {
							sum++;
						}
					}
					
					if(this.tmpCells[i-1][j]==true) {
						sum++;
					}
					if(j+1 < this.cols) {
						if(this.tmpCells[i-1][j+1]==true) {
							sum++;
						}
					}
				}
				if(j-1 >= 0) {
					if(this.tmpCells[i][j-1]==true) {
						sum++;
					}
				}
				if(j+1 < this.cols) {
					if(this.tmpCells[i][j+1]==true) {
						sum++;
					}
				}
				if(i+1 < this.rows) {
					if(j-1 >= 0) {
						if(this.tmpCells[i+1][j-1]==true) {
							sum++;
						}
					}
					
					if(this.tmpCells[i+1][j]==true) {
						sum++;
					}
					if(j+1 < this.cols) {
						if(this.tmpCells[i+1][j+1]==true) {
							sum++;
						}
					}
				}
				
				if(sum==3) {
					this.cells[i][j] = true;
				}else if(sum==2) {
					this.cells[i][j] = this.tmpCells[i][j];
				}else {
					this.cells[i][j] = false;
				}
			}
		}
		for(int i=0; i < this.rows; i++) {
			for(int j=0; j < this.cols; j++) {
				this.pastCells[i][j][pastIndex] = this.tmpCells[i][j];
			}
		}
		if(this.pastIndex < 31) {
			this.pastIndex++;
		}else {
			this.pastIndex = 0;
		}
		if(undoNum < 32) {
			this.undoNum++;
		}
	}
	
	public synchronized void undo() {
		if(this.pastIndex > 0) {
			this.pastIndex--;
		}else {
			this.pastIndex = 31;
		}
		for(int i=0; i < this.rows; i++) {
			for(int j=0; j < this.cols; j++) {
				this.cells[i][j] = this.pastCells[i][j][pastIndex];
			}
		}
		this.undoNum--;
	}
	
	public boolean isUndoable() {
		if(this.undoNum > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isAlive(int cols, int rows) {
		if(this.cells[cols][rows] == true) {
			return true;
		}else {
			return false;
		}
	}

}