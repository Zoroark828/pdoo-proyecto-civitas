
package civitas;

import java.util.ArrayList;

/** autora: @Zoroark828
 * 
 * 
 * 
 */

public class Casilla {
      static private final float FACTORALQUILERCALLE = 1.0f,
                                 FACTORALQUILERCASA = 1.0f,
                                 FACTORALQUILERHOTEL = 4.0f;
            
      private String nombre;
      private float precioCompra, precioEdificar, precioBaseAlquiler;
      private int numCasas, numHoteles;
      private TipoCasilla tipo;
      private Jugador propietario;       // No es un ArrayList ya que es 0..1. Si no vale null, contendrá un objeto de la clase Jugador
      private Sorpresa sorpresa;
      private MazoSorpresas mazo;
      
      
      // Inicializador de los atributos a un valor por defecto
      // Llamaremos a init() en los constructores
      private void init(){
          this.propietario=null;
          this.sorpresa=null;
          this.mazo=null;
          this.precioCompra=0;
          this.precioEdificar=0;
          this.precioBaseAlquiler=0;         
          this.numCasas=0;
          this.numHoteles=0;     
      }
      
      // Constructor casillas del tipo DESCANSO (Solo se introduce el nombre)
      Casilla (String nombre){
          this.init();
          this.tipo = TipoCasilla.DESCANSO;
          this.nombre=nombre;                  
      }
      
      // Constructor casillas del tipo CALLE
      Casilla (String titulo, float precioCompra, float precioEdificar, float precioBaseAlquiler){
          this.init();
          this.tipo = TipoCasilla.CALLE;
          this.nombre=titulo;  
          this.precioCompra=precioCompra;
          this.precioEdificar=precioEdificar;
          this.precioBaseAlquiler=precioBaseAlquiler;
      }
      
      // Constructor casillas del tipo SORPRESA
      Casilla (String nombre, MazoSorpresas mazo){
          this.init();
          this.tipo = TipoCasilla.SORPRESA;
          this.nombre=nombre;
          this.mazo = mazo;
      }
      
      // CAMBIO (!!). Antes tenia visibilidad paquete pero lo he cambiado a public para que VistaTextual
      // pueda acceder al metodo getNombre()
      public String getNombre(){
          return this.nombre;
      }
                  
      float getPrecioCompra(){
          return this.precioCompra;
      }
      
      float getPrecioEdificar(){
        return this.precioEdificar;  
      }
      
      float getPrecioAlquilerCompleto(){
          float precioAlquiler;          
          precioAlquiler = this.precioBaseAlquiler * (FACTORALQUILERCALLE + this.numCasas * FACTORALQUILERCASA + this.numHoteles * FACTORALQUILERHOTEL);          
          return precioAlquiler;
      }  
      
      int getNumCasas(){
          return this.numCasas;
      }
      
      int getNumHoteles(){
          return this.numHoteles;
      }
      
      public int cantidadCasasHoteles(){
          int sumaTotal = this.getNumCasas() + this.getNumHoteles();
          return sumaTotal;
      }
      
      boolean construirCasa(Jugador jugador){
          this.propietario.paga(this.precioEdificar);
          numCasas++;
          return true;
      }
      
      boolean construirHotel(Jugador jugador){
          this.propietario.paga(precioEdificar);
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
          this.propietario.paga(this.precioCompra);
          
          return true;
      }
      
      public boolean esEsteElPropietario (Jugador jugador){          
          return this.propietario == jugador;
      }
      
      void recibeJugador (int iactual, ArrayList<Jugador> todos){
          if(this.tipo == TipoCasilla.CALLE)
              this.recibeJugador_calle(iactual,todos);
          else if(this.tipo == TipoCasilla.SORPRESA)
              this.recibeJugador_sorpresa(iactual,todos);
          else if(this.tipo == TipoCasilla.DESCANSO)
              this.informe(iactual,todos);
      }
      
      private void recibeJugador_calle (int iactual, ArrayList<Jugador> todos){
          this.informe(iactual,todos);
          Jugador jugador = todos.get(iactual);
          
          if (! this.tienePropietario())
              jugador.puedeComprarCasilla();
          else
              this.tramitarAlquiler(jugador);
      }
      
      private void recibeJugador_sorpresa (int iactual, ArrayList<Jugador> todos){
          this.sorpresa = mazo.siguiente();
          this.informe(iactual, todos);
          this.sorpresa.aplicarAJugador(iactual, todos);

          
          
          
          
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
      
      void informe (int iactual, ArrayList<Jugador> todos){                    
          Diario.getInstance().ocurreEvento("El jugador " + todos.get(iactual).getNombre() 
                  +" ha caido en la casilla de siguientes parametros:\n" + this.toString() );
      }
      
     @Override
      public String toString(){
          String informacion = " ";
          String tab = "    ";
          
          switch (this.tipo){
              case DESCANSO:
              informacion = ("Casilla tipo Descanso. Nombre: " +this.nombre +".\n");
              break;
              
              case CALLE:
              if (this.tienePropietario()){
              informacion = (tab +"Casilla tipo Calle. Titulo: " +this.nombre +".\n" +tab +"Propietario: " +this.propietario.getNombre() +".\n"
                      +tab +"== PRECIOS ==>" +tab +"Compra: " +this.precioCompra +tab +"Edificar: " +this.precioEdificar 
                      +tab +"Alquiler base: " +this.precioBaseAlquiler +"\n"
                      +tab +"== CONSTRUCCIONES ==>" +tab  +"Casas: " +this.numCasas +tab +"Hoteles: " +this.numHoteles +"\n");
              }
              else
                  informacion = (tab +"Casilla tipo Calle. Titulo: " +this.nombre +".\n" +tab +"No tiene propietario.\n"
                      +tab +"== PRECIOS ==>" +tab +"Compra: " +this.precioCompra +tab +"Edificar: " +this.precioEdificar 
                      +tab +"Alquiler base: " +this.precioBaseAlquiler +"\n"
                      +tab +"== CONSTRUCCIONES ==>" +tab  +"Casas: " +this.numCasas +tab +"Hoteles: " +this.numHoteles +"\n");
              
              break;
              
              case SORPRESA:
              informacion = (tab +"Casilla tipo Sorpresa. Nombre: " +this.nombre +".\n");
              break;
          }
          
          return informacion;
      }
      
      
      
      
      
      
}
    