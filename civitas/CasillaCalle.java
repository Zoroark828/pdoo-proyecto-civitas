
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 * 
 * 
 * 
 */

public class CasillaCalle extends Casilla {
    static private final float FACTORALQUILERCALLE = 1.0f,
                               FACTORALQUILERCASA = 1.0f,
                               FACTORALQUILERHOTEL = 4.0f;
    
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    private Jugador propietario;       // No es un ArrayList ya que es 0..1. Si no vale null, contendrá un objeto de la clase Jugador
    
    CasillaCalle (String titulo, float precioCompra, float precioEdificar, float precioBaseAlquiler){
        super (titulo);
        this.precioCompra=precioCompra;
        this.precioEdificar=precioEdificar;
        this.precioBaseAlquiler=precioBaseAlquiler;
        this.numCasas = 0;
        this.numHoteles = 0;
        this.propietario = null;
    }
    
    public Jugador getPropietario(){
        return propietario;
    }
    
    public float getPrecioCompra(){
        return this.precioCompra;
      }
      
    public float getPrecioEdificar(){
        return this.precioEdificar;  
    }
      
    public float getPrecioAlquilerCompleto(){
        float precioAlquiler;          
        precioAlquiler = this.precioBaseAlquiler * (FACTORALQUILERCALLE + this.numCasas * FACTORALQUILERCASA + this.numHoteles * FACTORALQUILERHOTEL);          
        return precioAlquiler;
    }  
      
    public int getNumCasas(){
        return this.numCasas;
    }
      
    public int getNumHoteles(){
        return this.numHoteles;
    }
      
    public int cantidadCasasHoteles(){
        int sumaTotal = this.getNumCasas() + this.getNumHoteles();
        return sumaTotal;
    }
      
    boolean construirCasa(Jugador jugador){
        if (this.propietario.getEsJugadorEspeculador())
            ((JugadorEspeculador) this.propietario).paga(this.precioEdificar);
        else
            this.propietario.paga(this.precioEdificar);
        numCasas++;
        return true;
    }
      
    boolean construirHotel(Jugador jugador){
        if (this.propietario.getEsJugadorEspeculador())
            ((JugadorEspeculador) this.propietario).paga(this.precioEdificar);
        else
            this.propietario.paga(this.precioEdificar);
        this.numHoteles++;
        return true;
      }
      
    boolean derruirCasas (int numero, Jugador jugador){
        if (this.esEsteElPropietario(jugador) && numero <= this.getNumCasas()){
            this.numCasas -= numero;
            return true;
        }
        else
            return false;          
    }
      
    boolean comprar (Jugador jugador){
        this.propietario = jugador;
        if (this.propietario.getEsJugadorEspeculador())
            ((JugadorEspeculador) this.propietario).paga(this.precioCompra);
        else
            this.propietario.paga(this.precioCompra);
        return true;
    }
      
    public boolean esEsteElPropietario (Jugador jugador){          
        return this.propietario == jugador;
    }
      
    public boolean tienePropietario(){          
        return this.propietario != null;
    }
      
    public void tramitarAlquiler (Jugador jugador){                    
        if (this.tienePropietario() && ! this.esEsteElPropietario(jugador)){
            float precioCasilla = this.getPrecioAlquilerCompleto();
            jugador.pagaAlquiler(precioCasilla);              
            this.propietario.recibe(precioCasilla);                            
        }
    }
    
    void actualizaPropietarioPorConversion (Jugador nuevoPropietario, ArrayList<CasillaCalle> propiedades){
        for (CasillaCalle i: propiedades){
            i.propietario = nuevoPropietario;
        }
    }
    
    @Override
    void recibeJugador (int iactual, ArrayList<Jugador> todos){
        this.informe(iactual,todos);
        Jugador jugador = todos.get(iactual);
        if (! this.tienePropietario())
            jugador.puedeComprarCasilla();
        else
            this.tramitarAlquiler(jugador);
    }
    
    @Override
    public String toString(){
        String informacion;
        String tab = "    ";
        if (this.tienePropietario())
        informacion = (tab +"Casilla tipo Calle. Titulo: " +this.getNombre() +".\n" +tab +"Propietario: " +this.propietario.getNombre() +".\n"
                +tab +"== PRECIOS ==>" +tab +"Compra: " +this.precioCompra +tab +"Edificar: " +this.precioEdificar 
                +tab +"Alquiler base: " +this.precioBaseAlquiler +"\n"
                +tab +"== CONSTRUCCIONES ==>" +tab  +"Casas: " +this.numCasas +tab +"Hoteles: " +this.numHoteles +"\n");
        else
        informacion = (tab +"Casilla tipo Calle. Titulo: " +this.getNombre() +".\n" +tab +"No tiene propietario.\n"
                +tab +"== PRECIOS ==>" +tab +"Compra: " +this.precioCompra +tab +"Edificar: " +this.precioEdificar
                +tab +"Alquiler base: " +this.precioBaseAlquiler +"\n"
                +tab +"== CONSTRUCCIONES ==>" +tab  +"Casas: " +this.numCasas +tab +"Hoteles: " +this.numHoteles +"\n");
          return informacion;
      }
    
    
    
}
