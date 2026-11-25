package suporte;

import java.util.ArrayList;
import java.util.List;

public class Chamado {

    private int numero;
    private String titulo;
    private ChamadoEstado estado;
    private List<ChamadoEstado> memento = new ArrayList<ChamadoEstado>();

    public Chamado(int numero, String titulo) {
        this.numero = numero;
        this.titulo = titulo;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public ChamadoEstado getEstado() {
        return this.estado;
    }

    public void setEstado(ChamadoEstado estado) {
        this.estado = estado;
        this.memento.add(this.estado);
    }

    public void restauraEstado(int indice) {
        if (indice < 0 || indice > this.memento.size() - 1) {
            throw new IllegalArgumentException("Indice invalido");
        }
        this.estado = this.memento.get(indice);
    }

    public List<ChamadoEstado> getEstados() {
        return this.memento;
    }
}
