package com.example.gerenciador_produtos.principal;

import com.example.gerenciador_produtos.model.Categoria;
import com.example.gerenciador_produtos.model.Pedido;
import com.example.gerenciador_produtos.model.Produto;
import com.example.gerenciador_produtos.repository.CategoriaRepository;
import com.example.gerenciador_produtos.repository.PedidoRepository;
import com.example.gerenciador_produtos.repository.ProdutoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    private Scanner scanner;
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private PedidoRepository pedidoRepository;

    public Principal(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pedidoRepository = pedidoRepository;
        this.scanner = new Scanner(System.in);
    }

    public void salvarDados(){
        var continuar = true;

        while(continuar) {
            System.out.println("-- SISTEMA DE GESTÃO DE PRODUTOS --");
            System.out.println("Escolha uma das opções: ");

            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Visualizar todos os produtos");
            System.out.println("3 - Exibir produtos pelo nome");
            System.out.println("4 - Produtos de uma categoria");
            System.out.println("5 - Produtos a partir de tal valor");
            System.out.println("6 - Produtos menores que determinado valor");
            System.out.println("7 - Pedidos sem data definida");
            System.out.println("8 - Pedidos com data definida");
            System.out.println("9 - Pegar produtos de uma categoria ordem crescente");
            System.out.println("10 - Pegar produtos de uma categoria ordem decrescente");
            System.out.println("11 - Total Produtos de uma Categoria");
            System.out.println("12 - Produtos com valor maior que");
            System.out.println("13 - Filtragem avançada");
            System.out.println("14 - Filtragem após a data");
            System.out.println("15 - Filtragem antes da data");
            System.out.println("16 - 3 Produtos mais caros");
            System.out.println("17 - Top 5 mais baratos");
            System.out.println("18 - Produtos a partir de um valor");
            System.out.println("19 - Produtos pelo preço crescente");
            System.out.println("20 - Produtos pelo preço decrescente");
            System.out.println("21 - Listar Produtos por Letra");
            System.out.println("22 - Listar Produtos entre duas datas");
            System.out.println("23 - Listar Média de Preço");
            System.out.println("24 - Listar Maior preço por Categoria");
            System.out.println("25 - Total de Produtos");
            System.out.println("26 - Categorias com mais de 2 Produtos");
            System.out.println("27 - Listar Produtos por nome ou Categoria");
            System.out.println("28 - Query Nativa SQL - Listar Produtos mais caros");
            var escolha = scanner.nextInt();
            if(escolha == 0){
                continuar = false;
            }

            switch (escolha) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Digite o nome do produto: ");
                    var nomeProduto = scanner.nextLine();


                    System.out.println("Digite o preco do produto: ");
                    var preco = scanner.nextDouble();
                    Produto produto = new Produto(nomeProduto, preco);
                    List<Produto> produtos = new ArrayList<>();
                    produtos.add(produto);

                    scanner.nextLine();
                    System.out.println("Digite o nome da categoria do produto: ");
                    var nomeCategoria = scanner.nextLine();
                    Categoria categoria = categoriaRepository.findByNome(nomeCategoria).orElseGet(() -> new Categoria(nomeCategoria, new ArrayList<>()));
                    Pedido pedido = new Pedido();
                    produto.setCategoria(categoria);
                    produto.setPedido(pedido);

                    categoriaRepository.save(categoria);
                    pedidoRepository.save(pedido);
                    produtoRepository.save(produto);

                    break;

                case 2:
                    exibirTodosProdutos();
                    break;
                case 3:
                    exibirProdutosPeloNome();
                    break;
                case 4:
                    exibirProdutosDeUmaCategoria();
                    break;
                case 5:
                    exibirProdutosAPartirdeValor();
                    break;
                case 6:
                    exibirProdutosMenoresValor();
                    break;
                case 7:
                    exibirPedidosSemData();
                    break;
                case 8:
                    exibirPedidosComData();
                    break;
                case 9:
                    exibirProdutosCategoriaCrescente();
                    break;
                case 10:
                    exibirProdutosCategoriaDecrescente();
                    break;
                case 11:
                    totalProdutosCategoria();
                    break;
                case 12:
                    produtosPrecoMaior();
                    break;
                case 13:
                    exibirProdutosPorValorOuNome();
                    break;
                case 14:
                    exibirPedidosAposData();
                    break;
                case 15:
                    exibirPedidosAntesData();
                    break;
                case 16:
                    exibirTop3();
                    break;
                case 17:
                    exibirTop5BaratosCategoria();
                    break;
                case 18:
                    buscarProdutosComValorMaior();
                    break;
                case 19:
                    listagemProdutosPeloPrecoCrescente();
                    break;
                case 20:
                    listagemProdutosPeloPrecoDecrescente();
                    break;
                case 21:
                    listarProdutosQueComecamComLetra();
                    break;
                case 22:
                    listarProdutosPedidosEntreDatas();
                    break;
                case 23:
                    listaMediaDePreco();
                    break;
                case 24:
                    listaMaiorPrecoDeCategoria();
                    break;
                case 25:
                    totalDeProdutos();
                    break;
                case 26:
                    categoriaComMaisDe2Produtos();
                case 27:
                    listarProdutosPorNomeOuCategoria();
                case 28:
                    listarOsProdutosMaisCaros();
            }
        }
    }

    private void listarOsProdutosMaisCaros() {
        List<Produto> produrtosCaros = produtoRepository.produtosMaisCaros();
        produrtosCaros.forEach(System.out::println);
    }

    private void listarProdutosPorNomeOuCategoria() {
        scanner.nextLine();
        System.out.println("Qual produto deseja buscar?");
        var nomeProduto = scanner.nextLine();
        System.out.println("Qual a categoria?");
        var nomeCategoria = scanner.nextLine();
        List<Produto> produtos = produtoRepository.produtosPorNomeOuCategoria(nomeCategoria, nomeProduto);
        produtos.forEach(p -> {
            System.out.println(p.getNome() + " R$ " + p.getPreco());
        });

    }

    private void categoriaComMaisDe2Produtos() {
        List<Categoria> categorias = categoriaRepository.categoriasComMaisDe2Produtos();
        categorias.forEach(categoria -> {
            System.out.println(categoria.getNome());
        });
    }

    private void totalDeProdutos() {
        scanner.nextLine();
        System.out.println("Digite uma categoria: ");
        var nomeCategoria = scanner.nextLine();
        Integer total = produtoRepository.totalProdutos(nomeCategoria);
        System.out.println("O total de produtos é " + total);
    }

    private void listaMaiorPrecoDeCategoria() {
        scanner.nextLine();
        System.out.println("Qual categoria deseja buscar? ");
        var nomeCategoria = scanner.nextLine();
        Double valor = produtoRepository.valorMaximoCategoria(nomeCategoria);
        System.out.println("O valor máximo da categoria " + nomeCategoria + " é R$" + valor);

    }

    private void listaMediaDePreco() {
        Double mediaDePreco = produtoRepository.mediaDePrecosProdutos();
        System.out.println("A média de preço dos produtos é " + mediaDePreco);
    }

    private void listarProdutosPedidosEntreDatas() {
        scanner.nextLine();
        System.out.println("Digite a data inicial: ");
        var dataInicial = scanner.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicialFormatada = LocalDate.parse(dataInicial, dateTimeFormatter);
        System.out.println("Digite a data final: ");
        var dataFinal = scanner.nextLine();
        LocalDate dataFinalFormatada = LocalDate.parse(dataFinal, dateTimeFormatter);
        List<Produto> produtos = produtoRepository.produtosEntreDatas(dataInicialFormatada, dataFinalFormatada);
        produtos.forEach(p -> {
            System.out.println(p.getNome() + " - R$ " + p.getPreco());
        });
    }

    private void listarProdutosQueComecamComLetra() {
        System.out.println("Digite a letra que deseja buscar:");
        scanner.nextLine();
        var letra = scanner.nextLine();
        List<Produto> produtos = produtoRepository.produtosQueComecamCom(letra);
        produtos.forEach(p -> {
            System.out.println(p.getNome() + " - R$ " + p.getPreco());
        });
    }

    private void listagemProdutosPeloPrecoDecrescente() {
        List<Produto> produtosOrdenadosCrescente = produtoRepository.produtosPeloPrecoDecrescente();
        produtosOrdenadosCrescente.forEach(p -> {
            System.out.println(p.getNome() + " - R$ " + p.getPreco());
        });
    }

    private void listagemProdutosPeloPrecoCrescente() {
        List<Produto> produtosOrdenadosCrescente = produtoRepository.produtosPeloPrecoCrescente();
        produtosOrdenadosCrescente.forEach(p -> {
            System.out.println(p.getNome() + " - R$ " + p.getPreco());
        });
    }

    private void buscarProdutosComValorMaior() {
        System.out.println("Digite o valor desejado: ");
        var valor = scanner.nextDouble();
        List<Produto> produtos = produtoRepository.buscarProdutosComValorMaiorQue(valor);
        produtos.forEach(p -> {
            System.out.println(p.getNome() + " - R$ " + p.getPreco());
        });
    }

    private void exibirTop5BaratosCategoria() {
        System.out.println("Digite o nome da categoria; ");
        scanner.nextLine();
        var nomeCategoria = scanner.nextLine();
        List<Produto> produtos = produtoRepository.findTop5ByCategoriaNomeContainingIgnoreCaseOrderByPrecoAsc(nomeCategoria);
        produtos.forEach(produto -> System.out.println(produto.getNome() + " R$ " + produto.getPreco()));
    }

    private void exibirTop3() {
        List<Produto> produtosCaros = produtoRepository.findTop3ByOrderByPrecoDesc();
        produtosCaros.forEach(produto -> System.out.println(produto.getNome() + " R$ " + produto.getPreco()));
    }

    private void exibirPedidosAntesData() {
        System.out.println("Digite uma data: ");
        scanner.nextLine();
        var data = scanner.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, dateTimeFormatter);
        List<Pedido> pedidos = pedidoRepository.findByDataBefore(dataFormatada);
        pedidos.forEach(p ->{
                    System.out.println(p.getId());
                    p.getProdutosPedido().forEach(produto -> System.out.println(produto.getNome() + " R$ " + produto.getPreco()));
                }

        );

    }

    private void exibirPedidosAposData() {
        System.out.println("Digite uma data: ");
        scanner.nextLine();
        var data = scanner.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, dateTimeFormatter);
        List<Pedido> pedidos = pedidoRepository.findByDataAfter(dataFormatada);
        pedidos.forEach(p ->{
                System.out.println(p.getId());
                p.getProdutosPedido().forEach(produto -> System.out.println(produto.getNome() + " R$ " + produto.getPreco()));
        }

        );

    }

    private void exibirProdutosPorValorOuNome() {
        scanner.nextLine();
        System.out.println("Digite um valor:");
        var valor = scanner.nextDouble();
        System.out.println("Digite um nome: ");
        scanner.nextLine();
        var nome = scanner.nextLine();

        List<Produto> produtos = produtoRepository.findByPrecoGreaterThanOrNomeContainingIgnoreCase(valor, nome);
        produtos.forEach(p -> {
            System.out.println(p.getNome() + " R$ " + p.getPreco());
        });
    }

    private void produtosPrecoMaior() {
        System.out.println("Digite o valor que deseja: ");
        scanner.nextLine();
        var valor = scanner.nextDouble();
        Integer contagem = produtoRepository.countByPrecoGreaterThan(valor);
        System.out.println("Total " + contagem);
    }

    private void totalProdutosCategoria() {
        System.out.println("Qual categoria você escolhe??");
        scanner.nextLine();
        var nomeCategoria = scanner.nextLine();
        System.out.println("Total de produtos na categoria " + nomeCategoria);
        Integer numero = produtoRepository.countByCategoriaNomeContainingIgnoreCase(nomeCategoria);
        System.out.println("Total: " + numero);
    }

    private void exibirProdutosCategoriaDecrescente() {
        System.out.println("Digite a categoria que deseja procurar:");
        scanner.nextLine();
        var categoriaNome = scanner.nextLine();
        List<Produto> produtosCategoria = produtoRepository.findByCategoriaNomeOrderByPrecoDesc(categoriaNome);
        System.out.println("Produtos da categoria: " + categoriaNome);
        produtosCategoria.forEach(p -> {
            System.out.println(p.getNome() + " R$ " + p.getPreco());
        });
    }

    private void exibirProdutosCategoriaCrescente() {
        System.out.println("Digite a categoria que deseja procurar:");
        scanner.nextLine();
        var categoriaNome = scanner.nextLine();
        List<Produto> produtosCategoria = produtoRepository.findByCategoriaNomeOrderByPreco(categoriaNome);
        System.out.println("Produtos da categoria: " + categoriaNome);
        produtosCategoria.forEach(p -> {
            System.out.println(p.getNome() + " R$ " + p.getPreco());
        });
    }

    private void exibirPedidosComData() {
        System.out.println("Lista de pedidos com data definida ainda: ");
        List<Pedido> listaPedidos = pedidoRepository.findByDataIsNotNull();
        listaPedidos.forEach(p -> {
            System.out.println("Pedido: " + p.getId());
            p.getProdutosPedido().forEach(produto -> System.out.println(produto.getNome() + " R$" + produto.getPreco()));
        });
    }

    private void exibirPedidosSemData() {
        System.out.println("Lista de pedidos sem data definida ainda: ");
        List<Pedido> listaPedidos = pedidoRepository.findByDataIsNull();
        listaPedidos.forEach(p -> {
            System.out.println("Pedido: " + p.getId());
            p.getProdutosPedido().forEach(produto -> System.out.println(produto.getNome() + " R$" + produto.getPreco()));
        });
    }

    private void exibirProdutosAPartirdeValor() {
        System.out.println("Digite a partir de qual valor deseja listar:");
        var valorProduto = scanner.nextDouble();
        List<Produto> produtosValor = produtoRepository.findProdutoByPrecoGreaterThan(valorProduto);
        System.out.println("Produtos com valor maior que " + "R$ " + valorProduto);
        produtosValor.forEach(p -> {
            System.out.println(p.getNome() + " - " + "R$ " + p.getPreco());
        });
    }

    private void exibirProdutosMenoresValor(){
        System.out.println("Qual valor limite você deseja definir?");
        var valorProduto = scanner.nextDouble();
        List<Produto> produtos = produtoRepository.findProdutoByPrecoLessThan(valorProduto);
        System.out.println("Produtos com valor menor que " + "R$ " + valorProduto);
        produtos.forEach(p -> {
            System.out.println(p.getNome() + " - " + "R$ " + p.getPreco());
        });

    }

    private void exibirProdutosDeUmaCategoria() {
        scanner.nextLine();
        System.out.println("Digite a categoria desejada: ");
        var nomeCategoria = scanner.nextLine();
        Optional<Categoria> categoria = categoriaRepository.findByNome(nomeCategoria);
        List<Produto> produtosCategoria = produtoRepository.findProdutoByCategoria(categoria);
        produtosCategoria.forEach(p -> {
            System.out.println(p.getNome() + " - " + "R$ " + p.getPreco());
        });

    }

    public void exibirTodosProdutos() {
        List<Produto> produtosNoBanco = produtoRepository.findAll();
        produtosNoBanco.forEach(p -> {
            System.out.println(p.getNome() + " - " + "R$ " + p.getPreco());
        });
    }

    private void exibirProdutosPeloNome() {
        System.out.println("Digite o nome que deseja buscar");
        scanner.nextLine();
        var nomeProduto = scanner.nextLine();
        List<Produto> produtosPeloNome = produtoRepository.findProdutoByNomeContainingIgnoreCase(nomeProduto);
        produtosPeloNome.forEach(p -> {
            System.out.println(p.getNome() + " - " + "R$ " + p.getPreco());
        });
    }
}