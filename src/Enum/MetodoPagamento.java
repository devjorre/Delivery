package Enum;

public enum MetodoPagamento {
    CARTAO("cartão"),
    PIX("pix"),
    DINHEIRO("dinheiro");

    private final String metodo;

    MetodoPagamento(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public static MetodoPagamento fromString(String text) {
        for (MetodoPagamento b : MetodoPagamento.values()) {
            if (b.metodo.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
