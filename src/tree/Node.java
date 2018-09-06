package tree;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Node {
	private State state;
	private Node father;
	private Action action;
	private int depth;
	private int cost;
	private ArrayList<Node> children;
	//private ArrayList<Integer> rightColumn;
	//private ArrayList<Integer> leftColumn;
	
	public Node(){
		
	}
	
	public Node(State state, Node father, Action action, int depth, int cost){
		this.state = state;
		this.father = father;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.children = new ArrayList<Node>();
		
		/*int i;
		for (i = 0; i <= Math.sqrt(this.getState().getN())+1; i++) {
			this.leftColumn.set(i, i * (int)Math.sqrt(this.state.getN()+1));
			
			if(i != 0){
				this.rightColumn.set(i, i * (int)Math.sqrt(this.state.getN()+1) - 1);
			}
		}
		
		this.rightColumn.set(i, this.state.getN() + 1); */
	}

	public boolean isGoal(State goalState){
		if(this.state.equals(goalState)){
			return true;
		}
		return false;
	}
	
	public void expand() throws CloneNotSupportedException{
		int emptyPosition = this.state.getEmptyPosition();
		int pos = (int)Math.sqrt(this.state.getN() + 1);
		//System.out.println("empty: " + emptyPosition);
		
		//System.out.println(this.state.getTile().toString());
		
		
		//cima
		if(emptyPosition > pos-1){
			//System.out.println("cima");		
			ArrayList<Integer> newTile = swap(emptyPosition - pos, emptyPosition, (ArrayList<Integer>)this.state.getTile().clone() );			
			Action action = new Action(TypeOfAction.GO_UP);
			State newState = new State(this.getState().getN());
			newState.setLeftColumn(this.state.getLeftColumn());
			newState.setRightColumn(this.state.getRightColumn());
			newState.setTile(newTile);
			Node ch = new Node(newState, this, action, this.depth+1, this.cost+1);			
			this.children.add(ch);
			
		}
		
		//baixo
		if(emptyPosition < ((this.state.getN()+1) - pos)){	
			//System.out.println("baixo");
			//State sta = this.state.clone();
			ArrayList<Integer> newTile= swap(emptyPosition +  pos, emptyPosition, (ArrayList<Integer>)this.state.getTile().clone());
			//System.out.println("aqui: " + this.state.getTile().toString());
			Action action = new Action(TypeOfAction.GO_DOWN);
			State newState = new State(this.getState().getN());
			newState.setLeftColumn(this.state.getLeftColumn());
			newState.setRightColumn(this.state.getRightColumn());
			newState.setTile(newTile);
			Node ch = new Node(newState, this, action, this.depth+1, this.cost+1);
			//System.out.println(ch.getState().getTile().toString());
			this.children.add(ch);
		}
		
		//esquerda
		/*if(!this.leftColumn.contains(emptyPosition)){
			State newState = swap(emptyPosition - 1, emptyPosition);
			Action action = new Action(TypeOfAction.GO_LEFT);
			this.children.add(new Node(newState, this, action, this.depth+1, this.cost+1));
		}*/
		
		if(!this.state.getLeftColumn().contains(emptyPosition)){
			//System.out.println("esquerda");
			//State sta = this.state.clone();
			ArrayList<Integer> newTile = swap(emptyPosition - 1, emptyPosition, (ArrayList<Integer>)this.state.getTile().clone());
			//System.out.println("aqui: " + this.state.getTile().toString());

			Action action = new Action(TypeOfAction.GO_LEFT);
			State newState = new State(this.getState().getN());
			newState.setLeftColumn(this.state.getLeftColumn());
			newState.setRightColumn(this.state.getRightColumn());
			newState.setTile(newTile);
			Node ch = new Node(newState, this, action, this.depth+1, this.cost+1);
			//System.out.println(ch.getState().getTile().toString());
			this.children.add(ch);
		}
		
		//direita
		/*if(!this.rightColumn.contains(emptyPosition)){
			State newState = swap(emptyPosition + 1, emptyPosition);
			Action action = new Action(TypeOfAction.GO_RIGHT);
			this.children.add(new Node(newState, this, action, this.depth+1, this.cost+1));
		}*/	
	
		if(!this.state.getRightColumn().contains(emptyPosition)){
			//System.out.println("direita");
			//State sta = this.state.clone();
			ArrayList<Integer> newTile = swap(emptyPosition + 1, emptyPosition, (ArrayList<Integer>)this.state.getTile().clone());
			//System.out.println("aqui: " + this.state.getTile().toString());

			Action action = new Action(TypeOfAction.GO_RIGHT);
			State newState = new State(this.getState().getN());
			newState.setLeftColumn(this.state.getLeftColumn());
			newState.setRightColumn(this.state.getRightColumn());
			newState.setTile(newTile);
			Node ch = new Node(newState, this, action, this.depth+1, this.cost+1);
			//System.out.println(ch.getState().getTile().toString());
			this.children.add(ch);
		}
	}
	
	public ArrayList<Integer> swap(int change, int emptyPosition, ArrayList<Integer> tile){
		
		int aux = tile.get(emptyPosition);	
		tile.set(emptyPosition, tile.get(change));
		tile.set(change, aux);
		//System.out.println("original: ");
		//System.out.println(this.getState().getTile().toString());
		//System.out.println(tile.toString());
		return tile;
	}
	
	
	public ArrayList<Node> parents(){
		Node auxFather= this.father;
		ArrayList<Node> parents = new ArrayList<Node>();
		while(auxFather != null){
			parents.add(auxFather);
			auxFather = auxFather.father;
		}
		
		return parents;
		
		
	}
	
	public int gn(){
		int costs = this.cost;
		for(Node parent : this.parents()) {
			costs += parent.cost;
		}
		return costs;
	}
	
	public int hn(State goalState){
		int manhattanDistanceSum = 0;		
		int rowSz = (int)Math.sqrt(this.state.getN() + 1);
		for (int i = 0; i < this.state.getTile().size(); i++)
		    if( this.state.getTile().get(i) == i ){ 
		    	continue;
		    }else{
		    	manhattanDistanceSum += (Math.abs((this.state.getTile().get(i) / rowSz )- (i / rowSz)) + 
		    						Math.abs((this.state.getTile().get(i) % rowSz ) - (i % rowSz)));
		    }
		return manhattanDistanceSum;
	}
	
	public int fn(State goalState){
		return gn() + hn(goalState);
	}
	
	/*public int gn(){
		int costs = this.cost;
		Node auxFather= this.father;
		
		while(auxFather != null){
			costs += auxFather.cost;
			auxFather = auxFather.father;
		}
		
		return costs;
	}*/
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	
}
