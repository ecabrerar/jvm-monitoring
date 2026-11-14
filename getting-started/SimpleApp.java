/**
 * Basic Java Application
 */

void main(){
    IO.println("Hola, JConf GT 2026!");


    IO.println("Presiona ENTER para salir...");
    try(Scanner scanner = new Scanner(System.in)) {
        scanner.nextLine(); //Bloquea hasta pulsar enter
    }
}