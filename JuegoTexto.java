// Fuera de los otros paquetes

/** autora: @Zoroark828
 *
 * 
 */

import vistaTextualCivitas.Vista;
import civitas.CivitasJuego;
import civitas.Jugador;
import controladorCivitas.Controlador;
import java.util.ArrayList;
import vistaTextualCivitas.VistaTextual;

public class JuegoTexto {
    
    public static void main(String[] args) {
        // Creamos un array de nombres que después pasaremos al objeto de CivitasJuego
        // En las reglas pone que deben ser entre 2 y 4 jugadores, pero esto no lo comprueba el modelo porque eres
        // tu mismo el que mete el numero de jugadores en esta clase main.
        ArrayList<String> nombresJugadores = new ArrayList();
        nombresJugadores.add("1");
        nombresJugadores.add("2");
        nombresJugadores.add("3");
        nombresJugadores.add("4");
        CivitasJuego juego = new CivitasJuego(nombresJugadores,true);
        
        // Creamos la Vista a partir del Modelo recien creado
        VistaTextual vista = new VistaTextual (juego);
        
        // Creamos el Controlador
        Controlador controlador = new Controlador (juego, vista);
        
        // Llamamos al metodo juega() del Controlador
        controlador.juega();
        
        
        
    }
}
