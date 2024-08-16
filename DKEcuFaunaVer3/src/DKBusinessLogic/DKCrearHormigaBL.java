package DKBusinessLogic;

import DKDataAccess.DKDAO.DKHormigaDAO;
import DKDataAccess.DKDTO.DKHormigaDTO;
import java.util.List;

public class DKCrearHormigaBL {

    private DKHormigaDAO hormigaDAO;
    private DKGetHormigaBL getHormigaBL;

    public DKCrearHormigaBL() {
        this.hormigaDAO = new DKHormigaDAO();
        this.getHormigaBL = new DKGetHormigaBL();
    }

    public boolean crearNuevaHormiga(String nombre, String sexo, String genoAlimento, String ingestaNativa,
            String provincia, String tipoHormiga) {
        try {
            // Convertir los nombres seleccionados en IDs correspondientes
            int idSexo = getHormigaBL.obtenerIdSexo(sexo);
            int idGenoAlimento = getHormigaBL.obtenerIdGenoAlimento(genoAlimento);
            int idIngestaNativa = getHormigaBL.obtenerIdIngestaNativa(ingestaNativa);
            int idProvincia = getHormigaBL.obtenerIdProvincia(provincia);

            // Crear el objeto Hormiga DTO
            DKHormigaDTO nuevaHormiga = new DKHormigaDTO();
            nuevaHormiga.setNombre(nombre);
            nuevaHormiga.setIdSexo(idSexo);
            nuevaHormiga.setIdGenoAlimento(idGenoAlimento);
            nuevaHormiga.setIdIngestaNativa(idIngestaNativa);
            nuevaHormiga.setIdProvincia(idProvincia);
            nuevaHormiga.setTipoHormiga(tipoHormiga);
            nuevaHormiga.setPorcentajeEvolucion(0); // Inicia en 0%
            nuevaHormiga.setEstado("A"); // Asignar un valor por defecto para 'estado'

            // Guardar en la base de datos
            return hormigaDAO.dkCreate(nuevaHormiga);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Métodos para obtener nombres de las diferentes entidades...
    public List<String> obtenerNombresSexo() {
        return getHormigaBL.obtenerNombresSexo();
    }

    public List<String> obtenerNombresProvincia() {
        return getHormigaBL.obtenerNombresProvincia();
    }

    public List<String> obtenerNombresGenoAlimento() {
        return getHormigaBL.obtenerNombresGenoAlimento();
    }

    public List<String> obtenerNombresIngestaNativa() {
        return getHormigaBL.obtenerNombresIngestaNativa();
    }

}
