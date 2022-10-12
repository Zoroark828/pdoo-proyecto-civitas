
package civitas;

import java.util.Random;

/** autora: @Zoroark828
 *
 * 
 */

class Dado {        
    static final private Dado instance = new Dado();
    
    private Random random;
    private int ultimoResultado;
    private boolean debug;                      // Si está activo, el numero al tirar el dado es siempre 1
    
    private Dado(){                             // Constructor privado
        random = new Random();
        ultimoResultado = 0;
        debug = false;        
    }
    
    static Dado getInstance(){        
        return instance;
    }
    
    int tirar(){
        int tirada;
        
        if (debug == true)
            tirada = 1;
        else
            tirada = (int) (random.nextDouble() * 6 + 1);
        
        ultimoResultado = tirada;
        return tirada;
    }
    
    int quienEmpieza (int n){
        int suertudo;
        suertudo = (int) (random.nextDouble() * (n) + 0);        
        return suertudo;
    }
    
    void setDebug (boolean d){
        debug = d;
        
        if (d == false)
            Diario.getInstance().ocurreEvento("El modo Debug del dado esta desactivado.");
        else
            Diario.getInstance().ocurreEvento("El modo Debug del dado esta activado.");
    }
    
    int getUltimoResultado(){        
        return ultimoResultado;
    }
    
    
    
    
    
    
    
    
    
    
}
