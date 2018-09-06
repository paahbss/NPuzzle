package problemPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import tree.*;

public class Problem {
	private State initialState;
	private State goalState;
	private int n;
	
	public Problem(int n){
		this.initialState = new State(n);
		this.goalState = new State(n);
		this.n = n;
	
		
	}
	
	
	
	public State ir_Lado_Direito(State initial, int emptyPosition){
		int aux = initial.getTile().get(emptyPosition);
		initial.getTile().set(emptyPosition,initial.getTile().get(emptyPosition +1));
		initial.getTile().set(emptyPosition + 1, aux);						
		return initial;
		
	}
	
	public State ir_Lado_Esquerdo(State initial, int emptyPosition){
		int aux = initial.getTile().get(emptyPosition);
		initial.getTile().set(emptyPosition, initial.getTile().get(emptyPosition -1));
		initial.getTile().set(emptyPosition - 1, aux);						
		return initial;
		
	}
	public State ir_Para_Baixo(State initial, int emptyPosition, int pos){
		int aux = initial.getTile().get(emptyPosition);
		initial.getTile().set(emptyPosition, initial.getTile().get(emptyPosition + pos));
		initial.getTile().set(emptyPosition + pos, aux);					
		return initial;
		
	}
	public State ir_Para_Cima(State initial, int emptyPosition, int pos){
		int aux = initial.getTile().get(emptyPosition);
		initial.getTile().set(emptyPosition, initial.getTile().get(emptyPosition - pos));
		initial.getTile().set(emptyPosition - pos, aux);					
		return initial;
		
	}
	
	public void initialize(){
		
		for (int i = 0; i <= n; i++) {
			this.goalState.getTile().add(i);
			this.initialState.getTile().add(i);
		}
		
		
		int emptyPosition = 0;
		
		int pos = (int)Math.sqrt(this.n + 1);
		Random random = new Random(); 
		int choice;
		int repeat = 30;
		
		while(repeat>0){
			choice = random.nextInt(4);
			if(emptyPosition < pos-1){				
				switch (choice) {
				
				//lado direito
					case 0:	
						if(emptyPosition + 1 > pos-1){
							repeat++;							
						}else{						
							this.initialState = this.ir_Lado_Direito(this.initialState, emptyPosition);	
							emptyPosition += 1;
						}
						break;
					
					// baixo 	
					case 1:						
						this.initialState = this.ir_Para_Baixo(this.initialState, emptyPosition, pos);
						emptyPosition += pos;
						break;
						
					// lado esquerdo	
					case 2:	
						if(emptyPosition - 1 < 0){
							repeat++;
						}else{
							this.initialState = this.ir_Lado_Esquerdo(this.initialState, emptyPosition);
							emptyPosition -= 1;
						}
						break;
						
					// cima	
					case 3:
						repeat++;
						break;
				}
					
			}else if (emptyPosition < this.n + 1 && emptyPosition > this.n - pos){
				switch (choice) {
								
					//lado direito
					case 0:	
						if(emptyPosition + 1 >= this.n + 1){
							repeat++;
						}else{
							this.initialState = this.ir_Lado_Direito(this.initialState, emptyPosition);	
							emptyPosition = emptyPosition + 1;
						}
						break;
					
					// baixo 	
					case 1:	
						repeat++;
						break;
						
						
					// lado esquerdo	
					case 2:	
						if(emptyPosition - 1 <= this.n - pos){
							repeat++;
						}else{
							this.initialState = this.ir_Lado_Esquerdo(this.initialState, emptyPosition);	
							emptyPosition = emptyPosition - 1;
						}
						break;
						
					// cima	
					case 3:	
						this.initialState = this.ir_Para_Cima(this.initialState, emptyPosition, pos);
						emptyPosition -= pos;
						break;
				}
			}else if (this.initialState.getLeftColumn().contains(emptyPosition)){
				switch (choice) {
				
				//lado direito
				case 0:						
					this.initialState = this.ir_Lado_Direito(this.initialState, emptyPosition);	
					emptyPosition = emptyPosition + 1;
					break;
				
				// baixo 	
				case 1:	
					this.initialState = this.ir_Para_Baixo(this.initialState, emptyPosition, pos);
					emptyPosition = emptyPosition + pos;
					break;
					
					
				// lado esquerdo	
				case 2:	
					repeat++;
					break;
					
				// cima	
				case 3:	
					this.initialState = this.ir_Para_Cima(this.initialState, emptyPosition, pos);
					emptyPosition = emptyPosition - pos;
					break;
				}
				
			}else if (this.initialState.getRightColumn().contains(emptyPosition)){
				switch (choice) {
				
				//lado direito
				case 0:						
					repeat++;
					break;
				
				// baixo 	
				case 1:	
					this.initialState = this.ir_Para_Baixo(this.initialState, emptyPosition, pos);
					emptyPosition = emptyPosition + pos;
					break;
					
					
				// lado esquerdo	
				case 2:	
					this.initialState = this.ir_Lado_Esquerdo(this.initialState, emptyPosition);
					emptyPosition = emptyPosition - 1;
					break;
					
				// cima	
				case 3:	
					if(emptyPosition - pos < 0){
						repeat++;
					}else{
						this.initialState = this.ir_Para_Cima(this.initialState, emptyPosition, pos);
						emptyPosition = emptyPosition - pos;
					}
					break;
				}					
			}else{
				switch (choice) {
								
					//lado direito
					case 0:						
						this.initialState = this.ir_Lado_Direito(this.initialState, emptyPosition);	
						emptyPosition = emptyPosition + 1;
						break;
					
					// baixo 	
					case 1:	
						this.initialState = this.ir_Para_Baixo(this.initialState, emptyPosition, pos);
						emptyPosition = emptyPosition + pos;
						break;
						
						
					// lado esquerdo	
					case 2:	
						this.initialState = this.ir_Lado_Esquerdo(this.initialState, emptyPosition);
						emptyPosition = emptyPosition - 1;
						break;
						
					// cima	
					case 3:	
						this.initialState = this.ir_Para_Cima(this.initialState, emptyPosition, pos);
						emptyPosition = emptyPosition - pos;
						break;
				}

			}
			repeat--;
			
			
			
		}
		
		
		
	}
		
		
		
		
		
				
	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public State getGoalState() {
		return goalState;
	}

	public void setGoalState(State goalState) {
		this.goalState = goalState;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
}
