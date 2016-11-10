
import umss.sis.importadora.restclients.AsistenciaRestClient;
/**
 *
 * @author LuisMaldonado
 */
public class PruebaCliente {

    public static void main(String[] args) {
        AsistenciaRestClient client = new AsistenciaRestClient();
//        client.findAll_XML();
//        System.out.println(count);
        client.close();
    }
}
