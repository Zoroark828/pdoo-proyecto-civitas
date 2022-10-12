
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 * 
 */

public class Tablero {
    static private final int TAMAÑOTABLERO = 20;
    
    private ArrayList<Casilla> casillas;
    private boolean porSalida;
    
    Tablero(){
        casillas = new ArrayList();                       
        casillas.add(new Casilla(TipoCasilla.DESCANSO, "Salida", 0,0,0));
           
        porSalida = false;  
    }
    
    private boolean correcto (int numCasilla){    
        return (numCasilla >= 0) && (numCasilla < TAMAÑOTABLERO);
    }       
    
    boolean computarPasoPorSalida(){        
        boolean pasoSalida = porSalida;
        porSalida = false;        
        
        return pasoSalida;
    }
    
    void añadeCasilla (Casilla casilla){
        casillas.add(casilla);        
    }    
    
    Casilla getCasilla (int numCasilla){        
        if(correcto(numCasilla))
            return casillas.get(numCasilla);
        else
            return null;
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
        
        if (nuevaPosicion > (TAMAÑOTABLERO - 1)){
            nuevaPosicion = nuevaPosicion % TAMAÑOTABLERO;
            porSalida = true;
        }   
        
        return nuevaPosicion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
