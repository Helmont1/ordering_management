import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class App {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) throws Exception {
        // Criação de objetos de clientes, fornecedores, produtos e pedidos
        var clientes = new ArrayList<Cliente>();
        leArquivoClientes(clientes);

        var fornecedores = new ArrayList<Fornecedor>();

        leArquivoFornecedores(fornecedores);
        var produtos = new ArrayList<Produto>();
        leArquivoProdutos(produtos);

        var pedidos = new ArrayList<Pedido>();
        leArquivoPedidos(pedidos);
        // Chama o menu principal
        new Menu(clientes, fornecedores, produtos, pedidos);
    }

    private static void leArquivoPedidos(ArrayList<Pedido> pedidos)
            throws IOException, NumberFormatException, ParseException {
        // Leitura do csv de pedidos e criação dos objetos de pedidos
        var linhas = Files.readAllLines(Paths.get("data/pedidos.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var novoPedido = new Pedido(Integer.parseInt(dados[0]),
                                                         sdf.parse(dados[1]),
                                                         Double.parseDouble(dados[2]),
                                                         dados[3],
                                                         Boolean.parseBoolean(dados[4]),
                                                         leCsvItemPedidos(dados[5]));
            pedidos.add(novoPedido);
        }
    }

    private static ArrayList<ItemPedido> leCsvItemPedidos(String dados) {
        // Leitura do csv de itens de pedidos e criação dos objetos de itens de pedidos
        ArrayList<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();
        for (var item : dados.split("],")) {
            var novoItem = item.replace("]", "").replace("[", "").split(",");
            itemPedidos.add(new ItemPedido(novoItem[0],
                                           Integer.parseInt(novoItem[1]),
                                           Double.parseDouble(novoItem[2]),
                                           Double.parseDouble(novoItem[3])));
        }
        return itemPedidos;
    }

    private static void leArquivoProdutos(ArrayList<Produto> produtos) throws IOException {
        // Leitura do csv de produtos e criação dos objetos de produtos
        var linhas = Files.readAllLines(Paths.get("data/produtos.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var novoProduto = new Produto(dados[0],
                                          dados[1],
                                          Double.parseDouble(dados[2]),
                                          (dados[3]),
                                          dados[4]);
            produtos.add(novoProduto);
        }
    }

    private static void leArquivoFornecedores(ArrayList<Fornecedor> fornecedores) throws IOException, ParseException {
        // Leitura do csv de fornecedores e criação dos objetos de fornecedores
        var linhas = Files.readAllLines(Paths.get("data/fornecedores.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var novoFornecedor = new Fornecedor(dados[0],
                                                dados[1],
                                                dados[2],
                                                sdf.parse(dados[3]));
            fornecedores.add(novoFornecedor);
        }
    }

    private static void leArquivoClientes(ArrayList<Cliente> clientes) throws IOException {
        // Leitura do csv de clientes e criação dos objetos de clientes
        var linhas = Files.readAllLines(Paths.get("data/clientes.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var cliente = new Cliente(dados[0],
                                      dados[1],
                                      dados[2],
                                      Double.parseDouble(dados[3]));
            clientes.add(cliente);
        }
    }
}
