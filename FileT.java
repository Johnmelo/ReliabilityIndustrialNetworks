import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Classe responsável pela leitura e manipulação de arquivos txt.
 * @author jonathas
 *
 */
public class FileT {

    private FileReader reader;
    private BufferedReader buffer;
    private File file;
    
    @SuppressWarnings("hiding")
	public FileT(File file) throws FileNotFoundException, IOException{
    	
        try{
        	this.file = file;
        	this.reader = new FileReader(file);
            this.buffer = new BufferedReader(this.reader);
			
			
		}catch(FileNotFoundException ex) {
			Logger.getLogger(FileT.class.getName()).log(Level.SEVERE,null,ex);
		}catch(IOException ex){
			Logger.getLogger(FileT.class.getName()).log(Level.SEVERE,null,ex);
		}finally{
			
		}
    }
    
    public BufferedReader getBuffer(){
    	return this.buffer;
    }
    
    public File getFile(){
    	return this.file;
    }
}