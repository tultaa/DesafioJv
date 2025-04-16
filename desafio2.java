import java.util.*;
import java.text.SimpleDateFormat;

public class desafio2 {

    static class Pessoa {
        String nome;
        int idade;
        String tipoDeficiencia;
        String grau;
        String endereco;
        List<Atendimento> atendimentos = new ArrayList<>();

        Pessoa(String nome, int idade, String tipoDeficiencia, String grau, String endereco) {
            this.nome = nome;
            this.idade = idade;
            this.tipoDeficiencia = tipoDeficiencia;
            this.grau = grau;
            this.endereco = endereco;
        }

        void adicionarAtendimento(Atendimento atendimento) {
            atendimentos.add(atendimento);
        }

        void gerarRelatorio() {
            System.out.println("\n Relatório de Atendimentos - " + nome);
            if (atendimentos.isEmpty()) {
                System.out.println("Nenhum atendimento registrado.");
            } else {
                for (Atendimento a : atendimentos) {
                    System.out.println("- " + a);
                }
            }
        }

        @Override
        public String toString() {
            return nome + " | " + idade + " anos | Deficiência: " + tipoDeficiencia + " (" + grau + ")";
        }
    }

    static class Atendimento {
        Date data;
        String tipo;
        String profissional;

        Atendimento(String tipo, String profissional, String dataStr) {
            try {
                this.data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
            } catch (Exception e) {
                this.data = new Date(); // hoje
            }
            this.tipo = tipo;
            this.profissional = profissional;
        }

        @Override
        public String toString() {
            String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);
            return tipo + " com " + profissional + " em " + dataFormatada;
        }
    }

    static class Sistema {
        List<Pessoa> pessoas = new ArrayList<>();

        void cadastrarPessoa(Pessoa p) {
            pessoas.add(p);
        }

        List<Pessoa> filtrar(String tipoDeficiencia, String grau) {
            List<Pessoa> resultado = new ArrayList<>();
            for (Pessoa p : pessoas) {
                if ((tipoDeficiencia == null || p.tipoDeficiencia.equalsIgnoreCase(tipoDeficiencia)) &&
                    (grau == null || p.grau.equalsIgnoreCase(grau))) {
                    resultado.add(p);
                }
            }
            return resultado;
        }

        void gerarRelatorios() {
            for (Pessoa p : pessoas) {
                p.gerarRelatorio();
            }
        }
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        // Cadastrando pessoas
        Pessoa maria = new Pessoa("Maria Silva", 30, "Visual", "Moderado", "Rua das Flores, 123");
        Pessoa joao = new Pessoa("Joao Souza", 25, "Auditiva", "Leve", "Av. Brasil, 456");

        // Adicionando atendimentos
        maria.adicionarAtendimento(new Atendimento("Fisioterapia", "Dra. Paula", "10/04/2024"));
        maria.adicionarAtendimento(new Atendimento("Terapia Ocupacional", "Dr. Lucas", "12/04/2024"));
        joao.adicionarAtendimento(new Atendimento("Psicologia", "Dra. Ana", "15/04/2024"));

        sistema.cadastrarPessoa(maria);
        sistema.cadastrarPessoa(joao);

        // Listar com filtro
        System.out.println("👥 Pessoas com deficiência visual (qualquer grau):");
        for (Pessoa p : sistema.filtrar("Visual", null)) {
            System.out.println("- " + p);
        }

        // Gerar relatório de todos
        sistema.gerarRelatorios();
    }
}
