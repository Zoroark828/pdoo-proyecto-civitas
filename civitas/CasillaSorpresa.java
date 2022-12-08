
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 * 
 * 
 * 
 */

public class CasillaSorpresa extends Casilla {
    private Sorpresa sorpresa;
    private MazoSorpresas mazo;
    
    CasillaSorpresa (String nombre, MazoSorpresas mazo){
        super(nombre);
        this.mazo = mazo;
        this.sorpresa = null;
      }
    
    @Override
    void recibeJugador (int iactual, ArrayList<Jugador> todos){
        this.sorpresa = mazo.siguiente();
        this.informe(iactual, todos);
        this.sorpresa.aplicarAJugador(iactual, todos);
      }
    
    @Override
    public String toString(){
        String informacion;
        String tab = "    ";
        informacion = (tab +"Casilla tipo Sorpresa. Nombre: " +this.getNombre() +".\n");
        return informacion;
      }
    
    
    
    
}
