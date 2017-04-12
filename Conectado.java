import java.util.concurrent.LinkedBlockingQueue;


public class Conectado {
	
	private LinkedBlockingQueue<Vertice> Q; 
	
	public Conectado(){
		
	}
	
	///WHITE = 0;GRAY = 1;BLACK = -1
		public boolean BFS(Grafo g,Vertice s,Vertice server,Aresta[][] E){
			Vertice v;
			for(Vertice u : g.getListV()){
				if(u.getIndice() != s.getIndice()){
					u.setColor(0);
					
				}
			}
			s.setColor(1);
			Q = new LinkedBlockingQueue<Vertice>();
			if(!s.isDefeito()){
				Q.add(s);
				while(Q.size() > 0){
					v = Q.remove();
					for(Vertice u : v.getAdj()){
						if((u.getColor() == 0) && (!u.isDefeito())&&(E[v.getIndice()][u.getIndice()]!= null && E[v.getIndice()][u.getIndice()].isAtiva())){
							if(u.getIndice() == server.getIndice())
								return true;
							u.setColor(1);
							Q.add(u);
						}
					}
					v.setColor(-1);
				}
			}
			
			return false;
		}

}
