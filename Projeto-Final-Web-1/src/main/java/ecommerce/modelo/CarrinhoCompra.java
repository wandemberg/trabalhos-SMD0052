package ecommerce.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 *
 *
 * Classe que implementa os métodos ou lógica de negócio para o funcionamento do
 * carrinho de compras
 */
public class CarrinhoCompra {

    public static final String SEPARADOR_PRODUTO = "&";
    public static final String SEPARADOR_CAMPO = "@";

    public static final String CHAVE_COOKIE_CARRINHO_COMPRA = "smdecommerce.carrinhocompras";

    /**
     * Método estático utilizado para obter um objeto do tipo Cookie a partir da
     * requisição e do identificador cookie do carrinho de compras
     *
     * @param request
     * @return
     */
    public static final Cookie obterCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(CHAVE_COOKIE_CARRINHO_COMPRA)) {
                cookie = cookies[i];
                break;
            }
        }
        return cookie;
    }

    /**
     * Método estático utilizado para transformar em uma lista de itens do
     * carrinho de compras por meio do valor String do Cookie que representa o
     * carrinho de compras
     *
     * @param valor
     * @return
     */
    public static final List<CarrinhoCompraItem> obterCarrinhoCompra(String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = new ArrayList<>();
        if (valor == null || valor.trim().length() == 0 || !valor.contains(SEPARADOR_CAMPO)) {
            return carrinhoCompraItens;
        }
        ProdutoDAO produtoDAO = new ProdutoDAO();
        if (valor.contains(SEPARADOR_PRODUTO)) {
            String[] produtos = valor.split(SEPARADOR_PRODUTO);
            for (int i = 0; produtos != null && i < produtos.length; i++) {
                String[] item = produtos[i].split(SEPARADOR_CAMPO);
                CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
                Produto produto = null;
                try {
                    produto = produtoDAO.obter(Integer.parseInt(item[0]));
                } catch (Exception ex) {
                    produto = null;
                }
                carrinhoCompraItem.setProduto(produto);
                carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));
                carrinhoCompraItens.add(carrinhoCompraItem);
            }
        } else {
            String[] item = valor.split(SEPARADOR_CAMPO);
            CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
            Produto produto = null;
            try {
                produto = produtoDAO.obter(Integer.parseInt(item[0]));
            } catch (Exception ex) {
                produto = null;
            }
            carrinhoCompraItem.setProduto(produto);
            carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));
            carrinhoCompraItens.add(carrinhoCompraItem);
        }
        return carrinhoCompraItens;
    }

    /**
     * Método estático utilizado para adicionar um item de produto no carrinho
     * de compras, retornando a String com o produto adicionado/atualizado
     *
     * @param produtoId
     * @param quantidade
     * @param valor
     * @return
     */
    public static final String adicionarItem(int produtoId, int quantidade, String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return produtoId + SEPARADOR_CAMPO + quantidade;
        }
        boolean adicionou = false;
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                carrinhoCompraItem.setQuantidade(carrinhoCompraItem.getQuantidade() + quantidade);
                adicionou = true;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_CAMPO + carrinhoCompraItem.getQuantidade();
        }
        if (!adicionou) {
            resultado += SEPARADOR_PRODUTO + produtoId + SEPARADOR_CAMPO + quantidade;
        }
        return resultado;
    }

    /**
     * Método estático utilizado para remover um produto do carrinho de compras,
     * retornandoa String com o produto removido
     *
     * @param produtoId
     * @param valor
     * @return
     */
    public static final String removerItem(int produtoId, String valor) {
        List<CarrinhoCompraItem> carrinhoCompraItens = obterCarrinhoCompra(valor);
        if (carrinhoCompraItens.isEmpty()) {
            return "";
        }
        String resultado = "";
        for (CarrinhoCompraItem carrinhoCompraItem : carrinhoCompraItens) {
            if (carrinhoCompraItem.getProduto().getId() == produtoId) {
                continue;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += carrinhoCompraItem.getProduto().getId() + SEPARADOR_CAMPO + carrinhoCompraItem.getQuantidade();
        }
        return resultado;
    }

}
