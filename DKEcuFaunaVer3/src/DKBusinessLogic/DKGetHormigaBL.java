package DKBusinessLogic;

import DKDataAccess.DKDAO.*;
import DKDataAccess.DKDTO.*;

import java.util.List;
import java.util.stream.Collectors;

public class DKGetHormigaBL {
    private DKGenoAlimentoDAO genoAlimentoDAO;
    private DKIngestaNativaDAO ingestaNativaDAO;
    private DKSexoDAO sexoDAO;
    private DKProvinciaDAO provinciaDAO;

    public DKGetHormigaBL() {
        this.genoAlimentoDAO = new DKGenoAlimentoDAO();
        this.ingestaNativaDAO = new DKIngestaNativaDAO();
        this.sexoDAO = new DKSexoDAO();
        this.provinciaDAO = new DKProvinciaDAO();
    }

    // Métodos existentes
    public String obtenerNombreGenoAlimento(int idGenoAlimento) {
        try {
            DKGenoAlimentoDTO genoAlimento = genoAlimentoDAO.dkReadBy(idGenoAlimento);
            return genoAlimento != null ? genoAlimento.getNombre().toUpperCase() : "Desconocido";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener nombre";
        }
    }

    public String obtenerNombreIngestaNativa(int idIngestaNativa) throws Exception {
        DKIngestaNativaDTO ingestaNativa = ingestaNativaDAO.dkReadBy(idIngestaNativa);
        return ingestaNativa != null ? ingestaNativa.getNombre().toUpperCase() : "N/A";
    }

    public String obtenerNombreSexo(int idSexo) throws Exception {
        DKSexoDTO sexo = sexoDAO.dkReadBy(idSexo);
        return sexo != null ? sexo.getNombre().toUpperCase() : "N/A";
    }

    public String obtenerNombreProvincia(int idProvincia) throws Exception {
        DKProvinciaDTO provincia = provinciaDAO.dkReadBy(idProvincia);
        return provincia != null ? provincia.getNombre().toUpperCase() : "N/A";
    }

    // Nuevos métodos para obtener listas de nombres
    public List<String> obtenerNombresSexo() {
        try {
            return sexoDAO.dkReadAll().stream()
                    .map(DKSexoDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    public List<String> obtenerNombresProvincia() {
        try {
            return provinciaDAO.dkReadAll().stream()
                    .map(DKProvinciaDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    public List<String> obtenerNombresGenoAlimento() {
        try {
            return genoAlimentoDAO.dkReadAll().stream()
                    .map(DKGenoAlimentoDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    public List<String> obtenerNombresIngestaNativa() {
        try {
            return ingestaNativaDAO.dkReadAll().stream()
                    .map(DKIngestaNativaDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    // Métodos para obtener IDs por nombre
    public int obtenerIdSexo(String nombreSexo) {
        try {
            List<DKSexoDTO> sexos = sexoDAO.dkReadAll();
            for (DKSexoDTO sexo : sexos) {
                if (sexo.getNombre().equalsIgnoreCase(nombreSexo)) {
                    return sexo.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int obtenerIdProvincia(String nombreProvincia) {
        try {
            List<DKProvinciaDTO> provincias = provinciaDAO.dkReadAll();
            for (DKProvinciaDTO provincia : provincias) {
                if (provincia.getNombre().equalsIgnoreCase(nombreProvincia)) {
                    return provincia.getIdCatalogoGeo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int obtenerIdGenoAlimento(String nombreGenoAlimento) {
        try {
            List<DKGenoAlimentoDTO> genoAlimentos = genoAlimentoDAO.dkReadAll();
            for (DKGenoAlimentoDTO genoAlimento : genoAlimentos) {
                if (genoAlimento.getNombre().equalsIgnoreCase(nombreGenoAlimento)) {
                    return genoAlimento.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int obtenerIdIngestaNativa(String nombreIngestaNativa) {
        try {
            List<DKIngestaNativaDTO> ingestaNativas = ingestaNativaDAO.dkReadAll();
            for (DKIngestaNativaDTO ingestaNativa : ingestaNativas) {
                if (ingestaNativa.getNombre().equalsIgnoreCase(nombreIngestaNativa)) {
                    return ingestaNativa.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
