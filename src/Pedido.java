import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    // Cada pedido possui um ou mais itens de pedido contendo a quantidade, nome do produto, preço unitário e valor total do item. Além do item de pedido, o pedido possui um identificador, data, valor total e o CPF do cliente, para o qual  o pedido foi feito, e um campo status indicando se o pedido foi pago ou não

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
    private int id;
    private Date data;
    private double valorTotal;
    private String cpf;
    private boolean pago;
    private ArrayList<ItemPedido> itensPedido;
    
    public Pedido(int id, Date data, double valorTotal, String cpf, boolean pago, ArrayList<ItemPedido> itensPedido) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cpf = cpf;
        this.pago = pago;
        this.itensPedido = itensPedido;
    }
    
    public int getId() {
        return id;
    }
    
    public Date getData() {
        return data;
    }
    
    public double getValorTotal() {
        return valorTotal;
    }

    public String getCpf() {
        return cpf;
    }
    
    public boolean isPago() {
        return pago;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }
    
    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    public String toCSV() {
        return id + ";" + sdf.format(data) + ";" + valorTotal + ";" + cpf + ";" + pago + ";" + itensPedido.toString();
    }

    public String toString() {
        return "Pedido [id=" + id + ", data=" + data + ", valorTotal=" + valorTotal + ", cpf=" + cpf + ", pago=" + pago + ", itensPedido=" + itensPedido + "]";
    }
}
