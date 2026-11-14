import java.util.Map;
import java.util.HashMap;

public class MemoryLeakDemo {

    // El clásico: una colección estática que crece sin control.
    private static final Map<String, String> cacheSinLimite = new HashMap<>();

    void main() throws Exception {
        long contador = 0;
        while (true) {
            // Simula atender peticiones: cada "usuario" genera una entrada
            contador++;
            String usuario = "usuario-" + contador;
            cacheSinLimite.put(usuario, "sesion-activa");

            // Ruido: objetos temporales que el GC sí puede recolectar
            String temporal = "dato-provisional-" + contador;

            if (contador % 1_000_000 == 0) {
                System.out.printf("Atendidas %,d peticiones | cache=%,d entradas | heap=%s%n",
                        contador, cacheSinLimite.size(),
                        Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB aprox");
            }
        }
    }
}