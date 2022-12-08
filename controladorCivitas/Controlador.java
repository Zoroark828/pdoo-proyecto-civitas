
package controladorCivitas;

/** autora: @Zoroark828
 * 
 * 
 * 
 */

//import civitas.Casilla;       (No usado)
import civitas.CivitasJuego;
import civitas.GestionInmobiliaria;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;
import vistaTextualCivitas.Vista;
import vistaTextualCivitas.VistaTextual;

public class Controlador {
    private static final String separador = "*==================================================================*";
    private CivitasJuego juego;
    private Vista vista;
    
    // CAMBIO (!!). Tenia visibilidad paquete pero tuve que ponerla en public para poder usarlo en JuegoTexto
    public Controlador (CivitasJuego juego, VistaTextual vista){
        this.juego = juego;
        this.vista = vista;
    }
    
    public void juega(){
        while (!this.juego.finalDelJuego()){
            System.out.println("*====   =SITUACION ACTUAL=   ======================================*\n");
            vista.actualiza();
            System.out.println(separador);
            vista.pausa();              // Esperamos a que el usuario interactue entre turno y turno
            
            
            
            System.out.println("*====   =MENSAJES Y ACCIONES=   ===================================*");
            OperacionJuego siguientePaso = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(siguientePaso);
            
            if (siguientePaso != OperacionJuego.PASAR_TURNO)
                vista.mostrarEventos();
            System.out.println(separador);
            vista.pausa();
            
            
            
            switch (siguientePaso){
                case COMPRAR:
                    Respuesta response = vista.comprar();
                    if (response == Respuesta.SI)
                        juego.comprar();
                    juego.siguientePasoCompletado(siguientePaso);
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
