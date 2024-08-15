package DKDataAccess.DKDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DKDataAccess.DKIDAO;
import DKDataAccess.DKDTO.DKSexoDTO;
import DKDataAccess.DKDataHelper.DKDataHelper;

public class DKSexoDAO implements DKIDAO<DKSexoDTO> {
    private Connection dkConnection;

    public DKSexoDAO() {
        this.dkConnection = DKDataHelper.dkConection();
    }

    @Override
    public boolean dkCreate(DKSexoDTO entity) throws Exception {
        String sql = "INSERT INTO CatalogoAlimento (idCatalogoTipoAl, nombre, descripcion) VALUES (3, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Sexo", e);
        }
    }

    @Override
    public List<DKSexoDTO> dkReadAll() throws Exception {
        List<DKSexoDTO> sexos = new ArrayList<>();
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE estado = 'A' AND idCatalogoTipoAl = 3";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKSexoDTO sexo = new DKSexoDTO();
                sexo.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                sexo.setNombre(rs.getString("nombre"));
                sexo.setDescripcion(rs.getString("descripcion"));
                sexo.setEstado(rs.getString("estado"));
                sexo.setFechaCreacion(rs.getString("FechaCreacion"));
                sexos.add(sexo);
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Sexos", e);
        }
        return sexos;
    }

    @Override
    public boolean dkUpdate(DKSexoDTO entity) throws Exception {
        String sql = "UPDATE CatalogoAlimento SET nombre = ?, descripcion = ? WHERE idCatalogoAl = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getNombre());
            preparedStatement.setString(2, entity.getDescripcion());
            preparedStatement.setInt(3, entity.getIdCatalogoAl());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Sexo", e);
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
            throw new Exception("Error al eliminar Sexo", e);
        }
    }

    @Override
    public DKSexoDTO dkReadBy(Integer id) throws Exception {
        DKSexoDTO sexo = null;
        String sql = "SELECT idCatalogoAl, nombre, descripcion, estado, FechaCreacion FROM CatalogoAlimento WHERE idCatalogoAl = ? AND estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    sexo = new DKSexoDTO();
                    sexo.setIdCatalogoAl(rs.getInt("idCatalogoAl"));
                    sexo.setNombre(rs.getString("nombre"));
                    sexo.setDescripcion(rs.getString("descripcion"));
                    sexo.setEstado(rs.getString("estado"));
                    sexo.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Sexo por ID", e);
        }
        return sexo;
    }
}
