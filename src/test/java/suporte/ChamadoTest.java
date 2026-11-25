package suporte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChamadoTest {

    Chamado chamado;

    @BeforeEach
    void setUp() {
        chamado = new Chamado(4821, "Integrador nao sincroniza");
        chamado.setEstado(ChamadoEstadoAberto.getInstance());
        chamado.setEstado(ChamadoEstadoEmAtendimento.getInstance());
        chamado.setEstado(ChamadoEstadoAguardandoCliente.getInstance());
        chamado.setEstado(ChamadoEstadoResolvido.getInstance());
        chamado.setEstado(ChamadoEstadoFechado.getInstance());
    }

    @Test
    void deveArmazenarOHistoricoDeEstados() {
        assertEquals(5, chamado.getEstados().size());
    }

    @Test
    void deveRetornarONomeDoEstadoAtual() {
        assertEquals("Fechado", chamado.getEstado().getNomeEstado());
    }

    @Test
    void deveRestaurarOEstadoInicial() {
        chamado.restauraEstado(0);

        assertEquals(ChamadoEstadoAberto.getInstance(), chamado.getEstado());
        assertEquals("Aberto", chamado.getEstado().getNomeEstado());
    }

    @Test
    void deveReabrirChamadoFechadoPorEngano() {
        chamado.restauraEstado(1);

        assertEquals(ChamadoEstadoEmAtendimento.getInstance(), chamado.getEstado());
        assertEquals("Em atendimento", chamado.getEstado().getNomeEstado());
    }

    @Test
    void deveManterOHistoricoAposRestaurar() {
        chamado.restauraEstado(1);

        assertEquals(5, chamado.getEstados().size());
        assertEquals("Fechado", chamado.getEstados().get(4).getNomeEstado());
    }

    @Test
    void deveCompartilharAMesmaInstanciaDeEstadoEntreChamados() {
        Chamado outro = new Chamado(4822, "Lentidao no relatorio");
        outro.setEstado(ChamadoEstadoAberto.getInstance());

        chamado.restauraEstado(0);

        assertSame(chamado.getEstado(), outro.getEstado());
        assertEquals(1, outro.getEstados().size());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        try {
            Chamado semHistorico = new Chamado(4823, "Erro ao exportar");
            semHistorico.restauraEstado(0);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Indice invalido", e.getMessage());
        }
    }
}
