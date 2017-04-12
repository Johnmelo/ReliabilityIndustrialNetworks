import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Grafo {
	private int qntVertices;
	private int matAdjacencia[][];
	private Aresta matArestas[][];
	private ArrayList<Vertice> listV;
	private ArrayList<Aresta> listE;
	

	public Grafo(int n){
		this.qntVertices = n;
		this.matAdjacencia = new int[n][n];
		this.matArestas = new Aresta[n][n];
		this.listV = new ArrayList<Vertice>();
		this.listE = new ArrayList<Aresta>();
		for(int i = 0;i < n; i++){
			for(int j = 0;j < n;j++){
				this.matAdjacencia[i][j] = 0;
				this.matArestas[i][j] = null;
			}
			this.listV.add(new Vertice(i));
			
		}
	}
	public Grafo(){
		
	}
	
	public void iniciarGrafo() throws IOException{
		FileT filesampletxt = null;
		JFileChooser chooser = new JFileChooser();
        chooser.showDialog(chooser, "Select Samples File");
		try {
			filesampletxt = new FileT(chooser.getSelectedFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String linha = filesampletxt.getBuffer().readLine();
		int i = 1,j = 0;
		int size = 0;
		while(linha != null){
			size = linha.length();	
			for(int x = 0; x < size; x+=2){
				//System.out.print("[" + i + "][" + j + "] = " + Integer.parseInt(linha.substring(x,x+1)) + " ");
				this.matAdjacencia[i][j] = Integer.parseInt(linha.substring(x,x+1));
				this.matAdjacencia[j][i] = Integer.parseInt(linha.substring(x,x+1));
				if(this.matAdjacencia[i][j] == 1){
					this.listV.get(i).setAdj(this.listV.get(j));
					this.listV.get(j).setAdj(this.listV.get(i));
					this.matArestas[i][j] = new Aresta(this.listV.get(i), this.listV.get(j));
					this.matArestas[j][i] = this.matArestas[i][j];
					this.listE.add(this.matArestas[i][j]);
				}
				j++;
			}
			i++;
			j = 0;
			linha = filesampletxt.getBuffer().readLine();
		}
	
		//startArestas();
		
	
	}
	
	/*public void startArestas(){
		int x = 0;
		int j = 1;
		int y = j;
		for(int i = 0;i < this.qntVertices;i++){
			while(j < this.qntVertices && y < this.qntVertices){
				if(this.matAdjacencia[i][y] == 1)
					this.listE.add(new Aresta(this.listV.get(i), this.listV.get(y)));
				x++;
				y++;
			}
			j++;
			y = j;
		}
	}*/
	
	public void exibirGrafo(){
		for(int x = 0;x<this.qntVertices;x++){
			for(int y = 0;y < this.qntVertices;y++){
				System.out.print(this.matAdjacencia[x][y]+" ");
			}
			System.out.println();
		}
	}
	
	public int qntArestas(){		
		return this.listE.size();
	}
	
	public void viewAdj(){
		System.out.println();
		for(Vertice v : this.listV){
			System.out.println(v.qntAdj());
		}
	}
	
	public ArrayList<Vertice> getListV(){
		return this.listV;
	}
	
	public ArrayList<Aresta> getListE(){
		return this.listE;
	}
	
	public void setDefeitoVertice(int idx){
		this.listV.get(idx).setDefeito(true);
	}
	
	public void setDefeitoAresta(int idx,int idy){
		if(((idx >= 0)&&(idx < this.qntVertices))&&((idy >= 0)&&(idy < this.qntVertices))){
			this.matArestas[idx][idy].setAtiva(false);
		}
		
	}
	
	public Aresta[][] getMatArestas() {
		return matArestas;
	}

	public int getQntVertices() {
		return qntVertices;
	}

	public void setQntVertices(int qntVertices) {
		this.qntVertices = qntVertices;
	}
		
}
