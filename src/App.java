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
        var clientes = new ArrayList<Cliente>();
        leArquivoClientes(clientes);

        var fornecedores = new ArrayList<Fornecedor>();
        leArquivoFornecedores(fornecedores);

        var produtos = new ArrayList<Produto>();
        leArquivoProdutos(produtos);

        var pedidos = new ArrayList<Pedido>();
        leArquivoPedidos(pedidos);
        
        new Menu(clientes, fornecedores, produtos, pedidos);
    }

    private static void leArquivoPedidos(ArrayList<Pedido> pedidos) throws IOException, NumberFormatException, ParseException {
        var linhas = Files.readAllLines(Paths.get("data/pedidos.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var novoPedido = new Pedido(Integer.parseInt(dados[0]), sdf.parse(dados[1]), Double.parseDouble(dados[2]), dados[3], Boolean.parseBoolean(dados[4]));
            pedidos.add(novoPedido);
        }
    }

    private static void leArquivoProdutos(ArrayList<Produto> produtos) throws IOException {
        var linhas = Files.readAllLines(Paths.get("data/produtos.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var novoProduto = new Produto(dados[0], dados[1], Double.parseDouble(dados[2]), (dados[3]), dados[4]);
            produtos.add(novoProduto);
        }
    }

    private static void leArquivoFornecedores(ArrayList<Fornecedor> fornecedores) throws IOException, ParseException {
        var linhas = Files.readAllLines(Paths.get("data/fornecedores.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var novoFornecedor = new Fornecedor(dados[0], dados[1], dados[2], sdf.parse(dados[3]));
            fornecedores.add(novoFornecedor);
        }
    }

    private static void leArquivoClientes(ArrayList<Cliente> clientes) throws IOException {
        var linhas = Files.readAllLines(Paths.get("data/clientes.csv"), Charset.defaultCharset());
        for (var linha : linhas) {
            var dados = linha.split(";");
            var cliente = new Cliente(dados[0], dados[1], dados[2], Double.parseDouble(dados[3]));
            clientes.add(cliente);
        }
    }
}
