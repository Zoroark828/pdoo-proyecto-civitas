
package civitas;

import java.util.Random;

/** autora: @Zoroark828
 *
 * 
 */

class Dado {        
    private static final Dado instance = new Dado();
    private static final int VALORDEBUG = 1;
    private static final int VALORESDADO = 6;
    
    private Random random;
    private int ultimoResultado;
    private boolean debug;                      // Si está activo, el numero al tirar el dado es siempre 1
    
    private Dado(){                             // Constructor privado
        this.random = new Random();
        this.ultimoResultado = 0;
        this.debug = false;        
    }
    
    public static Dado getInstance(){        
        return instance;
    }
    
    int tirar(){
        int tirada;
        
        if (this.debug == true)
            tirada = 1;
        else
            tirada = (int) (random.nextDouble() * 6 + 1);
        
        this.ultimoResultado = tirada;
        return tirada;
    }
    
    int quienEmpieza (int n){
        int suertudo;
        suertudo = (int) (random.nextDouble() * (n-1) + 0);
        return suertudo;
    }
    
    public void setDebug (boolean debug){
        this.debug = debug;
        
        if (debug == false)
            Diario.getInstance().ocurreEvento("El modo Debug del dado esta desactivado.");
        else
            Diario.getInstance().ocurreEvento("El modo Debug del dado esta activado.");
    }
    
    int getUltimoResultado(){        
        return this.ultimoResultado;
    }
    
    
    
    
    
    
    
    
    
    
}
