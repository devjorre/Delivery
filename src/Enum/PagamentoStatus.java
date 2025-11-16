package Enum;

public enum PagamentoStatus {
    PENDENTE("pendente"),
    PAGO("pago"),
    REEMBOLSADO("reembolsado");

    private final String status;

    PagamentoStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static PagamentoStatus fromString(String text) {
        for (PagamentoStatus b : PagamentoStatus.values()) {
            if (b.status.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
