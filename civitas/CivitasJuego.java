
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/** autora: @Zoroark828
 *
 * 
 */

public class CivitasJuego {
    private int indiceJugadorActual;
    private Tablero tablero;
    private MazoSorpresas mazo;
    private GestorEstados gestorEstados;
    private EstadoJuego estado;
    private ArrayList<Jugador> jugadores;
    
    public CivitasJuego (ArrayList<String> nombres, boolean debug){
        // Inicializamos los jugadores añadiendo un jugador por cada nombre introducido como parametro
        jugadores = new ArrayList();
        for (int i = 0; i < nombres.size(); i++){
            jugadores.add(new Jugador(nombres.get(i)));            
        }
        
        // Fijamos el estado actual como el estado inicial descrito por el Gestor de Estados
        this.gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        
        // Ponemos el dado en el modo debug que estuviera indicado en los parametros
        Dado.getInstance().setDebug(debug);
        
        // Inicializamos el inidice del jugador actual, que tendrá el primer turno
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());

        // Creamos el mazo y el tablero, y los inicializamos con los metodos correspondientes de esta misma clase
        this.mazo = new MazoSorpresas(debug);        
        this.tablero = new Tablero();
        this.inicializaTablero(mazo);
        this.inicializaMazoSorpresas();
    }
    
    private void avanzaJugador(){
        Jugador jugadorActual = this.getJugadorActual();
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = this.tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = this.tablero.getCasilla(posicionNueva);
        
        this.contabilizarPasosPorSalida();  // En el DS pone que se le pasa por parametro el jugadorActual pero eso se hace ya
                                            // dentro del metodo contabilizarPasosPorSalida(), que no recibe ningun parametro
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual,jugadores);
    }
    
    public boolean comprar(){
        Jugador jugadorActual = this.getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        Casilla casilla = tablero.getCasilla(numCasillaActual);
        boolean res = jugadorActual.comprar((CasillaCalle) casilla);
        
        return res;
    }
    
    public boolean construirCasa (int ip){
        getJugadorActual().construirCasa(ip);
        return true;
    }
    
    public boolean construirHotel (int ip){
        getJugadorActual().construirHotel(ip);
        return true;
    }
    
    private void contabilizarPasosPorSalida(){
        if (this.tablero.computarPasoPorSalida())
            getJugadorActual().pasaPorSalida();        
    }
    
    public boolean finalDelJuego(){                             // Devuelve true si al menos un jugador esta en bancarrota
        boolean bancarrota = false;
        for (int i = 0; i < jugadores.size(); i++){
            bancarrota = bancarrota || jugadores.get(i).enBancarrota();
        }
        return bancarrota;
    }
    
    public int getInidiceJugadorActual(){
        return this.indiceJugadorActual;
    }
    
    public Jugador getJugadorActual(){
        return this.jugadores.get(indiceJugadorActual);
    }
    
    public ArrayList<Jugador> getJugadores(){
        return this.jugadores;
    }
    
    public Tablero getTablero(){
        return this.tablero;
    }
    
    private void inicializaMazoSorpresas(){
        // Primero añadimos la carta sorpresa que convierte a un jugador en especulador
        // Hay que tener en cuenta que si probamos el juego en modo debug, el primer jugador que llegue a una casilla
        // tipo sorpresa será el que se convierta en especulador
        this.mazo.alMazo(new SorpresaConvertirme ("El jugador se ha convertido en un jugador especulador.",0));
        
        // Añadimos 3 sorpresas negativas del tipo PagarCobrar
        for (int i = 0; i <= 3; i++){
            this.mazo.alMazo(new SorpresaPagarCobrar ("El jugador va a pagar 200 euros", -200));
        }
        
        // Añadimos 3 sorpresas positivas del tipo PagarCobrar
        for (int i = 0; i <= 3; i++){
            this.mazo.alMazo(new SorpresaPagarCobrar ("El jugador va a cobrar 400 euros", 400));
        }
        
        // Añadimos 2 sorpresas negativas del tipo PorCasaHotel
        for (int i = 0; i <= 3; i++){
            this.mazo.alMazo(new SorpresaPorCasaHotel ("El jugador debe pagar 100 euros por cada casa y hotel a su propiedad", -100));
        }
        
        // Añadimos 2 sorpresas positivas del tipo PorCasaHotel
        for (int i = 0; i <= 3; i++){
            this.mazo.alMazo(new SorpresaPorCasaHotel ("El jugador va a cobrar 100 euros por cada casa y hotel a su propiedad", 100));
        }
        
        // Ya hemos añadido las 10 cartas que componen el mazo
        // Habria que barajarlas, pero de eso se encarga el metodo siguiente() del mazo, y ya lo usaremos en el futuro
        // Si el modo debug esta activado, ten en cuenta que las sorpresas saldrán en este orden en el que las creamos
    }
    
    private void inicializaTablero (MazoSorpresas mazo){
        // La salida se creo con el constructor de Tablero
        // Creamos 14 casillas del tipo calle, 4 del tipo sorpresa y una de tipo parking (descanso)
        tablero.añadeCasilla(new CasillaCalle("Calle Arcilla",400,100,50));
        tablero.añadeCasilla(new CasillaCalle("Calle Terracota",450,150,75));
        tablero.añadeCasilla(new CasillaSorpresa("SORPRESAAA",mazo));
        tablero.añadeCasilla(new CasillaCalle("Calle Gres",450,150,75));        
        tablero.añadeCasilla(new CasillaCalle("Calle Esmalte",500,200,100));
        tablero.añadeCasilla(new CasillaSorpresa("SORPRESAAA",mazo));
        tablero.añadeCasilla(new CasillaCalle("Calle Porcelana",550,250,125));        
        tablero.añadeCasilla(new CasillaCalle("Calle Mayolica",550,250,125));        
        tablero.añadeCasilla(new CasillaCalle("Calle Biscuit",550,250,125));
        tablero.añadeCasilla(new Casilla("Parking Arenisca"));
        tablero.añadeCasilla(new CasillaCalle("Calle Fayenza",600,300,150));        
        tablero.añadeCasilla(new CasillaCalle("Calle Loza",600,300,150));        
        tablero.añadeCasilla(new CasillaCalle("Calle Teja",600,300,150));
        tablero.añadeCasilla(new CasillaSorpresa("SORPRESAAA",mazo));
        tablero.añadeCasilla(new CasillaCalle("Calle Caolin",600,300,150));        
        tablero.añadeCasilla(new CasillaCalle("Calle Negra",600,300,150));
        tablero.añadeCasilla(new CasillaSorpresa("SORPRESAAA",mazo));
        tablero.añadeCasilla(new CasillaCalle("Calle Blanca",700,400,200));        
        tablero.añadeCasilla(new CasillaCalle("Calle Ladrillo",700,400,200));        
    }
    
    private void PasarTurno(){
        if(this.indiceJugadorActual == (jugadores.size() - 1))
            this.indiceJugadorActual = 0;
        else
            this.indiceJugadorActual++;
    }
    
    // CAMBIO (!!). En el DC ranking() era private, pero lo he tenido que poner en public para poder usarlo
    // en el Controlador
    public ArrayList<Jugador> ranking(){
        Collections.sort(this.jugadores, Jugador::compareTo);
        return this.jugadores;
    }
    
    public OperacionJuego siguientePaso(){
        Jugador jugadorActual = this.getJugadorActual();
        OperacionJuego operacion = gestorEstados.siguienteOperacion(jugadorActual, this.estado);
        switch (operacion){
            case PASAR_TURNO :
                this.PasarTurno();
                this.siguientePasoCompletado(operacion);
                break;
            case AVANZAR :
                this.avanzaJugador();
                this.siguientePasoCompletado(operacion);
                break;
        }
        
        return operacion;
    }
    
    public void siguientePasoCompletado (OperacionJuego operacion){
        this.estado = this.gestorEstados.siguienteEstado (this.getJugadorActual(), this.estado, operacion);
    }
    
    
    
    
    
    
    
    
}
