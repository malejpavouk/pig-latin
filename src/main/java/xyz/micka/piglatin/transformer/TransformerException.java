package xyz.micka.piglatin.transformer;

public class TransformerException extends RuntimeException {
    public TransformerException(Throwable cause) {
        super("Exception happened during transformation to Pig Latin", cause);
    }
}
