package br.com.apsoo.pedidos.service.exception;

public class ProdutoNaoPodeSerAtualizadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoNaoPodeSerAtualizadoException(String message) {
        super(message);
    }

    public ProdutoNaoPodeSerAtualizadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
