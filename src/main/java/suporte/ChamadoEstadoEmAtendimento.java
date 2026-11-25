package suporte;

public class ChamadoEstadoEmAtendimento implements ChamadoEstado {

    private ChamadoEstadoEmAtendimento() {};
    private static ChamadoEstadoEmAtendimento instance = new ChamadoEstadoEmAtendimento();
    public static ChamadoEstadoEmAtendimento getInstance() {
        return instance;
    }

    public String getNomeEstado() {
        return "Em atendimento";
    }

}
