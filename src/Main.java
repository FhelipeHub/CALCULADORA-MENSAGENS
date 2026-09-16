import java.util.Locale;

class Mensagem {
    protected String campanha;
    protected int quantidade;

    public Mensagem(String campanha, int quantidade) {
        this.campanha = campanha;
        this.quantidade = quantidade;
    }

    // COM RETORNO: na classe mae, apenas retorna 0.0
    public double calcularCusto() {
        return 0.0;
    }

    // COM RETORNO: retorna o texto "Mensagem"
    public String getCanal() {
        return "Mensagem";
    }

    // SEM RETORNO (void): imprime o canal, o nome da campanha, a quantidade e o custo
    public void exibirResumo() {
        System.out.printf("%s %s %d envios R$ %.2f%n", getCanal(), campanha, quantidade, calcularCusto());
    }
}

class Email extends Mensagem {
    public Email(String campanha, int quantidade) {
        super(campanha, quantidade);
    }

    @Override
    public double calcularCusto() {
        double custo = quantidade * 0.02; // R$ 0,02 por envio
        if (quantidade > 1000) {
            custo = custo - (custo * 0.10); // Aplica 10% de desconto
        }
        return custo;
    }

    @Override
    public String getCanal() {
        return "E-mail";
    }
}

class SMS extends Mensagem {
    public SMS(String campanha, int quantidade) {
        super(campanha, quantidade);
    }

    @Override
    public double calcularCusto() {
        return quantidade * 0.15; // R$ 0,15 por envio (sem desconto)
    }

    @Override
    public String getCanal() {
        return "SMS";
    }
}

public class Main {
    public static void main(String[] args) {
        // Configura o Locale para garantir que o separador decimal seja o ponto (R$ 45.00)
        Locale.setDefault(Locale.US);

        // 1. Cria um vetor do tipo Mensagem com os 3 envios da seção 4
        Mensagem[] envios = new Mensagem[3];
        envios[0] = new Email("boas-vindas", 2500);
        envios[1] = new Email("promocao-agosto", 800);
        envios[2] = new SMS("codigo-verificacao", 300);

        double total = 0.0;

        // 2. Percorre o vetor chamando exibirResumo() de cada um
        for (int i = 0; i < envios.length; i++) {
            envios[i].exibirResumo();

            // 3. Soma os custos em uma variavel total (Polimorfismo em ação)
            total += envios[i].calcularCusto();
        }

        // 4. Mostra o total
        System.out.printf("TOTAL DA FATURA R$ %.2f%n", total);

        // 5. Usa if/else para verificar o limite de R$ 100,00
        if (total > 100.00) {
            System.out.println("Atencao: fatura acima do limite contratado.");
        } else {
            System.out.println("Fatura dentro do limite.");
        }
    }
}
