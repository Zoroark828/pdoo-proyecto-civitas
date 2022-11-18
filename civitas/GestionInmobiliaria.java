
package civitas;

/** autora: @Zoroark828
 *
 *  Esta clase almacena la información de una operacion inmobiliaria
 */

public class GestionInmobiliaria {
    private int propiedad;                          // Indice de la propiedad sobre la que se opera (de las propiedades del jugador)
    private OperacionInmobiliaria operacion;        // Tipo de operacion que se está aplicando
    
    public GestionInmobiliaria (OperacionInmobiliaria gest, int ip){
        this.operacion = gest;
        this.propiedad = ip;
    }
    
    public OperacionInmobiliaria getOperacion(){
        return this.operacion;
    }
    
    public int getPropiedad(){
        return this.propiedad;
    }
    
    
}
