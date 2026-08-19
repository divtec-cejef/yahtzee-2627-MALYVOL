public class YahtzeeProcedural {

    public static void main(String[] args) {
        System.out.println( "Bon courage !");

        const int MIN = 1;
        const int MAX = 6;
        int nombre;

        int tirerAleatoirement(nombre) {
            int resultat = Math.floor(Math.random() * (MAX - MIN + 1)) + MIN;
            return resultat;
        }

        System.out.println(tirerAleatoirement(nombre)

    }
}
