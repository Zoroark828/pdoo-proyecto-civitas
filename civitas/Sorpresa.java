
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 *
 *
 */

abstract class Sorpresa {
    private String texto;
    private int valor;                                                          // Si la sorpresa es de cobro, valor será positivo
    private MazoSorpresas mazo;                                                 // Si la sorpresa obliga a pagar, valor será negativo
    
    Sorpresa (String texto, int valor){
        this.texto = texto;
        this.valor = valor;
        this.mazo = null;
    }
    
    abstract void aplicarAJugador (int actual, ArrayList<Jugador> todos);
    
    // CAMBIO (!!). Cambie su visibilidad de private a paquete para que las subclases lo puedan usar
    void informe (int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Se le esta aplicando una sorpresa al jugador " + todos.get(actual).getNombre()
        +"\n" +this.toString());
    }
    
    public String toString(){
        return this.texto;
    }
    
    // Creé este getter para acceder al atributo valor desde las clases hijas
    int getValor(){
        return this.valor;
    }
    
}
