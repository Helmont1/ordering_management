import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Relatorios {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public Relatorios(ArrayList<Cliente> clientes, ArrayList<Fornecedor> fornecedores, ArrayList<Produto> produtos,
            ArrayList<Pedido> pedidos) throws ParseException {
        int op = 0;
        var sc = new Scanner(System.in);

        do {
            limpaConsole();
            System.out.println("1 - Relatório de Clientes");
            System.out.println("2 - Relatório de Fornecedores");
            System.out.println("3 - Relatório de Produtos");
            System.out.println("4 - Relatório de Pedidos");
            System.out.println("5 - Listagem de pedidos feitos em um tempo determinado");
            System.out.println("6 - Busca de um pedido pelo número");
            System.out.println("7 - Listagem de todos os pedidos pagos");
            System.out.println("8 - Busca de um produto pelo nome deste");
            System.out.println("9 - Cálculo do total dos pedidos em aberto (não pagos).");
            System.out.println("10 - Voltar");
            System.out.print("Opção: ");
            op = sc.nextInt();
            var scannerString = new Scanner(System.in);
            var scanner = new Scanner(System.in);
            switch (op) {
                case 1:
                    listaClientes(clientes, scannerString);
                    break;
                case 2:
                    listaFornecedores(fornecedores, scannerString);
                    break;
                case 3:
                    listaProdutos(produtos, scannerString);
                    break;
                case 4:
                    listaPedidos(pedidos, scannerString);
                    break;
                case 5:
                    listaPedidosPorData(pedidos, scannerString);
                    break;
                case 6:
                    buscaPedidoPorId(pedidos, scannerString, scanner);
                    break;
                case 7:
                    listaPedidosPagos(pedidos, scannerString);
                    break;
                case 8:
                    buscaProdutoPorNome(produtos, scannerString);
                    break;
                case 9:
                    calculoPedidosEmAberto(pedidos, scannerString);
                    break;
                case 10:
                    System.out.println("Voltando ao menu!");
                    scannerString.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (op != 10);
    }

    private void calculoPedidosEmAberto(ArrayList<Pedido> pedidos, Scanner scannerString) {
        // Calcula o total dos pedidos em aberto (não pagos) e exibe o resultado.
        System.out.println("Cálculo do total dos pedidos em aberto (não pagos).");
        var total = 0.0;
        for (var pedido : pedidos) {
            if (!pedido.isPago()) {
                total += pedido.getValorTotal();
            }
        }
        System.out.println("Total dos pedidos em aberto: " + total);

        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void buscaProdutoPorNome(ArrayList<Produto> produtos, Scanner scannerString) {
        // Acha um produto pelo nome e printa o produto
        System.out.println("Busca de um produto pelo nome deste");
        System.out.println("Digite o nome do produto");
        var nomeProduto = scannerString.nextLine();
        for (var produto : produtos) {
            if (produto.getNome().equals(nomeProduto)) {
                System.out.println(produto.toString());
            }
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void listaPedidosPagos(ArrayList<Pedido> pedidos, Scanner scannerString) {
        // Lista todos os pedidos pagos
        System.out.println("Listagem de todos os pedidos pagos");
        for (var pedido : pedidos) {
            if (pedido.isPago()) {
                System.out.println(pedido.toString());
            }
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void buscaPedidoPorId(ArrayList<Pedido> pedidos, Scanner scannerString, Scanner scanner) {
        // Printa um pedido pelo id
        System.out.println("Busca de um pedido pelo número");
        System.out.println("Digite o número do pedido");
        var numeroPedido = scanner.nextInt();
        for (var pedido : pedidos) {
            if (pedido.getId() == numeroPedido) {
                System.out.println(pedido.toString());
            }
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void listaPedidosPorData(ArrayList<Pedido> pedidos, Scanner scannerString) throws ParseException {
        // Lista de pedidos feitos em um tempo determinado
        System.out.println("Listagem de todos os pedidos feitos em um determinado intervalo de datas");
        System.out.println("Digite a data inicial");
        var dataInicial = scannerString.nextLine();
        System.out.println("Digite a data final");
        var dataFinal = scannerString.nextLine();
        for (var pedido : pedidos) {
            if (pedido.getData().after(sdf.parse(dataInicial))
                    && pedido.getData().before(sdf.parse(dataFinal))) {
                System.out.println(pedido.toString());
            }

        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void listaPedidos(ArrayList<Pedido> pedidos, Scanner scannerString) {
        // Lista todos os pedidos
        System.out.println("Listagem de todos os Pedidos");
        for (var pedido : pedidos) {
            System.out.println(pedido.toString());
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void listaProdutos(ArrayList<Produto> produtos, Scanner scannerString) {
        // Listagem de todos os produtos
        System.out.println("Listagem de todos os Produtos");
        for (var produto : produtos) {
            if (produto != null)
                System.out.println(produto.toString());
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void listaFornecedores(ArrayList<Fornecedor> fornecedores, Scanner scannerString) {
        // Listagem de todos os Fornecedores
        System.out.println("Listagem de todos os Fornecedores");
        for (var fornecedor : fornecedores) {
            System.out.println(fornecedor.toString());
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    private void listaClientes(ArrayList<Cliente> clientes, Scanner scannerString) {
        // Listagem de todos os clientes
        System.out.println("Listagem de todos os Clientes");
        for (var cliente : clientes) {
            System.out.println(cliente.toString());
        }
        System.out.println("Pressione qualquer tecla para continuar...");
        scannerString.nextLine();
    }

    public void limpaConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
