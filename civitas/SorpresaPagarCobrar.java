
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 *
 */

public class SorpresaPagarCobrar extends Sorpresa {
    SorpresaPagarCobrar (String texto, int valor){
        super(texto, valor);
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
        todos.get(actual).modificarSaldo(this.getValor());
    }
    
    
}
