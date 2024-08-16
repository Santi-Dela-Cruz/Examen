package DKDataAccess.DKDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DKDataAccess.DKIDAO;
import DKDataAccess.DKDTO.DKRegionDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

public class DKRegionDAO implements DKIDAO<DKRegionDTO> {
    private Connection dkConnection;

    public DKRegionDAO() {
        this.dkConnection = DKDataHelper.dkConection();
    }

    private void ensureConnectionOpen() throws SQLException {
        if (dkConnection == null || dkConnection.isClosed()) {
            dkConnection = DKDataHelper.dkConection();
        }
    }

    @Override
    public boolean dkCreate(DKRegionDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, idRegion, nombre, descripcion) VALUES (2, ?, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdCatalogoGeo());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Región", e);
        }
    }

    @Override
    public List<DKRegionDTO> dkReadAll() throws Exception {
        ensureConnectionOpen();
        List<DKRegionDTO> regiones = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 2";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKRegionDTO region = new DKRegionDTO();
                region.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                region.setNombre(rs.getString("nombre"));
                region.setDescripcion(rs.getString("descripcion"));
                region.setEstado(rs.getString("estado"));
                region.setFechaCreacion(rs.getString("FechaCreacion"));
                regiones.add(region);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Regiones", e);
        }
        return regiones;
    }

    @Override
    public boolean dkUpdate(DKRegionDTO entity) throws Exception {
        ensureConnectionOpen();
        String sql = "UPDATE CatalogoGeografia SET nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Región", e);
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
            throw new Exception("Error al eliminar Región", e);
        }
    }

    @Override
    public DKRegionDTO dkReadBy(Integer id) throws Exception {
        ensureConnectionOpen();
        DKRegionDTO region = null;
        String sql = "SELECT idCatalogoGeo, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    region = new DKRegionDTO();
                    region.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    region.setNombre(rs.getString("nombre"));
                    region.setDescripcion(rs.getString("descripcion"));
                    region.setEstado(rs.getString("estado"));
                    region.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Región por ID", e);
        }
        return region;
    }
}
