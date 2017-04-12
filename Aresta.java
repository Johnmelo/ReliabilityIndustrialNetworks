
public class Aresta {
	private Vertice v1,v2;
	private boolean ativa;
	
	public Aresta(Vertice v1,Vertice v2){
		this.v1 = v1;
		this.v2 = v2;
		this.ativa = true;
	}
	
	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	public Vertice getVerticeV1(){
		return v1;
	}
	
	public Vertice getVerticeV2(){
		return v2;
	}
	
	public void viewVertices(){
		System.out.println(this.v1.getIndice()+" ---- "+this.v2.getIndice());
	}

}
