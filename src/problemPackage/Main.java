package problemPackage;

import java.util.ArrayList;
import java.util.LinkedList;

import search.Astar;
import search.Busca_Em_Largura;
import search.Busca_Em_Profundidade;
import search.IDAStar;
import tree.Action;

public class Main {
	public static void main(String[] args) throws Exception {
		Problem problem = new Problem(8);
		problem.initialize();
		
		//System.out.println(problem.getInitialState().getTile().toString());
		
		ArrayList<Integer> teste = new ArrayList<>();
		
		teste.add(0);
		teste.add(3);
		teste.add(2);
		teste.add(4);
		teste.add(1);
		teste.add(7);
		teste.add(6);
		teste.add(8);
		teste.add(5);
		
		problem.getInitialState().setTile(teste);
		Busca_Em_Largura busca = new Busca_Em_Largura(problem.getInitialState(), problem.getGoalState());
		Busca_Em_Profundidade buscaProf = new Busca_Em_Profundidade(problem.getInitialState(), problem.getGoalState());
		Astar buscaStar = new Astar(problem.getInitialState(), problem.getGoalState());
		IDAStar buscaIDA = new IDAStar(problem.getInitialState(), problem.getGoalState());
		
		LinkedList<Action> ac = new LinkedList<Action>();
		System.out.println("A*");
		long inicio = System.currentTimeMillis();  
		ac = buscaStar.resolve();
		long fim  = System.currentTimeMillis(); 
		
		System.out.println("Tempo:" +  (fim - inicio));
		System.out.println("Qtd Nos Expandidos:" +  buscaStar.getQtdNodeExp());
		System.out.println("Fator de ramificacao :" +  (buscaStar.getRamif()/buscaStar.getQtdFather()));
		System.out.println("Profundidade :" + buscaStar.getDepth());
		
		
		System.out.println("\nLARGURA");
		inicio = System.currentTimeMillis();  
		ac = busca.resolve();
		fim  = System.currentTimeMillis(); 
		
		System.out.println("Tempo:" +  (fim - inicio));
		System.out.println("Qtd Nos Expandidos:" +  buscaStar.getQtdNodeExp());
		System.out.println("Fator de ramificacao :" +  (buscaStar.getRamif()/buscaStar.getQtdFather()));
		System.out.println("Profundidade :" + buscaStar.getDepth());
		
		System.out.println("\nIDA*");
		inicio = System.currentTimeMillis();  
		ac = buscaIDA.resolve();
		fim  = System.currentTimeMillis(); 
		
		System.out.println("Tempo:" +  (fim - inicio));
		System.out.println("Qtd Nos Expandidos:" +  buscaStar.getQtdNodeExp());
		System.out.println("Fator de ramificacao :" +  (buscaStar.getRamif()/buscaStar.getQtdFather()));
		System.out.println("Profundidade :" + buscaStar.getDepth());
		
		System.out.println("\nPRODUNDIDADE");
		inicio = System.currentTimeMillis();  
		ac = buscaProf.resolve();
		fim  = System.currentTimeMillis(); 
		
		System.out.println("Tempo:" +  (fim - inicio));
		System.out.println("Qtd Nos Expandidos:" +  buscaStar.getQtdNodeExp());
		System.out.println("Fator de ramificacao :" +  (buscaStar.getRamif()/buscaStar.getQtdFather()));
		System.out.println("Profundidade :" + buscaStar.getDepth());
		
		
		
	}
}
