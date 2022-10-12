
package civitas;

/** autora: @Zoroark828
 *
 * 
 */

public class Civitas {
    
    public static void main(String[] args) {
        
        ////////////////// PRUEBAS CASILLA //////////////
        System.out.println("\t\t\t!!!!!!!==== PRUEBAS CLASE CASILLA ====!!!!!!!");
        
        Casilla calleazul = new Casilla(TipoCasilla.CALLE, "azul", 10, 15, 3);
        Casilla calleamarilla = new Casilla(TipoCasilla.CALLE, "amarilla", 20, 15, 3);
        Casilla calleverde = new Casilla(TipoCasilla.CALLE, "verde", 30, 15, 3);
        Casilla calleroja = new Casilla(TipoCasilla.CALLE, "roja", 40, 15, 3);

        System.out.println(calleazul.toString());
        
        System.out.println("\nPrecio alquiler completo: " +calleazul.getPrecioAlquilerCompleto());
        
        System.out.println("\n\n(((Construimos una casa y 3 hoteles)))\n");
        
        calleazul.construirCasa();
        calleazul.construirHotel();
        calleazul.construirHotel();
        calleazul.construirHotel();
        calleazul.construirHotel();
        
        System.out.println(calleazul.toString());
        
        System.out.println("\nPrecio Alquiler completo: " +calleazul.getPrecioAlquilerCompleto()+"\n\n");

        
              
        
        //////////////// PRUEBAS TABLERO //////////////
        System.out.println("\t\t\t!!!!!!!==== PRUEBAS CLASE TABLERO ====!!!!!!!");
        
        Tablero tablero1 = new Tablero();
        tablero1.añadeCasilla(calleazul);
        tablero1.añadeCasilla(calleamarilla);
        tablero1.añadeCasilla(calleverde);
        tablero1.añadeCasilla(calleroja);
        System.out.println(tablero1.getCasilla(0));         // Imprime las casillas del tablero como
        System.out.println("\n\n" +tablero1.getCasilla(1));         // pide el toString() de la clase Casilla
        
        System.out.println("\nEstamos en la posicion 19. Tiramos 1 vez. Nueva posicion: " +tablero1.nuevaPosicion(19, 1));
        System.out.println("\nEstamos en la posicion 19. Tiramos 3 veces. Nueva posicion: " +tablero1.nuevaPosicion(19, 3) +"\n\n");
        
        
        
        
        /////////////// PRUEBAS DADO /////////////////
        System.out.println("\t\t\t!!!!!!!==== PRUEBAS CLASE DADO ====!!!!!!!");
        
        //Dado.getInstance().setDebug(true);
        System.out.println("Numero del dado: " +Dado.getInstance().tirar());
        System.out.println("Ultimo resultado: " +Dado.getInstance().getUltimoResultado() +"\n");
        
                
        
        
        //////////////////////////////////////////
        ///// PRUEBAS PEDIDAS EN LA PRACTICA 1 /////
        //////////////////////////////////////////
        System.out.println("\t\t\t!!!!!!!==== PRUEBAS PEDIDAS EN LA PRACTICA 1 ====!!!!!!!");
        /* 1 - Llama 100 veces al metodo quienEmpieza() de la clase Dado considerando 4 jugadores
        for (int i = 0; i<50; i++){
        System.out.println(Dado.getInstance().quienEmpieza(4));
        }
        */
        
        /* Imprimí 50 valores aleatorios y salieron estas cantidades de cada numero:
        0 == 15/50
        1 == 11/50
        2 == 12/50
        3 == 12/50
        Como se observa, son numeros bastante aproximados, ya que por probabilidad los numeros se van repitiendo con el tiempo
        */
        
        //6 - Crea algunos bucles sobre la totalidad de las calles para calcular y mostrar cuál es la calle
        // más cara (en cuanto a su precio de compra), la más barata y el precio medio de las calles.
        float precioMAX = 0;
        for(int i = 0; i<5; i++){
            float precioActual = tablero1.getCasilla(i).getPrecioCompra();
            if (precioMAX<precioActual)
                precioMAX = precioActual;
        }   
            
        System.out.println("El precio maximo entre las casillas del tablero es: " +precioMAX +"\n");
        
        // Los otros bucles son mas de lo mismo asi que no los voy a hacer
        
        
        
        
        /////// PRUEBAS DIARIO ////////
        System.out.println("\t\t\t!!!!!!!==== PRUEBAS CLASE DIARIO ====!!!!!!!");
        System.out.println("Hay eventos pendientes en el diario? " +Diario.getInstance().eventosPendientes());
        
        Diario.getInstance().ocurreEvento("Pepe acaba de recibir un millon de euros del banco");
        
        System.out.println("Hay eventos pendientes en el diario? " +Diario.getInstance().eventosPendientes());

        System.out.println("Que evento acaba de ocurrir? " +Diario.getInstance().leerEvento());
        
        System.out.println(Diario.getInstance().leerEvento());
        
        
        
        // 8 - Finalmente, realiza distintas tiradas con el dado y asegúrate de que se calcula correctamente
        // la posición de destino en el tablero
        int tirada = Dado.getInstance().tirar();
        int posicionInicial = 19;        
        int dondeEstoy = tablero1.nuevaPosicion(posicionInicial, tirada);
        
        System.out.println("Prueba de calculo de nueva posicion en el tablero.");
        System.out.println("Posicion inicial: " +posicionInicial + "\nNumero del dado: " +tirada +"\nPosicion actual: "+dondeEstoy);
        
        
        
    }
    
}
