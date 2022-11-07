
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
      
      String getNombre(){
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
          //////////////////////////////////////////////////////////////////
          // Se implementará en la proxima práctica
          //////////////////////////////////////////////////////////////////
                    
          return true;
      }
      
      boolean construirHotel(Jugador jugador){
          //////////////////////////////////////////////////////////////////
          // Se implementará en la proxima práctica
          //////////////////////////////////////////////////////////////////
          
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
      
      public String toString(){
          String informacion;
          
          switch (this.tipo){
              case DESCANSO:
              informacion = ("Casilla tipo Descanso. Nombre: " +this.nombre +".\n");
              break;
              
              case CALLE:
              if (this.tienePropietario()){
              informacion = ("Casilla tipo Calle. Titulo: " +this.nombre +". \nPropietario: " +this.propietario
                      +"\n=== PRECIOS ===" +"\nCompra: " +this.precioCompra
                      +"\nEdificar: " +this.precioEdificar +"\nAlquiler base: " +this.precioBaseAlquiler 
                      +"\n== CONSTRUCCIONES ===" +"\nCasas: " +this.numCasas +"\nHoteles: " +this.numHoteles +"\n");
              }
              else
                  informacion = ("Casilla tipo Calle. Titulo: " +this.nombre +". \nNo tiene propietario. "
                      +"\n=== PRECIOS ===" +"\nCompra: " +this.precioCompra
                      +"\nEdificar: " +this.precioEdificar +"\nAlquiler base: " +this.precioBaseAlquiler 
                      +"\n== CONSTRUCCIONES ===" +"\nCasas: " +this.numCasas +"\nHoteles: " +this.numHoteles +"\n");
              
              break;
              
              case SORPRESA:
              informacion = ("Casilla tipo Sorpresa. Nombre: " +this.nombre +".\n");
              break;
              
              default:
                  informacion = "ERROR. Esta casilla no tiene tipo.";
          }
          
          return informacion;
      }
      
      boolean comprar (Jugador jugador){
          //////////////////////////////////////////////////////////////////
          // Se implementará en la proxima práctica
          //////////////////////////////////////////////////////////////////
          
          return true;
      }
      
      public boolean esEsteElPropietario (Jugador jugador){          
          return this.propietario == jugador;
      }
      
      void informe (int iactual, ArrayList<Jugador> todos){                    
          Diario.getInstance().ocurreEvento("El jugador " + todos.get(iactual).getNombre() 
                  +" ha caido en la casilla de siguientes parametros:\n" + this.toString() );
      }
      
      void recibeJugador (int iactual, ArrayList<Jugador> todos){
          //////////////////////////////////////////////////////////////////
          // Se implementará en la proxima práctica
          //////////////////////////////////////////////////////////////////
          
      }
      
      private void recibeJugador_calle (int iactual, ArrayList<Jugador> todos){
          //////////////////////////////////////////////////////////////////
          // Se implementará en la proxima práctica
          //////////////////////////////////////////////////////////////////
          
      }
      
      private void recibeJugador_sorpresa (int iactual, ArrayList<Jugador> todos){
          //////////////////////////////////////////////////////////////////
          // Se implementará en la proxima práctica
          //////////////////////////////////////////////////////////////////
          
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
      
      
     
}
    
    
    /*
    main para hacer pruebas con esta clase a través de la opcion "Run file" de Netbeans
    
    class pruebasCasillas{
        public static void main(String[] args){
            Casilla descanso = new Casilla ("SPA descanso");
            Casilla sorpresa = new Casilla ("Sorpresitas guays", null);
            Casilla calle1 = new Casilla ("Azul",10,15,20);
            Casilla calle2 = new Casilla ("Roja",20,25,30);
        
            System.out.println(descanso.toString());
        
            System.out.println(sorpresa.toString());
        
            System.out.println(calle1.toString());
        
            System.out.println(calle2.toString());
        
            System.out.println(calle2.tienePropietario());
         
        }
    
    }
    */
    





