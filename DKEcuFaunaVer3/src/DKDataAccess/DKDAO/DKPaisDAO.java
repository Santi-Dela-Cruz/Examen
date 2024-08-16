package DKDataAccess.DKDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DKDataAccess.DKIDAO;
import DKDataAccess.DKDTO.DKPaisDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

public class DKPaisDAO implements DKIDAO<DKPaisDTO> {
    private Connection dkConnection;

    public DKPaisDAO() {
        this.dkConnection = DKDataHelper.dkConection();
    }

    private void ensureConnectionOpen() throws SQLException {
        if (dkConnection == null || dkConnection.isClosed()) {
            dkConnection = DKDataHelper.dkConection();
        }
    }

    @Override
    public boolean dkCreate(DKPaisDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, nombre, descripcion) VALUES (1, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear País", e);
        }
    }

    @Override
    public List<DKPaisDTO> dkReadAll() throws Exception {
        ensureConnectionOpen();
        List<DKPaisDTO> paises = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 1";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKPaisDTO pais = new DKPaisDTO();
                pais.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                pais.setNombre(rs.getString("nombre"));
                pais.setDescripcion(rs.getString("descripcion"));
                pais.setEstado(rs.getString("estado"));
                pais.setFechaCreacion(rs.getString("FechaCreacion"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Países", e);
        }
        return paises;
    }

    @Override
    public boolean dkUpdate(DKPaisDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoGeografia SET nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar País", e);
        }
    }

    @Override
    public boolean dkDelete(int id) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoGeografia SET estado = 'X' WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar País", e);
        }
    }

    @Override
    public DKPaisDTO dkReadBy(Integer id) throws Exception {
        ensureConnectionOpen();
        DKPaisDTO pais = null;
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    pais = new DKPaisDTO();
                    pais.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    pais.setNombre(rs.getString("nombre"));
                    pais.setDescripcion(rs.getString("descripcion"));
                    pais.setEstado(rs.getString("estado"));
                    pais.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer País por ID", e);
        }
        return pais;
    }
}
