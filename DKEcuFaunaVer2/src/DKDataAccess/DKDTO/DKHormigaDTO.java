package DKDataAccess.DKDTO;

public class DKHormigaDTO {
    private int idHormiga;
    private int idSexo;
    private int idGenoAlimento;
    private int idIngestaNativa;
    private int idProvincia;
    private String tipoHormiga;
    private String nombre;
    private String estado;
    private String fechaCreacion;
    private int porcentajeEvolucion;

    // Getters y setters para todos los campos
    public int getIdHormiga() {
        return idHormiga;
    }

    public void setIdHormiga(int idHormiga) {
        this.idHormiga = idHormiga;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public int getIdGenoAlimento() {
        return idGenoAlimento;
    }

    public void setIdGenoAlimento(int idGenoAlimento) {
        this.idGenoAlimento = idGenoAlimento;
    }

    public int getIdIngestaNativa() {
        return idIngestaNativa;
    }

    public void setIdIngestaNativa(int idIngestaNativa) {
        this.idIngestaNativa = idIngestaNativa;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getTipoHormiga() {
        return tipoHormiga;
    }

    public void setTipoHormiga(String tipoHormiga) {
        this.tipoHormiga = tipoHormiga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getPorcentajeEvolucion() {
        return porcentajeEvolucion;
    }

    public void setPorcentajeEvolucion(int porcentajeEvolucion) {
        this.porcentajeEvolucion = porcentajeEvolucion;
    }
}
