public class ItemPedido {
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;
    

    public ItemPedido(String nomeProduto, int quantidade, double precoUnitario, double precoTotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoTotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }
    
    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    @Override
    public String toString() {
        return "[" + this.nomeProduto + "," + this.quantidade + "," + this.precoUnitario + "," + this.precoTotal + "]";
    }
}
