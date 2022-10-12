
package civitas;

/** autora: @Zoroark828
 * 
 * 
 */

public class Casilla {
      static private final float FACTORALQUILERCALLE = 1.0f,
                                 FACTORALQUILERCASA = 1.0f,
                                 FACTORALQUILERHOTEL = 4.0f;
            
      private String nombre;
      private TipoCasilla tipo;
      private float precioCompra, precioEdificar, precioBaseAlquiler;
      private int numCasas, numHoteles;
      
      Casilla (TipoCasilla unTipo, String unNombre, float unPrecioCompra, float unPrecioEdificar, float unPrecioAlquilerBase){         // Constructor de Parcela
          this.tipo = unTipo;
          nombre=unNombre;
          precioCompra=unPrecioCompra;
          precioEdificar=unPrecioEdificar;
          precioBaseAlquiler=unPrecioAlquilerBase;         
          numCasas=0;
          numHoteles=0;          
      }
      
      public String getNombre(){
          return nombre;
      }
                  
      public float getPrecioCompra(){
          return precioCompra;
      }
      
      public float getPrecioEdificar(){
        return precioEdificar;  
      }
                  
      public int getNumCasas(){
          return numCasas;
      }
      
      public int getNumHoteles(){
          return numHoteles;
      }

      public float getPrecioAlquilerCompleto(){
          float precioAlquiler;          
          precioAlquiler = precioBaseAlquiler * (FACTORALQUILERCALLE + numCasas * FACTORALQUILERCASA + numHoteles * FACTORALQUILERHOTEL);          
          return precioAlquiler;
      }      
      
      public boolean construirCasa(){
          numCasas++;          
          return true;
      }
      
      public boolean construirHotel(){          
          numHoteles++;          
          return true;
      }
            
      public boolean igualdadIdentidad (Casilla otraCasilla){          
          return this == otraCasilla;
      }
      
      public boolean igualdadEstado (Casilla otraCasilla){
          return this.nombre.equals(otraCasilla.nombre) &&
                  this.precioCompra == otraCasilla.precioCompra &&
                  this.precioEdificar == otraCasilla.precioEdificar &&
                  this.numCasas == otraCasilla.numCasas &&
                  this.numHoteles == otraCasilla.numHoteles &&
                  this.precioBaseAlquiler == otraCasilla.precioBaseAlquiler;                 
      }
      
      public String toString(){
          
          return (tipo +" " +nombre +"." +"\n=== PRECIOS ===" +"\nCompra: " +precioCompra +"\nEdificar: " 
                  +precioEdificar +"\nAlquiler base: " +precioBaseAlquiler +"\n== CONSTRUCCIONES ===" 
                  +"\nCasas: " +numCasas +"\nHoteles: " +numHoteles);                   
      }
      
      
}    
      
      

