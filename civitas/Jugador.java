
package civitas;

import java.util.ArrayList;


/** autora: @Zoroark828
 *      
 *      
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
    private ArrayList<CasillaCalle> propiedades;
    private boolean esJugadorEspeculador;   // He añadido esta variable para ayudarme a hacer los castings en algunos metodos
    
    // CAMBIO (!!). Antes tenia visibilidad paquete y lo tuve que cambiar a public porque si no en la clase JuegoTexto no
    // puedo crear nuevos jugadores
    public Jugador (String nombre){
        this.nombre = nombre;
        this.casillaActual = 0;
        this.puedeComprar = false;
        this.saldo = SaldoInicial;
        this.propiedades = new ArrayList();
        this.esJugadorEspeculador = false;
    }

    protected Jugador (Jugador otro){
        this.nombre = otro.nombre;
        this.casillaActual = otro.casillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = otro.propiedades;
        this.esJugadorEspeculador = otro.esJugadorEspeculador;
    }
    
    protected static int getCasasMax(){
        return CasasMax;
    }
    
    static int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    protected static int getHotelesMax(){
        return HotelesMax;
    }
    
    private static float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    int cantidadCasasHoteles(){
        int numCasasHoteles = 0;
        for (int i = 0; i < propiedades.size(); i++){
            CasillaCalle propiedadActual = propiedades.get(i);
            numCasasHoteles = propiedadActual.getNumCasas() + propiedadActual.getNumHoteles();            
        }
        return numCasasHoteles;
    } 
    
    @Override
    public int compareTo (Jugador otro){
        return Float.compare(otro.saldo,this.saldo);
    }
    
    boolean comprar (CasillaCalle titulo){
        boolean result = false;
        if (this.puedeComprar){
            float precio = titulo.getPrecioCompra();
            
            if(this.puedoGastar(precio)){
                result = titulo.comprar(this);
                propiedades.add(titulo);
                Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" compra la " +titulo.getNombre());
                this.puedeComprar = false;
            }
            else
                Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" no tiene saldo para comprar la " +titulo.getNombre());
        }
        
        return result;
    }
    
    boolean construirCasa (int ip){
        boolean result = false;
        boolean existe = this.existeLaPropiedad(ip);
        
        if (existe){
            CasillaCalle propiedad = this.propiedades.get(ip);
            boolean puedoEdificar = this.puedoEdificarCasa(propiedad);
            
            if (puedoEdificar){
                result = propiedad.construirCasa(this);
                Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" construye casa en la " +propiedad.getNombre());
            }
        }
        
        return result;
    }
    
    boolean construirHotel (int ip){
        boolean result = false;
        
        if (this.existeLaPropiedad(ip)){
            CasillaCalle propiedad = propiedades.get(ip);
            boolean puedoEdificarHotel = this.puedoEdificarHotel(propiedad);
            
            if (puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                propiedad.derruirCasas(CasasPorHotel,this);
                Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" construye un hotel en la " +propiedad.getNombre());
            }
        }
        
        return result;
    }
    
    boolean enBancarrota(){
        return this.saldo < 0;
    }
    
    private boolean existeLaPropiedad (int ip){
    // Si el array propiedades tiene size = 5, si ip = 5, ip no es un indice valido
    // Si propiedades.size() es 5 e ip = 4, ip es un indice valido porque sus indices irán de 0 a 4
        return this.propiedades.size() > ip;
    }
    
    // CAMBIO (!!). En el DC getCasillaActual() tiene visibilidad paquete. La he cambiado a publica para poder
    // usarla en el metodo actualiza() de la clase VistaTextual y poder imprimir informacion sobre la casilla actual
    public int getCasillaActual(){
        return this.casillaActual;
    }
    
    // CAMBIO (!!). En el DC getNombre() tiene visibilidad protected. La he cambiado a publica para poder
    // usarla en el metodo actualiza() de la clase VistaTextual y poder obtener el nombre del jugador actual
    public String getNombre(){
        return this.nombre;
    }
    
    public boolean getEsJugadorEspeculador(){
        return esJugadorEspeculador;
    }
    
    // CAMBIO (!!). En el DC getPropiedades() tiene visibilidad protected. La he cambiado a publica para poder
    // usarla en el metodo elegirPropiedad() de la clase VistaTextual y poder obtener los nombres de las propiedades
    public ArrayList<CasillaCalle> getPropiedades(){
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
        int casillaDisplay = numCasilla + 1;    // Variable que imprimiremos. Le sumamos 1 para que el usuario nunca vea una "casilla 0",
                                                // que queda bastante anti-intuitivo en la vida real
        Diario.getInstance().ocurreEvento("El jugador " +this.nombre +" se ha movido a la casilla numero " +casillaDisplay +".");
        return true;
    }
    
    boolean paga (float cantidad){        
        return this.modificarSaldo(-1 * cantidad);
    }
    
    boolean pagaAlquiler (float cantidad){
        boolean result = false;
        
        if (this.esJugadorEspeculador)
            result = ((JugadorEspeculador) this).paga(cantidad);
        else
            result = this.paga(cantidad);
        
        return result;
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
    
    private boolean puedoEdificarCasa (CasillaCalle propiedad){        
        float precioEdificar = propiedad.getPrecioEdificar();
        int casasMax;   // Hacemos casting si es un jugador especulador
        if (this.getEsJugadorEspeculador())
            casasMax = ((JugadorEspeculador) this).getCasasMax();
        else
            casasMax = this.getCasasMax();
        
        boolean puedoEdificarCasa = this.puedoGastar(precioEdificar) && propiedad.getNumCasas() < casasMax;
        return puedoEdificarCasa;
    }
    
    private boolean puedoEdificarHotel (CasillaCalle propiedad){
        boolean puedoEdificarHotel = false;
        float precio = propiedad.getPrecioEdificar();
        int hotelesMax;   // Hacemos casting si es un jugador especulador
        if (this.getEsJugadorEspeculador())
            hotelesMax = ((JugadorEspeculador) this).getHotelesMax();
        else
            hotelesMax = this.getHotelesMax();
        
        if (this.puedoGastar(precio) && propiedad.getNumHoteles() < hotelesMax && propiedad.getNumCasas() >= CasasPorHotel)
            puedoEdificarHotel = true;
        return puedoEdificarHotel;
    }
    
    private boolean puedoGastar (float precio){
        return this.saldo >= precio;
    }
    
    boolean recibe (float cantidad){        
        return this.modificarSaldo(cantidad);
    }
    
    // CAMBIO (!!). Lo cambié de package a public para poder utilizarlo en el Controlador
    public boolean tieneAlgoQueGestionar(){
        return !this.propiedades.isEmpty();
    }
    
    Jugador convertir(){            // Metodo que convierte a un jugador en jugador especulador
        this.esJugadorEspeculador = true;
        Jugador especulador = new JugadorEspeculador (this);
        return especulador;
    }
    
    @Override
    public String toString(){
        int numeroCasillaReal = this.casillaActual + 1;     // Para que la salida sea la casilla 1 en vez de la casilla 0,
                                                            // que es menos intuitivo en la vida real
        String informacion = "El jugador " +this.nombre +" se encuentra en la casilla numero " +numeroCasillaReal 
                +".\nSu saldo es de " +this.saldo +" euros.\n";
        return informacion;
    }
    
    
    
    
    
    
}
