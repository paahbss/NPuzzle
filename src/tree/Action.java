package tree;

public class Action {
	private TypeOfAction type;
	
	public Action(TypeOfAction type){
		this.type = type;
	}
	
	public Action(){
		
	}

	public TypeOfAction getType() {
		return type;
	}

	public void setType(TypeOfAction type) {
		this.type = type;
	}
}
