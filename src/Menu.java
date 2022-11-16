import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("resource")
public class Menu {
    public Menu(ArrayList<Cliente> clientes, ArrayList<Fornecedor> fornecedores, ArrayList<Produto> produtos,
            ArrayList<Pedido> pedidos) throws ParseException {
        int opcao = 0;
        do {
            var sc = new Scanner(System.in);
            limpaConsole();
            System.out.println("Sistema de Vendas");
            System.out.println("1 - Cadastro de Clientes");
            System.out.println("2 - Cadastro de Fornecedores");
            System.out.println("3 - Cadastro de Produtos");
            System.out.println("4 - Efetuação de um pedido");
            System.out.println("5 - Baixa de pagamento de um pedido");
            System.out.println("6 - Relatórios");
            System.out.println("7 - Sair");
            System.out.print("Digite a opção desejada: ");
            opcao = 0;
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    var novoCliente = cadastrarCliente();
                    clientes.add(novoCliente);
                    break;
                case 2:
                    var novoFornecedor = cadastrarFornecedor();
                    fornecedores.add(novoFornecedor);
                    break;
                case 3:
                    var novoProduto = cadastrarProduto(produtos);
                    produtos.add(novoProduto);
                    break;
                case 4:
                    var novoPedido = efetuarPedido(produtos);
                    pedidos.add(novoPedido);
                    break;
                case 5:
                    baixarPagamento(pedidos);
                    break;
                case 6:
                    new Relatorios(clientes, fornecedores, produtos, pedidos);
                    break;
                case 7:
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 7);

        gravaArquivoClientes(clientes);
        gravaArquivoFornecedores(fornecedores);
        gravaArquivoProdutos(produtos);
        gravaArquivoPedidos(pedidos);
    }

    private static void gravaArquivoPedidos(ArrayList<Pedido> pedidos) {
        var linhas = new ArrayList<String>();
        for (var pedido : pedidos) {
            linhas.add(pedido.toCSV());
        }
        try {
            Files.write(Paths.get("data/pedidos.csv"), linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void gravaArquivoProdutos(ArrayList<Produto> produtos) {
        var linhas = new ArrayList<String>();

        for (var produto : produtos) {
            if (produto != null)
                linhas.add(produto.toCSV());
        }
        try {
            Files.write(Paths.get("data/produtos.csv"), linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gravaArquivoFornecedores(ArrayList<Fornecedor> fornecedores) {
        var linhas = new ArrayList<String>();
        for (var fornecedor : fornecedores) {
            linhas.add(fornecedor.toCSV());
        }
        try {
            Files.write(Paths.get("data/fornecedores.csv"), linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gravaArquivoClientes(ArrayList<Cliente> clientes) {
        var linhas = new ArrayList<String>();
        for (var cliente : clientes) {
            linhas.add(cliente.toCSV());
        }
        try {
            Files.write(Paths.get("data/clientes.csv"), linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void limpaConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls")
                .inheritIO()
                .start()
                .waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Pedido efetuarPedido(ArrayList<Produto> produtos) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        var valorTotal = 0.0;
        var sc = new Scanner(System.in);
        var scString = new Scanner(System.in);
        var itensPedido = new ArrayList<ItemPedido>();
        System.out.println("Efetuar Pedido");
        System.out.print("Digite o id do pedido: ");
        var id = sc.nextInt();
        System.out.print("Digite a data do pedido (dd-MM-yyyy): ");
        var data = sdf.parse(scString.nextLine());
        System.out.print("Digite o CPF do cliente: ");
        var cpf = scString.nextLine();
        System.out.println("Para finalizar o pedido digite 0 no nome e na quantidade");
        while (true) {
            System.out.print("Digite o nome do produto: ");
            var nomeProduto = scString.nextLine();
            System.out.print("Digite a quantidade do produto: ");
            var quantidadeProduto = sc.nextInt();
            if (nomeProduto != "0" && quantidadeProduto != 0) {
                Produto produto = produtos.stream()
                                    .filter(p -> p.getNome().equals(nomeProduto))
                                    .findFirst()
                                    .orElse(null);
                if (produto != null) {
                    var itemPedido = new ItemPedido(nomeProduto, quantidadeProduto, produto.getValorUnitario(),
                            produto.getValorUnitario() * quantidadeProduto);
                    itensPedido.add(itemPedido);
                    valorTotal += itemPedido.getPrecoTotal();
                } else {
                    System.out.println("Produto não encontrado!");
                }
            } else {
                break;
            }
        }
        var pedido = new Pedido(id, data, valorTotal, cpf, false, itensPedido);
        return pedido;
    }

    private Produto cadastrarProduto(ArrayList<Produto> produtos) {
        var sc = new Scanner(System.in);
        var scString = new Scanner(System.in);
        System.out.print("Digite o nome do produto: ");
        var nome = scString.nextLine();
        if (produtos.stream().anyMatch(p -> p.getNome().equals(nome))) {
            System.out.println("Produto já cadastrado!");
            scString.nextLine();
            return null;
        }
        System.out.print("Digite a descrição do produto: ");
        var descricao = scString.nextLine();
        System.out.print("Digite o preço do produto: ");
        var preco = sc.nextDouble();
        System.out.print("Digite o CNPJ do fornecedor: ");
        var cnpj = scString.nextLine();
        System.out.print("Digite o nome do fornecedor: ");
        var nomeFornecedor = scString.nextLine();
        var novoProduto = new Produto(nome, descricao, preco, cnpj, nomeFornecedor);
        return novoProduto;
    }

    private Fornecedor cadastrarFornecedor() throws ParseException {
        var sdf = new SimpleDateFormat("dd-MM-yyyy");
        var sc = new Scanner(System.in);
        var scString = new Scanner(System.in);
        System.out.println("Cadastro de Fornecedores");
        System.out.print("Nome: ");
        var nome = sc.nextLine();
        System.out.print("Email: ");
        var email = sc.nextLine();
        System.out.print("CNPJ: ");
        var cnpj = sc.nextLine();
        System.out.print("Prazo maximo de faturamento (dd-MM-yyyy): ");
        var maxPrazo = sdf.parse(scString.nextLine());

        var novoFornecedor = new Fornecedor(nome, email, cnpj, maxPrazo);
        return novoFornecedor;
    }

    private Cliente cadastrarCliente() {
        var sc = new Scanner(System.in);
        var scString = new Scanner(System.in);
        System.out.println("Cadastro de Clientes");
        System.out.print("Nome: ");
        var nome = scString.nextLine();
        System.out.print("Email: ");
        var email = scString.nextLine();
        System.out.print("CPF: ");
        var cpf = scString.nextLine();
        System.out.print("Máximo de parcelamento: ");
        var maxParcelamento = sc.nextDouble();

        var novoCliente = new Cliente(nome, email, cpf, maxParcelamento);
        return novoCliente;
    }

    private void baixarPagamento(ArrayList<Pedido> pedidos) {
        var sc = new Scanner(System.in);
        System.out.print("Digite o id do pedido: ");
        var id = sc.nextInt();
        for (var pedido : pedidos) {
            if (pedido.getId() == id) {
                pedido.setPago(true);
                System.out.println("Pagamento efetuado com sucesso!");
                return;
            }
        }
    }
}
