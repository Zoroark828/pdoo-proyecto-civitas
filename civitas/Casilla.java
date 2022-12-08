
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 * 
 * 
 * 
 */

public class Casilla {
    private String nombre;
      
    // Su unico constructor es el siguiente, que equivale al constructor de las casillas de Descanso
    Casilla (String nombre){
        this.nombre=nombre;                  
    }
    
    // CAMBIO (!!). Antes tenia visibilidad paquete pero lo he cambiado a public para que VistaTextual
    // pueda acceder al metodo getNombre()
    public String getNombre(){
        return this.nombre;
    }
    
    void informe (int iactual, ArrayList<Jugador> todos){                    
        Diario.getInstance().ocurreEvento("El jugador " + todos.get(iactual).getNombre() 
                +" ha caido en la casilla de siguientes parametros:\n" + this.toString() );
    }
    
    
    
    
    
    
    
    // recibeJugador y toString se sobreescribiran en las dos clases hijas
    void recibeJugador (int iactual, ArrayList<Jugador> todos){
        this.informe(iactual,todos);
    }
    
    @Override
    public String toString(){
        String informacion;
        String tab = "    ";
        informacion = (tab +"Casilla tipo Descanso. Nombre: " +this.nombre +".\n");          
        return informacion;
    }
      
    
    
}
    