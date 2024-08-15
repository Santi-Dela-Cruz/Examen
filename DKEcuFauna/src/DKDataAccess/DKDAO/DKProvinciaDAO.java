package DKDataAccess.DKDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DKDataAccess.DKIDAO;
import DKDataAccess.DKDTO.DKProvinciaDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

public class DKProvinciaDAO implements DKIDAO<DKProvinciaDTO> {
    private Connection dkConnection;

    public DKProvinciaDAO() {
        this.dkConnection = DKDataHelper.dkConection();
    }

    @Override
    public boolean dkCreate(DKProvinciaDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoGeografia (idCatalogoTipoGeo, idRegion, nombre, descripcion) VALUES (3, ?, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdRegion());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Provincia", e);
        }
    }

    @Override
    public List<DKProvinciaDTO> dkReadAll() throws Exception {
        List<DKProvinciaDTO> provincias = new ArrayList<>();
        String sql = "SELECT idCatalogoGeo, idRegion, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE estado = 'A' AND idCatalogoTipoGeo = 3";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKProvinciaDTO provincia = new DKProvinciaDTO();
                provincia.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                provincia.setIdRegion(rs.getInt("idRegion"));
                provincia.setNombre(rs.getString("nombre"));
                provincia.setDescripcion(rs.getString("descripcion"));
                provincia.setEstado(rs.getString("estado"));
                provincia.setFechaCreacion(rs.getString("FechaCreacion"));
                provincias.add(provincia);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Provincias", e);
        }
        return provincias;
    }

    @Override
    public boolean dkUpdate(DKProvinciaDTO entity) throws Exception {
        String sql = "UPDATE CatalogoGeografia SET idRegion = ?, nombre = ?, descripcion = ? WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdRegion());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());
            preparedStatement.setInt(4, entity.getIdCatalogoGeo());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Provincia", e);
        }
    }

    @Override
    public boolean dkDelete(int id) throws Exception {
        String sql = "UPDATE CatalogoGeografia SET estado = 'X' WHERE idCatalogoGeo = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al eliminar Provincia", e);
        }
    }

    @Override
    public DKProvinciaDTO dkReadBy(Integer id) throws Exception {
        DKProvinciaDTO provincia = null;
        String sql = "SELECT idCatalogoGeo, idRegion, nombre, descripcion, estado, FechaCreacion FROM CatalogoGeografia WHERE idCatalogoGeo = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    provincia = new DKProvinciaDTO();
                    provincia.setIdCatalogoGeo(rs.getInt("idCatalogoGeo"));
                    provincia.setIdRegion(rs.getInt("idRegion"));
                    provincia.setNombre(rs.getString("nombre"));
                    provincia.setDescripcion(rs.getString("descripcion"));
                    provincia.setEstado(rs.getString("estado"));
                    provincia.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Provincia por ID", e);
        }
        return provincia;
    }
}
