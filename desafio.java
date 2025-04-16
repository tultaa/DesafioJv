import java.util.*;

public class desafio {

    static class Jogadora {
        String nome;
        int idade;
        String posicao;
        int numeroCamisa;

        Jogadora(String nome, int idade, String posicao, int numeroCamisa) {
            this.nome = nome;
            this.idade = idade;
            this.posicao = posicao;
            this.numeroCamisa = numeroCamisa;
        }
    }

    static class Time {
        String nome;
        String cidade;
        List<Jogadora> jogadoras = new ArrayList<>();
        int pontos = 0;

        Time(String nome, String cidade) {
            this.nome = nome;
            this.cidade = cidade;
        }

        void adicionarJogadora(Jogadora j) {
            jogadoras.add(j);
        }

        void adicionarPontos(int p) {
            pontos += p;
        }
    }

    static class Jogo {
        Time timeA;
        Time timeB;
        int golsA;
        int golsB;

        Jogo(Time a, Time b, int ga, int gb) {
            this.timeA = a;
            this.timeB = b;
            this.golsA = ga;
            this.golsB = gb;
            calcularResultado();
        }

        void calcularResultado() {
            if (golsA > golsB) {
                timeA.adicionarPontos(3);
            } else if (golsB > golsA) {
                timeB.adicionarPontos(3);
            } else {
                timeA.adicionarPontos(1);
                timeB.adicionarPontos(1);
            }
        }
    }

    static class Campeonato {
        List<Time> times = new ArrayList<>();
        List<Jogo> jogos = new ArrayList<>();

        void cadastrarTime(Time t) {
            times.add(t);
        }

        void registrarJogo(Jogo j) {
            jogos.add(j);
        }

        List<Time> getClassificacao() {
            times.sort((a, b) -> b.pontos - a.pontos);
            return times;
        }
    }

    public static void main(String[] args) {
        Campeonato campeonato = new Campeonato();

        Time arsenal = new Time("Arsenal Women", "Londres");
        Time realMadrid = new Time("Real Madrid Femenino", "Madri");
        Time lyon = new Time("Olympique Lyonnais Féminin", "Lyon");

        arsenal.adicionarJogadora(new Jogadora("Beth Mead", 28, "Atacante", 9));
        realMadrid.adicionarJogadora(new Jogadora("Olga Carmona", 23, "Lateral", 7));
        lyon.adicionarJogadora(new Jogadora("Ada Hegerberg", 28, "Atacante", 14));

        campeonato.cadastrarTime(arsenal);
        campeonato.cadastrarTime(realMadrid);
        campeonato.cadastrarTime(lyon);

        campeonato.registrarJogo(new Jogo(arsenal, realMadrid, 3, 2));
        campeonato.registrarJogo(new Jogo(realMadrid, lyon, 1, 2));
        campeonato.registrarJogo(new Jogo(lyon, arsenal, 0, 0));

        System.out.println("🏆 Classificaçao do Campeonato:");
        for (Time t : campeonato.getClassificacao()) {
            System.out.println(t.nome + " - " + t.pontos + " pontos");
        }
    }
}
