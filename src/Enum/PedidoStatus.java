package Enum;

public enum PedidoStatus {
    PENDENTE("pendente"),
    EM_PREPARO("em_preparo"),
    A_CAMINHO("a_caminho"),
    ENTREGUE("entregue"),
    CANCELADO("cancelado");

    private final String valor;

    PedidoStatus(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static PedidoStatus fromString(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status nulo!");
        }

        for (PedidoStatus ps : PedidoStatus.values()) {
            if (ps.valor.equalsIgnoreCase(status.trim())) {
                return ps;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + status);
    }
}
