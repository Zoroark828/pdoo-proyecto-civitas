
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 *
 */

public class Sorpresa {
    private String texto;
    private int valor;                                                          // Si la sorpresa es de cobro, valor será positivo
    private MazoSorpresas mazo;                                                 // Si la sorpresa obliga a pagar, valor será negativo
    private TipoSorpresa tipo;
    
    Sorpresa (TipoSorpresa tipo, String texto, int valor){
        this.tipo = tipo;
        this.texto = texto;
        this.valor = valor;
        this.mazo = null;
    }
    
    void aplicarAJugador (int actual, ArrayList<Jugador> todos){
        if (this.tipo == TipoSorpresa.PAGARCOBRAR)
            aplicarAJugador_pagarCobrar(actual, todos);
        else if (this.tipo == TipoSorpresa.PORCASAHOTEL)
            aplicarAJugador_porCasaHotel(actual, todos);
    }
    
    private void aplicarAJugador_pagarCobrar (int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        todos.get(actual).modificarSaldo(this.valor);
    }
    
    private void aplicarAJugador_porCasaHotel (int actual, ArrayList<Jugador> todos){
        float valorPorCasaHotel = this.valor * todos.get(actual).cantidadCasasHoteles();
        this.informe(actual, todos);
        todos.get(actual).modificarSaldo(valorPorCasaHotel);
    }
    
    private void informe (int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Se le está aplicando una sorpresa al jugador " + todos.get(actual).getNombre()
        +"\n" +this.toString());
    }
    
    public String toString(){        
        return this.texto;
    }
    
    
        
    
    
}
