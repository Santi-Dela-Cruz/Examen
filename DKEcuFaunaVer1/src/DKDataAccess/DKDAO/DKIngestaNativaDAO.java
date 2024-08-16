package DKDataAccess.DKDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DKDataAccess.DKIDAO;
import DKDataAccess.DKDTO.DKIngestaNativaDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

public class DKIngestaNativaDAO implements DKIDAO<DKIngestaNativaDTO> {
    private Connection dkConnection;

    public DKIngestaNativaDAO() {
        this.dkConnection = DKDataHelper.dkConection();
    }

    @Override
    public boolean dkCreate(DKIngestaNativaDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoAlimento (idCatalogoTipoAl, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear IngestaNativa", e);
        }
    }

    @Override
    public List<DKIngestaNativaDTO> dkReadAll() throws Exception {
        List<DKIngestaNativaDTO> ingestas = new ArrayList<>();
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE estado = 'A' AND idCatalogoTipoAl = 1";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKIngestaNativaDTO ingesta = new DKIngestaNativaDTO();
                ingesta.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                ingesta.setNombre(rs.getString("nombre"));
                ingesta.setDescripcion(rs.getString("descripcion"));
                ingesta.setEstado(rs.getString("estado"));
                ingesta.setFechaCreacion(rs.getString("FechaCreacion"));
                ingestas.add(ingesta);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer IngestaNativa", e);
        }
        return ingestas;
    }

    @Override
    public boolean dkUpdate(DKIngestaNativaDTO entity) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET nombre = ?, descripcion = ? WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoAl());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar IngestaNativa", e);
        }
    }

    @Override
    public boolean dkDelete(int id) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET estado = 'X' WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar IngestaNativa", e);
        }
    }

    @Override
    public DKIngestaNativaDTO dkReadBy(Integer id) throws Exception {
        DKIngestaNativaDTO ingesta = null;
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE idCatalogoAl = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    ingesta = new DKIngestaNativaDTO();
                    ingesta.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                    ingesta.setNombre(rs.getString("nombre"));
                    ingesta.setDescripcion(rs.getString("descripcion"));
                    ingesta.setEstado(rs.getString("estado"));
                    ingesta.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer IngestaNativa por ID", e);
        }
        return ingesta;
    }
}
