
import Controller.ClienteController;
import Model.ClienteModel;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();


        System.out.println("\n--- Cadastrando Cliente ---");
        ClienteModel novoCliente = new ClienteModel(0, "João Silva", "(11) 98765-4321");
        clienteController.cadastrarCliente(novoCliente);


        System.out.println("\n--- Listando Clientes ---");
        List<ClienteModel> clientes = clienteController.listarTodosClientes();
        if (clientes != null) {
            clientes.forEach(System.out::println);
        }


        System.out.println("\n--- Buscando Cliente por ID ---");
        if (novoCliente.getId_cliente() != 0) {
            ClienteModel clienteEncontrado = clienteController.buscarClientePorId(novoCliente.getId_cliente());
            if (clienteEncontrado != null) {
                System.out.println("Cliente encontrado: " + clienteEncontrado);
            } else {
                System.out.println("Cliente com ID " + novoCliente.getId_cliente() + " não encontrado.");
            }
        }


        System.out.println("\n--- Atualizando Cliente ---");
        if (novoCliente.getId_cliente() != 0) {
            novoCliente.setNome("João Silva Atualizado");
            novoCliente.setTelefone("(11) 99999-8888");
            clienteController.atualizarCliente(novoCliente);
            ClienteModel clienteAtualizado = clienteController.buscarClientePorId(novoCliente.getId_cliente());
            if (clienteAtualizado != null) {
                System.out.println("Cliente atualizado: " + clienteAtualizado);
            }
        }


        System.out.println("\n--- Deletando Cliente ---");
        if (novoCliente.getId_cliente() != 0) {
            clienteController.deletarCliente(novoCliente.getId_cliente());
            ClienteModel clienteDeletado = clienteController.buscarClientePorId(novoCliente.getId_cliente());
            if (clienteDeletado == null) {
                System.out.println("Cliente com ID " + novoCliente.getId_cliente() + " deletado com sucesso.");
            } else {
                System.out.println("Falha ao deletar cliente com ID " + novoCliente.getId_cliente() + ".");
            }
        }
    }
}

