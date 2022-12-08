
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 * Este tipo de sorpresa se encarga de sustituir al jugador convertido en jugador especulador en el
 * array de jugadores
 * 
 */

public class SorpresaConvertirme extends Sorpresa {
    SorpresaConvertirme (String texto, int valor){
        super(texto, valor);
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
        Jugador nuevoJugador = todos.get(actual).convertir();
        todos.remove(actual);
        todos.add(actual, nuevoJugador);
    }
    
}
