public class Cliente extends Base{
    private String cpf;
    private double maxParcelamento;
    
    public Cliente(String nome, String email, String cpf, double maxParcelamento) {
        super(nome, email);
        this.cpf = cpf;
        this.maxParcelamento = maxParcelamento;
    }
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public double getMaxParcelamento() {
        return maxParcelamento;
    }
    public void setMaxParcelamento(double maxParcelamento) {
        this.maxParcelamento = maxParcelamento;
    }

    public String toCSV() {
        return super.toCSV() + ";" + this.cpf + ";" + this.maxParcelamento;
    }
    public String toString() {
        return super.toString() + " CPF: " + this.cpf + " Max Parcelamento: " + this.maxParcelamento;
    }
}
