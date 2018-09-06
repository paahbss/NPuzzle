package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import tree.*;

public class Busca_Em_Profundidade {

	State state;
	State goal_state;
	private int qtdNodeExp;
	private int ramif;
	private int depth;
	private int qtdFather;

	public Busca_Em_Profundidade(State state, State goal_state) {
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
		LinkedList<Node> stack = new  LinkedList<Node>();
		ArrayList<State> explored = new ArrayList<State>();
		Node current_node = new Node(state, null, null, 0, 0);

		if(current_node.isGoal(goal_state)){
			this.depth = current_node.getDepth();
			return soluction(current_node);
		}

		stack.addFirst(current_node);
		int limite = 50;
		while(true){
			if(stack.isEmpty()){
				throw new Exception();
			}

			Node node = stack.removeFirst();
			explored.add(node.getState());

			if(node.getDepth() <= limite){
				node.expand();
				
				qtdFather++;
				this.qtdNodeExp++;

				for (Node children : node.getChildren()) {
					if(!contains(explored, children.getState()) && !contains(stack, children)){
						if(children.isGoal(goal_state)){
							this.depth = children.getDepth();
							return soluction(children);
						}
						stack.push(children);
					}
				}
				this.ramif += node.getChildren().size();
			}
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
