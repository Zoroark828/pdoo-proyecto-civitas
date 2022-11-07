
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/** autora: @Zoroark828
 *
 * 
 */

public class MazoSorpresas {
    private static final int TAMANIOMAZO = 10;
    
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;                      // Si esta activo, el mazo no se baraja
    
        
    private void init(){                        // Este inicializador será usado por ambos constructores
        sorpresas = new ArrayList();
        barajada = false;
        usadas = 0;
    }
    
    MazoSorpresas (boolean debug){                  // Constructor con parametro
        this.init();
        this.debug = debug;        
        if (this.debug)
            Diario.getInstance().ocurreEvento("El modo Debug del mazo de sorpresas esta activado");
    }
    
    MazoSorpresas () {                          // Constructor sin parametro
    this.init();
    debug = false;
    }

    void alMazo (Sorpresa sorpresa){           // Si el mazo no ha sido barajado, añade la sorpresa
        if (!barajada && this.sorpresas.size() <= TAMANIOMAZO)
            sorpresas.add(sorpresa);
    }
    
    Sorpresa siguiente(){                      
        if ((!barajada || usadas == TAMANIOMAZO) && !debug){
            Collections.shuffle(sorpresas);                         // Con la orden shuffle barajamos el Mazo
            barajada = true;
            usadas = 0;
        }
        
        Sorpresa sorpresaSiguiente = sorpresas.get(0);             // Variable de la carta Sorpresa que salio
        sorpresas.remove(0);
        sorpresas.add(sorpresaSiguiente);
        usadas++;
        
        return sorpresaSiguiente;
    }
       
    
}
