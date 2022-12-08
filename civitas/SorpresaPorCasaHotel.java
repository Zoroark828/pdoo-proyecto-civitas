
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 *
 */

public class SorpresaPorCasaHotel extends Sorpresa {
    SorpresaPorCasaHotel (String texto, int valor){
        super(texto, valor);
    }
    
    @Override
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        float valorPorCasaHotel = this.getValor() * todos.get(actual).cantidadCasasHoteles();
        super.informe(actual, todos);
        todos.get(actual).modificarSaldo(valorPorCasaHotel);
    }
}
