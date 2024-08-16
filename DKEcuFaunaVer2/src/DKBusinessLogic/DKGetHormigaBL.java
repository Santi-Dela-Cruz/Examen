package DKBusinessLogic;

import DKDataAccess.DKDAO.*;
import DKDataAccess.DKDTO.*;

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

    public String obtenerNombreGenoAlimento(int idGenoAlimento) throws Exception {
        DKGenoAlimentoDTO genoAlimento = genoAlimentoDAO.dkReadBy(idGenoAlimento);
        return genoAlimento != null ? genoAlimento.getNombre().toUpperCase() : "N/A";
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
}
