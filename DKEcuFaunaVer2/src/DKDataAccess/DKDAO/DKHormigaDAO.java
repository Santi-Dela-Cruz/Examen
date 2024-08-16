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

    private void ensureConnectionOpen() throws SQLException {
        if (dkConnection == null || dkConnection.isClosed()) {
            dkConnection = DKDataHelper.dkConection();
        }
    }

    @Override
    public boolean dkCreate(DKHormigaDTO entity) throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        String sql = "INSERT INTO Hormiga (idSexo, idGenoAlimento, idIngestaNativa, idProvincia, tipoHormiga, nombre, estado, porcentajeEvolucion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdSexo());
            preparedStatement.setInt(2, entity.getIdGenoAlimento());
            preparedStatement.setInt(3, entity.getIdIngestaNativa());
            preparedStatement.setInt(4, entity.getIdProvincia());
            preparedStatement.setString(5, entity.getTipoHormiga());
            preparedStatement.setString(6, entity.getNombre());
            preparedStatement.setString(7, entity.getEstado());
            preparedStatement.setInt(8, entity.getPorcentajeEvolucion());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al crear Hormiga", e);
        }
    }

    @Override
    public List<DKHormigaDTO> dkReadAll() throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        List<DKHormigaDTO> hormigas = new ArrayList<>();
        String sql = "SELECT idHormiga, idSexo, idGenoAlimento, idIngestaNativa, idProvincia, tipoHormiga, nombre, estado, porcentajeEvolucion, FechaCreacion FROM Hormiga WHERE estado = 'A'";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                DKHormigaDTO hormiga = new DKHormigaDTO();
                hormiga.setIdHormiga(rs.getInt("idHormiga"));
                hormiga.setIdSexo(rs.getInt("idSexo"));
                hormiga.setIdGenoAlimento(rs.getInt("idGenoAlimento"));
                hormiga.setIdIngestaNativa(rs.getInt("idIngestaNativa"));
                hormiga.setIdProvincia(rs.getInt("idProvincia"));
                hormiga.setTipoHormiga(rs.getString("tipoHormiga"));
                hormiga.setNombre(rs.getString("nombre"));
                hormiga.setEstado(rs.getString("estado"));
                hormiga.setPorcentajeEvolucion(rs.getInt("porcentajeEvolucion"));
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
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        String sql = "UPDATE Hormiga SET idSexo = ?, idGenoAlimento = ?, idIngestaNativa = ?, idProvincia = ?, tipoHormiga = ?, nombre = ?, estado = ?, porcentajeEvolucion = ? WHERE idHormiga = ?";
        try (PreparedStatement preparedStatement = dkConnection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getIdSexo());
            preparedStatement.setInt(2, entity.getIdGenoAlimento());
            preparedStatement.setInt(3, entity.getIdIngestaNativa());
            preparedStatement.setInt(4, entity.getIdProvincia());
            preparedStatement.setString(5, entity.getTipoHormiga());
            preparedStatement.setString(6, entity.getNombre());
            preparedStatement.setString(7, entity.getEstado());
            preparedStatement.setInt(8, entity.getPorcentajeEvolucion());
            preparedStatement.setInt(9, entity.getIdHormiga());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new Exception("Error al actualizar Hormiga", e);
        }
    }

    @Override
    public boolean dkDelete(int id) throws Exception {
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
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
        ensureConnectionOpen(); // Asegurarse de que la conexión esté abierta
        DKHormigaDTO hormiga = null;
        String sql = "SELECT idHormiga, idSexo, idGenoAlimento, idIngestaNativa, idProvincia, tipoHormiga, nombre, estado, porcentajeEvolucion, FechaCreacion FROM Hormiga WHERE idHormiga = ? AND estado = 'A'";
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
                    hormiga.setTipoHormiga(rs.getString("tipoHormiga"));
                    hormiga.setNombre(rs.getString("nombre"));
                    hormiga.setEstado(rs.getString("estado"));
                    hormiga.setPorcentajeEvolucion(rs.getInt("porcentajeEvolucion"));
                    hormiga.setFechaCreacion(rs.getString("FechaCreacion"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Error al leer Hormiga por ID", e);
        }
        return hormiga;
    }

    public String determinarEvolucion(String tipoHormigaAnterior, String tipoAlimento) {
        String nuevaEvolucion = null;

        // Determinar la evolución esperada según el tipo de hormiga anterior y el tipo
        // de alimento
        String evolucionEsperada = determinarEvolucionEsperada(tipoHormigaAnterior, tipoAlimento);

        // Consulta SQL para obtener el nombre de la evolución desde CatalogoEvoluciones
        String query = "SELECT nombreEvolucion FROM CatalogoEvoluciones WHERE nombreEvolucion = ?";

        try (Connection conn = DKDataHelper.dkConection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, evolucionEsperada);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nuevaEvolucion = rs.getString("nombreEvolucion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si se encontró una nueva evolución, la devolvemos; de lo contrario,
        // devolvemos el tipo de hormiga anterior
        return nuevaEvolucion != null ? nuevaEvolucion : tipoHormigaAnterior;
    }

    private String determinarEvolucionEsperada(String tipoHormigaAnterior, String tipoAlimento) {
        switch (tipoHormigaAnterior) {
            case "Larva":
                return "Obrera";
            case "Obrera":
                return "Reina";
            case "Reina":
                return "Soldado";
            case "Soldado":
                return "Guerrero";
            // Agregar más casos según sea necesario
            default:
                return "Maximo alcanzado"; // Si no hay evolución, se devuelve el tipo actual
        }
    }

}
