package search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import tree.Action;
import tree.Node;
import tree.State;

public class Astar {
	
	State state;
	State goal_state;
	private int qtdNodeExp;
	private int ramif;
	private int depth;
	private int qtdFather;

	public Astar(State state, State goal_state) {
		this.state = state;
		this.goal_state = goal_state;
		this.qtdNodeExp = 0;
		this.ramif = 0;
		this.depth = 0;
		this.qtdFather = 0;
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
	
	public LinkedList<Action> resolve() throws Exception{
		PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if(o1.hn(goal_state) < o2.hn(goal_state)){
					return -1;
				}else if(o1.hn(goal_state) > o2.hn(goal_state)){
					return 1; 
				}else{
				    return 0;
                }
			}
		});
		
		ArrayList<State> explored = new ArrayList<State>();
		Node current_node = new Node(state, null, null, 0, 0);
		
		if(current_node.isGoal(goal_state)){
			this.depth = current_node.getDepth();
			return soluction(current_node);
		}
		
		priorityQueue.add(current_node);
		
		
		while(true){
			if(priorityQueue.isEmpty()){
				throw new Exception();
			}
			
			Node node = priorityQueue.remove();
			
			/*for (Node n : priorityQueue) {
				//System.out.println(n.gn());
			}*/
			
			explored.add(node.getState());
			node.expand();
			qtdFather++;
			this.qtdNodeExp++;
			
			
			for (Node children : node.getChildren()) {
                if(!contains(explored, children.getState()) && !contains(priorityQueue, children)){
					if(children.isGoal(goal_state)){
						this.depth = children.getDepth();
						return soluction(children);
					}
					priorityQueue.add(children);
				}
			}
			this.ramif += node.getChildren().size();
		}
	}

	public boolean contains(PriorityQueue<Node> list, Node elem){
		for (Node n : list) {
			if(n.getState().equals(elem.getState())){
				return true;
			}
		}
		return false;
	}

	public boolean contains(ArrayList<State> list, State elem){
		for (State n : list) {
			if(n.equals(elem)){
				return true;
			}
		}
		return false;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getGoal_state() {
		return goal_state;
	}

	public void setGoal_state(State goal_state) {
		this.goal_state = goal_state;
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
	
	
}
