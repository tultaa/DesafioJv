import java.util.*;

public class desafio3 {

    enum TipoVeiculo {
        CARRO, MOTO, CAMINHAO
    }

    static class Veiculo {
        String placa;
        TipoVeiculo tipo;
        int eixos; // só usado se for caminhão

        Veiculo(String placa, TipoVeiculo tipo, int eixos) {
            this.placa = placa;
            this.tipo = tipo;
            this.eixos = eixos;
        }

        @Override
        public String toString() {
            return tipo + " - Placa: " + placa + (tipo == TipoVeiculo.CAMINHAO ? " - Eixos: " + eixos : "");
        }
    }

    static class PracaPedagio {
        String localizacao;
        double tarifaBase;
        List<RegistroPassagem> registros = new ArrayList<>();

        PracaPedagio(String localizacao, double tarifaBase) {
            this.localizacao = localizacao;
            this.tarifaBase = tarifaBase;
        }

        void registrarPassagem(Veiculo v) {
            double valor = calcularValor(v);
            registros.add(new RegistroPassagem(v, valor));
        }

        double calcularValor(Veiculo v) {
            switch (v.tipo) {
                case CARRO:
                    return tarifaBase;
                case MOTO:
                    return tarifaBase * 0.5;
                case CAMINHAO:
                    return tarifaBase * v.eixos;
                default:
                    return 0;
            }
        }

        double valorTotalArrecadado() {
            double total = 0;
            for (RegistroPassagem r : registros) {
                total += r.valor;
            }
            return total;
        }

        void gerarRelatorio() {
            System.out.println("\nPraça de Pedágio - " + localizacao);
            for (RegistroPassagem r : registros) {
                System.out.println("- " + r);
            }
            System.out.printf("Total arrecadado: R$ %.2f\n", valorTotalArrecadado());
        }
    }

    static class RegistroPassagem {
        Veiculo veiculo;
        double valor;

        RegistroPassagem(Veiculo veiculo, double valor) {
            this.veiculo = veiculo;
            this.valor = valor;
        }

        @Override
        public String toString() {
            return veiculo + " | Valor: R$ " + String.format("%.2f", valor);
        }
    }

    public static void main(String[] args) {
        PracaPedagio spMarginal = new PracaPedagio("Marginal Tietê", 10.0);
        PracaPedagio spCampinas = new PracaPedagio("Rodovia Anhanguera - Campinas", 12.0);

        Veiculo carro1 = new Veiculo("ABC1234", TipoVeiculo.CARRO, 0);
        Veiculo moto1 = new Veiculo("XYZ5678", TipoVeiculo.MOTO, 0);
        Veiculo caminhao1 = new Veiculo("TRK9999", TipoVeiculo.CAMINHAO, 4);

        spMarginal.registrarPassagem(carro1);     // 10.0
        spMarginal.registrarPassagem(moto1);      // 5.0
        spMarginal.registrarPassagem(caminhao1);  // 40.0

        spCampinas.registrarPassagem(caminhao1);  // 48.0
        spCampinas.registrarPassagem(carro1);     // 12.0

        // Relatórios por praça
        spMarginal.gerarRelatorio();
        spCampinas.gerarRelatorio();
    }
}
