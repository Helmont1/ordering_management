public class Produto {
    private String nome;
    private String descricao;
    private double valorUnitario;
    private String cnpj;
    private String nomeFornecedor;

    public Produto(String nome, String descricao, double valorUnitario, String cnpj, String nomeFornecedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.cnpj = cnpj;
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String toCSV() {
        return this.nome + ";" + this.descricao + ";" + this.valorUnitario + ";" + this.cnpj + ";"
                + this.nomeFornecedor;
    }

    public String toString() {
        return "Nome: " + this.nome + " Descricao: " + this.descricao + " Valor Unitario: " + this.valorUnitario
                + " CNPJ: " + this.cnpj + " Nome Fornecedor: " + this.nomeFornecedor;
    }
}
