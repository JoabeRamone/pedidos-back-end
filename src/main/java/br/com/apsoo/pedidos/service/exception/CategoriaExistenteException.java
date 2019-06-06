package br.com.apsoo.pedidos.service.exception;

public class CategoriaExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CategoriaExistenteException(String message) {
        super(message);
    }

    public CategoriaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
