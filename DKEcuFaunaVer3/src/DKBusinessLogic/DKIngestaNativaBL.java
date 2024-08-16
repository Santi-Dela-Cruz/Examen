package DKBusinessLogic;

import java.util.List;

import DKDataAccess.DKDAO.DKIngestaNativaDAO;
import DKDataAccess.DKDTO.DKIngestaNativaDTO;

public class DKIngestaNativaBL {
    private DKIngestaNativaDAO ingestaNativaDAO;

    public DKIngestaNativaBL() {
        this.ingestaNativaDAO = new DKIngestaNativaDAO();
    }

    public int obtenerIdIngestaNativa(String nombreIngestaNativa) {
        try {
            List<DKIngestaNativaDTO> ingestaNativas = ingestaNativaDAO.dkReadAll();
            for (DKIngestaNativaDTO ingestaNativa : ingestaNativas) {
                if (ingestaNativa.getNombre().equals(nombreIngestaNativa)) {
                    return ingestaNativa.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> obtenerNombresIngestaNativa() {
        try {
            return ingestaNativaDAO.dkReadAll().stream()
                    .map(DKIngestaNativaDTO::getNombre)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }
}
