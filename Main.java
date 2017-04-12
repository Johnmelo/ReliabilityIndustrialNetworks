import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Grafo G = new Grafo();
		Conectado C = new Conectado();

		ArrayList<Vertice> listVerticesDanificados = new ArrayList<Vertice>();
		ArrayList<Aresta> listArestasDanificadas = new ArrayList<Aresta>();
		ArrayList<Vertice> listEssenciais = new ArrayList<Vertice>();
		
		boolean end = false;
		boolean falha = false;
		int control = 0;
		int choice = -1;
		int k = -1;
		int server = 9;
		Scanner in = new Scanner(System.in);
		while(!end){
			switch(control){
				case 0:
					System.out.println("1-STATUS DA REDE   2-DANIFICAR VERTICE   3-DANIFICAR ARESTA   4-CONFIGURAR REDE   5-ESCANEAR REDE   6-TIPO DE FALHA   7-LIMPAR FALHAS   8-SAIR");
					control = in.nextInt();
					if((control < 0 || control > 8)||((k < 0)&&(control != 4))){
						System.out.println("REDE NÃO CONFIGURADA OU OPÇÃO INVALIDA");
						control = 0;
					}
					//choice = -1;
					break;
				case 1:
					if(listVerticesDanificados.size() >= k){
						System.out.println("REDE FALHOU!");
						falha = true;
					}else if(listVerticesDanificados.size() < k && listVerticesDanificados.size() > 0){
						int cont = 0;
						for(Vertice v : listEssenciais){
							if(v.isFalhou())
								cont++;
						}
						if(cont>0){
							System.out.println("REDE FALHOU!");
							falha = true;
						}else{
							System.out.println("REDE OK");
						}
					}else{
						System.out.println("REDE OK");
					}
					control = 0;
					break;
				case 2:
					System.out.println("DEFINA O VERTICE A DANIFICAR:");
					choice = in.nextInt();
					if(choice < 0 || choice > G.getQntVertices()-1){
						System.out.println("VERTICE INEXISTENTE!");
						choice = -1;
					}else{
						G.setDefeitoVertice(choice);
						control = 0;
					}
					break;
				case 3:
					int idx = -1,idy = -1;
					System.out.println("DIGITE OS INDICES DOS VERTICES QUE COMPOEM A ARESTA:");
					choice = in.nextInt();
					while(choice < 0 || choice > G.getQntVertices()-1){
			            System.out.println("VERTICE INIXISTENTE!TENTE NOVAMENTE:");
			            System.out.println("1º INDICE:");
			            choice = in.nextInt();
			        }
					idx = choice;
					choice = -1;
					System.out.println("2º INDICE:");
					choice = in.nextInt();
					while(choice < 0 || choice > G.getQntVertices()-1){
			            System.out.println("VERTICE INIXISTENTE!TENTE NOVAMENTE:");
			            System.out.println("2º INDICE:");
			            choice = in.nextInt();
			        }
					idy = choice;
					G.setDefeitoAresta(idx, idy);
					Aresta a[][] = G.getMatArestas();
					if(a[idx][idy]!= null)
						listArestasDanificadas.add(a[idx][idy]);
					choice = -1;
					control = 0;
					break;
				case 4:
					System.out.println("DIGITE A QUANTIDADE DE VERTICES DA REDE:");
					choice = in.nextInt();
					while(choice < 0){
						System.out.println("QUANTIDADE INVALIDA!TENTE NOVAMENTE");
						choice = in.nextInt();
					}
					k = choice/2;
					G = new Grafo(choice);
					G.iniciarGrafo();
					G.exibirGrafo();
					control = 0;
					break;
				case 5:
					for(Vertice v : G.getListV()){
						if(v.getIndice() != G.getListV().get(server).getIndice()){
							if(C.BFS(G, v, G.getListV().get(server), G.getMatArestas())){
								System.out.println(v.getIndice()+" Tem caminho");
							}else{
								System.out.println(v.getIndice()+" Não Tem caminho");
								v.setFalhou(true);
								listVerticesDanificados.add(v);
							}
						}
							
					}
					control = 1;
					break;
				case 6:
					if(falha){
						if((listVerticesDanificados.size() > 0)&&(listArestasDanificadas.size() == 0)){
							System.out.println("FALHA DE HARDWARE - PERMANENTE");
						}else if(listArestasDanificadas.size() > 0){
							int count = 0;
							for(Vertice v : listVerticesDanificados){
								if(v.isDefeito())
									count++;
							}
							if(count == 0)
								System.out.println("FALHA NO LINK - TRANSIENTE");
							else
								System.out.println("FALHA PERMANENTE E TRANSIENTE");
						}
					}else{
						System.out.println("REDE NÃO APRESENTA FALHAS");
					}
					control = 0;
					break;
				case 7:
					for(Aresta e : listArestasDanificadas){
						e.setAtiva(true);
					}
					listArestasDanificadas.removeAll(listArestasDanificadas);
					//System.out.println(listArestasDanificadas.size());
					for(Vertice e : listVerticesDanificados){
						e.setDefeito(false);
						e.setFalhou(false);
					}
					listVerticesDanificados.removeAll(listVerticesDanificados);
					//System.out.println(listVerticesDanificados.size());
					falha = false;
					control = 0;
					break;
				case 8:
					end = true;
					break;
				case 9:
					int qnt = 0;
					System.out.println("DIGITE A QUANTIDADE DE VERTICES ESSENCIAIS:");
					qnt = in.nextInt();
					if(qnt <= 0 || qnt > G.getListV().size()){
						System.out.println("NADA REALIZADO!");
						control = 0;
					}else{
						for(int i = 0;i<qnt;i++){
							System.out.println("DIGITE O INDICE DO "+i+1+"º :");
							choice = in.nextInt();
							while(choice < 0 || choice > G.getListV().size()-1){
								System.out.println("DIGITE O INDICE DO "+i+1+"º :");
								choice = in.nextInt();
							}
							listEssenciais.add(G.getListV().get(choice));
							choice = -1;
						}
						System.out.println("ESSENCIAIS REGISTRADOS!");
						control = 0;
					}
					break;
					
			}
		}
		
	}
	
	
	
	///PERCORRENDO APENAS DIAGONAL
			/*int x = 0;
			int j = 1;
			int y = j;
			for(int i = 0;i < 6;i++){
				while(j < 6 && y < 6){
					x++;
					y++;
				}
				j++;
				y = j;
			}
			System.out.println(x);*/

}
