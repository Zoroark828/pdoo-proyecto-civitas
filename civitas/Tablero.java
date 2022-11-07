
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 * 
 */

public class Tablero {
    private static final int TAMANIOTABLERO = 20;
    
    private ArrayList<Casilla> tablero;
    private boolean porSalida;
    
    Tablero(){
        tablero = new ArrayList();                       
        tablero.add(new Casilla("Salida"));                     // Crea la casilla de salida
           
        porSalida = false;  
    }
    
    private boolean correcto (int numCasilla){    
        return (numCasilla >= 0) && (numCasilla < TAMANIOTABLERO);
    }       
    
    boolean computarPasoPorSalida(){        
        boolean pasoSalida = porSalida;
        porSalida = false;        
        
        return pasoSalida;
    }
    
    void añadeCasilla (Casilla casilla){
        tablero.add(casilla);        
    }    
    
    public Casilla getCasilla (int numCasilla){        
        if(correcto(numCasilla))
            return tablero.get(numCasilla);
        else
            return null;
    }
    
    public ArrayList<Casilla> getCasillas(){
        return tablero;
    }
    
    int nuevaPosicion (int actual, int tirada){
        
        int nuevaPosicion = actual + tirada;
                
        // Si estamos en el tamañoTabero - 1 (es decir, la ultima casilla), nuevaPosición será
        // 0 (es decir, la casilla de salida) + el resto de dividir la nuevaPosicion entre el tamaño del tablero
        // EJEMPLOS:                 size() es tamañoTablero
        // actual = 19      tirada = 6      size() = 25       nuevaPosicion = 25 % 25 = 0
        // actual = 3       tirada = 3      size() = 5        nuevaPosicion = 6 % 5 = 1
        // actual = 3       tirada = 2      size() = 5        nuevaPosicion = 5 % 5 = 0
        // actual = 3       tirada = 1      size() = 5        nuevaPosicion = 4
        
        if (nuevaPosicion > (TAMANIOTABLERO - 1)){
            nuevaPosicion = nuevaPosicion % TAMANIOTABLERO;
            porSalida = true;
        }   
        
        return nuevaPosicion;
    }
    
    
    
    
    
    
}
