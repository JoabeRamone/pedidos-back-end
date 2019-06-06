package br.com.apsoo.pedidos.service.exception;

public class ProdutoExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoExistenteException(String message) {
        super(message);
    }

    public ProdutoExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
