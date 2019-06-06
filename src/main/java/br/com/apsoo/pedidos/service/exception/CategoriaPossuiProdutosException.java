package br.com.apsoo.pedidos.service.exception;

public class CategoriaPossuiProdutosException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategoriaPossuiProdutosException(String message) {
        super(message);
    }

    public CategoriaPossuiProdutosException(String message, Throwable cause) {
        super(message, cause);
    }
}
