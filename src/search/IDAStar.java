package search;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import tree.Action;
import tree.Node;
import tree.State;

public class IDAStar {
	final int FOUND = -1;
	private Node initial;
	private State goal;
	private int threshold;
	private int qtdNodeExp;
	private int ramif;
	private int depth;
	private int qtdFather;

	public IDAStar(State initial, State goal) {
		this.initial = new Node(initial, null, null, 0, 0);;
		this.goal = goal;
		this.threshold = this.initial.hn(this.goal);
		this.qtdNodeExp = 0;
		this.ramif = 0;
		this.depth = 0;
		this.qtdFather = 0;
	}

	
	public LinkedList<Action> resolve() throws CloneNotSupportedException {

	   
	    int cutOff = this.initial.hn(this.goal);
	    int low = 0;
	    int number =0;
	    
	    LinkedList<Node> open = new LinkedList<Node>();
	    LinkedList<Node> closed = new LinkedList<Node>();
	    LinkedList<Node> m = new LinkedList<Node>();
	    LinkedList<Node> visited = new LinkedList<Node>();

	    open.add(initial);

	    while (true) {
	      while (open.size() != 0) {

			 
	    	  Node n = open.element();
	    			  
			  open.remove(n);
		
			  if(n.isGoal(this.goal)) {
					this.depth = n.getDepth();
					return soluction(n);
			  }

			  if (n.fn(this.goal) <= cutOff) {
		  
				 closed.add(n);
				 n.expand();
				 qtdFather++;
				 this.qtdNodeExp++;
				 for (Node children : n.getChildren()) {
						open.addFirst(children);
				
				 }
				 this.ramif += n.getChildren().size();
		  

			  } else {
				 visited.addFirst(n);
			  }
	      } 

	      if (visited.size() == 0) {
			  System.out.println("Failure! Can't solve problem, exiting...");
			  return null;
	      }

	     low = visited.removeFirst().fn(this.goal); 
	     
	     for (Node node : visited) {
	    	 number = node.fn(this.goal);
	    	 if (number < low)
				 low = number;
	     }	     

	      cutOff = low;

	      for (Node node : visited) {
	    	  open.addFirst(node);
	      }
	      
	      visited.removeAll(visited);

	    } 
	}

	
	
	
	public boolean contains(LinkedList<Node> list, Node elem){
		for (Node n : list) {
			if(n.getState().equals(elem.getState())){
				return true;
			}
		}
		return false;
	}
	

	public LinkedList<Action> soluction(Node node){
		Node auxFather= node.getFather();
		LinkedList<Action> actions = new LinkedList<Action>();
		actions.addFirst(node.getAction());

		while(auxFather != null){
			actions.addFirst(auxFather.getAction());
			auxFather = auxFather.getFather();
		}

		return actions;
	}

	public Node getInitial() {
		return initial;
	}

	public void setInitial(Node initial) {
		this.initial = initial;
	}

	public State getGoal() {
		return goal;
	}

	public void setGoal(State goal) {
		this.goal = goal;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public int getQtdNodeExp() {
		return qtdNodeExp;
	}

	public void setQtdNodeExp(int qtdNodeExp) {
		this.qtdNodeExp = qtdNodeExp;
	}

	public int getRamif() {
		return ramif;
	}

	public void setRamif(int ramif) {
		this.ramif = ramif;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getQtdFather() {
		return qtdFather;
	}

	public void setQtdFather(int qtdFather) {
		this.qtdFather = qtdFather;
	}

	public int getFOUND() {
		return FOUND;
	}


}
