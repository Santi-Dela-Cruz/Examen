package DKBusinessLogic;

import java.util.List;

import DKDataAccess.DKDAO.DKGenoAlimentoDAO;
import DKDataAccess.DKDTO.DKGenoAlimentoDTO;

public class DKGenoAlimentoBL {
    private DKGenoAlimentoDAO genoAlimentoDAO;

    public DKGenoAlimentoBL() {
        this.genoAlimentoDAO = new DKGenoAlimentoDAO();
    }

    public int obtenerIdGenoAlimento(String nombreGenoAlimento) {
        try {
            List<DKGenoAlimentoDTO> genoAlimentos = genoAlimentoDAO.dkReadAll();
            for (DKGenoAlimentoDTO genoAlimento : genoAlimentos) {
                if (genoAlimento.getNombre().equals(nombreGenoAlimento)) {
                    return genoAlimento.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> obtenerNombresGenoAlimento() {
        try {
            return genoAlimentoDAO.dkReadAll().stream()
                    .map(DKGenoAlimentoDTO::getNombre)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }
}
