import java.text.SimpleDateFormat;
import java.util.Date;

public class Fornecedor extends Base {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private String cnpj;
    private Date maxPrazo;
    
    public Fornecedor(String nome, String email, String cnpj, Date maxPrazo) {
        super(nome, email);
        this.cnpj = cnpj;
        this.maxPrazo = maxPrazo;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public Date getMaxPrazo() {
        return maxPrazo;
    }
    public void setMaxPrazo(Date maxPrazo) {
        this.maxPrazo = maxPrazo;
    }

    public String toCSV() {
        return super.toCSV() + ";" + this.cnpj + ";" + sdf.format(this.maxPrazo);
    }

    public String toString() {
        return super.toString() + " CNPJ: " + this.cnpj + " Max Prazo: " + sdf.format(this.maxPrazo);
    }

}
