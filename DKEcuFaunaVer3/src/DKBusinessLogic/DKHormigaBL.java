package DKBusinessLogic;

import java.util.List;

import DKDataAccess.DKDAO.DKHormigaDAO;
import DKDataAccess.DKDTO.DKHormigaDTO;

public class DKHormigaBL {
    private DKHormigaDAO hormigaDAO;

    public DKHormigaBL() {
        this.hormigaDAO = new DKHormigaDAO();
    }

    public boolean guardarHormiga(DKHormigaDTO hormiga) throws Exception {
        return hormigaDAO.dkUpdate(hormiga);
    }

    public boolean eliminarHormiga(int idHormiga) throws Exception {
        return hormigaDAO.dkDelete(idHormiga);
    }

    public DKHormigaDTO obtenerHormigaPorId(int idHormiga) throws Exception {
        return hormigaDAO.dkReadBy(idHormiga);
    }

    public List<DKHormigaDTO> obtenerTodasLasHormigas() throws Exception {
        return hormigaDAO.dkReadAll();
    }

    public String determinarEvolucion(String tipoHormigaAnterior, String tipoAlimento) {
        return hormigaDAO.determinarEvolucion(tipoHormigaAnterior, tipoAlimento);
    }
}
