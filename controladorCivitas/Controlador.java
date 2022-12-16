
package controladorCivitas;

/** autora: @Zoroark828
 * 
 * 
 * 
 */

import GUI.CivitasView;
import GUI.Vista;
import civitas.CivitasJuego;
import civitas.GestionInmobiliaria;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;

public class Controlador {
    private CivitasJuego juego;
    private Vista vista;
    
    // CAMBIO (!!). Tenia visibilidad paquete pero tuve que ponerla en public para poder usarlo en el archivo main
    public Controlador (CivitasJuego juego, CivitasView vista){
        this.juego = juego;
        this.vista = vista;
    }
    
    public void juega(){
        while (!this.juego.finalDelJuego()){
            vista.actualiza();
            vista.pausa();              // Esperamos a que el usuario interactue entre turno y turno
            
            
            OperacionJuego siguientePaso = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(siguientePaso);
            
            if (siguientePaso != OperacionJuego.PASAR_TURNO)
                vista.mostrarEventos();
            
            
            switch (siguientePaso){
                case COMPRAR:
                    Respuesta response = vista.comprar();
                    if (response == Respuesta.SI)
                        juego.comprar();
                    juego.siguientePasoCompletado(siguientePaso);
                    vista.actualiza();
                    break;
                
                case GESTIONAR:
                    // Este if siguiente lo añadí para que, si un jugador no tiene NINGUNA propiedad, no pueda elegir si quiere
                    // construir una casa o un hotel. Como no tiene propiedades, si elegía una de estas dos opciones,
                    // el programa entraba en un bucle infinito por el metodo elegirPropiedad() de VistaTextual. Pediría al
                    // usuario el valor de la ip que quiere elegir, pero como no tiene propiedades, no puede introducir valor alguno.
                    if (juego.getJugadorActual().tieneAlgoQueGestionar()){
                        OperacionInmobiliaria operacionElegida = vista.elegirOperacion();
                        int ip = 0;
                        if (operacionElegida != OperacionInmobiliaria.TERMINAR)
                            ip = vista.elegirPropiedad();

                        GestionInmobiliaria gestor = new GestionInmobiliaria(operacionElegida, ip);

                        switch (operacionElegida){
                            case TERMINAR:
                                juego.siguientePasoCompletado(siguientePaso);
                                break;

                            case CONSTRUIR_CASA:
                                juego.construirCasa(ip);
                                break;

                            case CONSTRUIR_HOTEL:
                                juego.construirHotel(ip);
                                break;
                        }
                    }
                    else{
                        juego.siguientePasoCompletado(siguientePaso);
                    }
                    break;
            }
        }
        
        if (juego.finalDelJuego()){
            juego.ranking();
            vista.actualiza();
            // Acaba el juego
        }
    }
    
    
    
    
    
    
}
