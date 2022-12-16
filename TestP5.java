
import GUI.CapturaNombres;
import GUI.CivitasView;
import GUI.VistaDado;
import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.util.ArrayList;


/** autora: @Zoroark828
 *
 * 
 */

public class TestP5 {
    
    public static void main(String[] args) {
        CivitasView vista = new CivitasView();
        CapturaNombres capturadora = new CapturaNombres(vista,true); // true porque el juego no hará nada hasta que se cierre la ventana
        ArrayList<String> nombres = capturadora.getNombres();
        
        VistaDado.createInstance(vista);
        CivitasJuego juego = new CivitasJuego(nombres,true);
        Controlador controlador = new Controlador (juego,vista);
        vista.setCivitasJuego(juego);
        
        controlador.juega();
        
    }
}
