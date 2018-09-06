package tree;

import java.util.ArrayList;

public class State {
	private ArrayList<Integer> tile;
	private int n;
	private ArrayList<Integer> rightColumn;
	private ArrayList<Integer> leftColumn;
	
	public State(){
		
	}
	
	public State(int n){
		this.tile = new ArrayList<Integer>(n+1);
		this.n = n;
		this.rightColumn = new ArrayList<Integer>();
		this.leftColumn = new ArrayList<Integer>();
		int i;
		for (i = 0; i < Math.sqrt(this.n + 1); i++) {
			//this.leftColumn.set(i, i * (int)Math.sqrt(this.n + 1));
			this.leftColumn.add(i * (int)Math.sqrt(this.n + 1));
			
			if(i != 0){
				//this.rightColumn.set(i, i * (int)Math.sqrt(this.n + 1) - 1);
				this.rightColumn.add(i * (int)Math.sqrt(this.n + 1) - 1);
			}
		}
		
		//this.rightColumn.set(i, this.n + 1);
		this.rightColumn.add(this.n);
		//System.out.println("right"+this.rightColumn.toString());
		//System.out.println("left" + this.leftColumn.toString());
	}
	
	

	
	public boolean equals(State state){
		if(this.tile.equals(state.getTile())){
			return true;
		}
		return false;
	}
	
	public int getEmptyPosition(){
		for (int i = 0; i < this.tile.size(); i++) {
			if(this.tile.get(i) == 0){
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Integer> getTile() {
		return tile;
	}

	public void setTile(ArrayList<Integer> tile) {
		this.tile = tile;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public ArrayList<Integer> getRightColumn() {
		return rightColumn;
	}

	public void setRightColumn(ArrayList<Integer> rightColumn) {
		this.rightColumn = rightColumn;
	}

	public ArrayList<Integer> getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(ArrayList<Integer> leftColumn) {
		this.leftColumn = leftColumn;
	}
	
}
