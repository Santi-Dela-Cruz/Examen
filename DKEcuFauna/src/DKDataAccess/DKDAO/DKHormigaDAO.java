package DKDataAccess.DKDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DKDataAccess.DKIDAO;
import DKDataAccess.DKDTO.DKHormigaDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

public class DKHormigaDAO implements DKIDAO<DKHormigaDTO> {
    private Connection dkConnection;

    public DKHormigaDAO() {
        this.dkConnection = DKDataHelper.dkConection();
    }

    @Override
    public boolean dkCreate(DKHormigaDTO entity) throws Exception {
        String sql = "INSERT INTO Hormiga (idSexo, idGenoAlimento, idIngestaNativa, idProvincia, nombre, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdSexo());
            preparedStatement.setInt(2, entity.getIdGenoAlimento());
            preparedStatement.setInt(3, entity.getIdIngestaNativa());
            preparedStatement.setInt(4, entity.getIdProvincia());
            preparedStatement.setString(5, entity.getNombre());
            preparedStatement.setString(6, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Hormiga", e);
        }
    }

    @Override
    public List<DKHormigaDTO> dkReadAll() throws Exception {
        List<DKHormigaDTO> hormigas = new ArrayList<>();
        String sql = "SELECT idHormiga, idSexo, idGenoAlimento, idIngestaNativa, idProvincia, nombre, descripcion, estado, FechaCreacion FROM Hormiga WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKHormigaDTO hormiga = new DKHormigaDTO();
                hormiga.setIdHormiga(rs.getInt("idHormiga"));
                hormiga.setIdSexo(rs.getInt("idSexo"));
                hormiga.setIdGenoAlimento(rs.getInt("idGenoAlimento"));
                hormiga.setIdIngestaNativa(rs.getInt("idIngestaNativa"));
                hormiga.setIdProvincia(rs.getInt("idProvincia"));
                hormiga.setNombre(rs.getString("nombre"));
                hormiga.setDescripcion(rs.getString("descripcion"));
                hormiga.setEstado(rs.getString("estado"));
                hormiga.setFechaCreacion(rs.getString("FechaCreacion"));
                hormigas.add(hormiga);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Hormigas", e);
        }
        return hormigas;
    }

    @Override
    public boolean dkUpdate(DKHormigaDTO entity) throws Exception {
        String sql = "UPDATE Hormiga SET idSexo = ?, idGenoAlimento = ?, idIngestaNativa = ?, idProvincia = ?, nombre = ?, descripcion = ? WHERE idHormiga = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdSexo());
            preparedStatement.setInt(2, entity.getIdGenoAlimento());
            preparedStatement.setInt(3, entity.getIdIngestaNativa());
            preparedStatement.setInt(4, entity.getIdProvincia());
            preparedStatement.setString(5, entity.getNombre());
            preparedStatement.setString(6, entity.getDescripcion());
            preparedStatement.setInt(7, entity.getIdHormiga());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Hormiga", e);
        }
    }

    @Override
    public boolean dkDelete(int id) throws Exception {
        String sql = "UPDATE Hormiga SET estado = 'X' WHERE idHormiga = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar Hormiga", e);
        }
    }

    @Override
    public DKHormigaDTO dkReadBy(Integer id) throws Exception {
        DKHormigaDTO hormiga = null;
        String sql = "SELECT idHormiga, idSexo, idGenoAlimento, idIngestaNativa, idProvincia, nombre, descripcion, estado, FechaCreacion FROM Hormiga WHERE idHormiga = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    hormiga = new DKHormigaDTO();
                    hormiga.setIdHormiga(rs.getInt("idHormiga"));
                    hormiga.setIdSexo(rs.getInt("idSexo"));
                    hormiga.setIdGenoAlimento(rs.getInt("idGenoAlimento"));
                    hormiga.setIdIngestaNativa(rs.getInt("idIngestaNativa"));
                    hormiga.setIdProvincia(rs.getInt("idProvincia"));
                    hormiga.setNombre(rs.getString("nombre"));
                    hormiga.setDescripcion(rs.getString("descripcion"));
                    hormiga.setEstado(rs.getString("estado"));
                    hormiga.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Hormiga por ID", e);
        }
        return hormiga;
    }
}
