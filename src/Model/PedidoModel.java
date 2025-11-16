package Model;

import java.time.LocalDateTime;
import Enum.PedidoStatus;

public class PedidoModel {
    private int id_pedido;

    private int id_cliente;

    private int id_restaurante;

    private LocalDateTime data_hora;

    private PedidoStatus status;


    public PedidoModel(int id_pedido, int id_cliente, int id_restaurante, LocalDateTime data_hora, PedidoStatus status) {
        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.id_restaurante = id_restaurante;
        this.data_hora = data_hora;
        this.status = status;
    }

    public PedidoModel(int id, int idCliente, int idRestaurante, LocalDateTime dataHora, String status) {
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }
}
