import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
    private int id;
    private Date data;
    private double valorTotal;
    private String cpf;
    private boolean pago;

    public Pedido(int id, Date data, double valorTotal, String cpf, boolean pago) {
        this.id = id;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cpf = cpf;
        this.pago = pago;
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

    public String toCSV() {
        return this.id + ";" + sdf.format(this.data) + ";" + this.valorTotal + ";" + this.cpf + ";" + this.pago;
    }

    public String toString() {
        return "ID: " + this.id + " Data: " + sdf.format(this.data) + " Valor Total: " + this.valorTotal + " CPF: " + this.cpf + " Pago: " + this.pago;
    }

}
