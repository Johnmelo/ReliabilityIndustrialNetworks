import java.util.ArrayList;


public class Vertice {
	private int indice;//identificador ou tag.
	private boolean defeito;
	private ArrayList<Vertice> adj;
	private int color;
	private boolean falhou;
	
	public Vertice(int idx){
		this.indice = idx;
		this.defeito = false;
		this.adj = new ArrayList<Vertice>();
		this.color = 0;
		this.falhou = false;
		
	}
	
	public Vertice(Vertice v){
		this.indice = v.getIndice();
		this.color = v.getColor();
		this.adj = v.getAdj();
		this.defeito = v.isDefeito();
	}
	
	public boolean isFalhou() {
		return falhou;
	}

	public void setFalhou(boolean falhou) {
		this.falhou = falhou;
	}

	public int getIndice() {
		return indice;
	}

	public boolean isDefeito() {
		return defeito;
	}

	public void setDefeito(boolean bateria) {
		this.defeito = bateria;
	}

	public ArrayList<Vertice> getAdj() {
		return adj;
	}

	public void setAdj(Vertice adj) {
		this.adj.add(adj);
	}
	
	public int qntAdj(){
		return this.adj.size();
	}
	
	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}

	
}
