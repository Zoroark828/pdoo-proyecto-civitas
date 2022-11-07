
package civitas;

import java.util.ArrayList;


/** autora: @Zoroark828
 *      
 *      DUDAS PREGUNTADAS EN CLASE:
 *      CasasMax es final? Como se si una variable es final en el DC?
 *      Las tres variables de factores de la clase Casilla son final porque están escritas en mayuscula
 *      Las variables de esta clase en un principio no lo son porque no estan escritas en mayusculas
 * 
 *      Para que hacemos el metodo getCasasMax en privado si la clase ya puede acceder a casasMax?
 *      La profesora me dijo que realmente no tiene sentido que exista ese metodo, porque si es privado no puede
 *      ser accedido desde otra clase, y dentro de Jugador podemos usar directamente el atributo. Así que, o su
 *      privacidad es una errata en el DC, o getCasasMax es un metodo implementado para nada.
 * 
 */

public class Jugador implements Comparable<Jugador> {
    protected static int CasasMax = 4;
    protected static int HotelesMax = 4;
    protected static int CasasPorHotel = 4;
    protected static float PasoPorSalida = 1000;
    private static float SaldoInicial = 7500;
    
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    private ArrayList<Casilla> propiedades;
    
    Jugador (String nombre){
        this.nombre = nombre;
        this.casillaActual = 0;
        this.puedeComprar = false;
        this.saldo = SaldoInicial;
        this.propiedades = new ArrayList();  
    }

    protected Jugador (Jugador otro){
        this.nombre = otro.nombre;
        this.casillaActual = otro.casillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = otro.propiedades;
    }
    
    private static int getCasasMax(){
        return CasasMax;
    }
    
    static int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    private static int getHotelesMax(){
        return HotelesMax;
    }
    
    private static float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    int cantidadCasasHoteles(){
        int numCasasHoteles = 0;
        for (int i = 0; i < propiedades.size(); i++){
            numCasasHoteles = propiedades.get(i).getNumCasas() + propiedades.get(i).getNumHoteles();            
        }
        return numCasasHoteles;
    } 
    
    public int compareTo (Jugador otro){
        // Posibles resultados: 0 -> ambos saldos son iguales
        //                      Valor negativo -> this.saldo < otro.saldo
        //                      Valor positivo -> this saldo > otro.saldo
        return Float.compare(this.saldo, otro.saldo);
    }
    
    boolean comprar (Casilla titulo){
        //////////////////////////////////////////////////////////////////
        // Se implementará en la proxima práctica
        //////////////////////////////////////////////////////////////////
        
        return true;        
    }
    
    boolean construirCasa (int ip){
        //////////////////////////////////////////////////////////////////
        // Se implementará en la proxima práctica
        //////////////////////////////////////////////////////////////////
        
        return true;
    }
    
    boolean construirHotel (int ip){
        //////////////////////////////////////////////////////////////////
        // Se implementará en la proxima práctica
        //////////////////////////////////////////////////////////////////
        
        return true;
    }
    
    boolean enBancarrota(){
        return this.saldo < 0;
    }
    
    private boolean existeLaPropiedad (int ip){
    // Si el array propiedades tiene size = 5, si ip = 5, ip no es un indice valido
    // Si propiedades.size() es 5 e ip = 4, ip es un indice valido porque sus indices irán de 0 a 4
        return this.propiedades.size() > ip;
    }
    
    int getCasillaActual(){
        return this.casillaActual;
    }
    
    protected String getNombre(){
        return this.nombre;
    }
    
    protected ArrayList<Casilla> getPropiedades(){
        return this.propiedades;
    }
    
    boolean getPuedeComprar(){
        return this.puedeComprar;
    }
    
    protected float getSaldo(){
        return this.saldo;
    }
    
    boolean modificarSaldo (float cantidad){
        this.saldo += cantidad;
        Diario.getInstance().ocurreEvento("Se ha modificado el sueldo del jugador " +this.nombre +" en " +cantidad +" euros.");
        return true;
    }
    
    boolean moverACasilla (int numCasilla){
        this.casillaActual = numCasilla;
        this.puedeComprar = false;
        Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" se ha movido a la casilla numero " +numCasilla +".");
        return true;
    }
    
    boolean paga (float cantidad){        
        return this.modificarSaldo(-1 * cantidad);
    }
    
    boolean pagaAlquiler (float cantidad){
        return this.paga(cantidad);
    }
    
    boolean pasaPorSalida(){
        this.recibe(PasoPorSalida);
        Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" ha pasado por Salida y ha recibido " +PasoPorSalida +" euros.");
        return true;
    }
    
    boolean puedeComprarCasilla(){
        this.puedeComprar = true;
        return this.puedeComprar;
    }
    
    private boolean puedoEdificarCasa (Casilla propiedad){        
        return this.puedoGastar(propiedad.getPrecioEdificar()) 
                && propiedad.getNumCasas() < CasasMax;
    }
    
    private boolean puedoEdificarHotel (Casilla propiedad){
        return this.puedoGastar(propiedad.getPrecioEdificar()) 
                && propiedad.getNumHoteles() < HotelesMax
                && propiedad.getNumCasas() == CasasMax;
    }
    
    private boolean puedoGastar (float precio){
        return this.saldo >= precio;
    }
    
    boolean recibe (float cantidad){        
        return this.modificarSaldo(cantidad);
    }
    
    boolean tieneAlgoQueGestionar(){
        return this.propiedades != null;
    }
    
    public String toString(){
        String informacion = "El jugador " +this.nombre +" se encuentra en la casilla numero" +this.casillaActual 
                +".\nSu saldo es de " +this.saldo +" euros.";
        return informacion;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
