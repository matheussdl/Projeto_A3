package model;

public class Venda {
    private int id;
    private Funcionario funcionario;
    private Produto produto;
    private int quantidade;
    private String data;
    private double precoUnitario;
    private String descricao;

    public Venda() {}

    public Venda(Funcionario funcionario, Produto produto, int quantidade, String data, double precoUnitario, String descricao) {
        this.funcionario = funcionario;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = data;
        this.precoUnitario = precoUnitario;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
