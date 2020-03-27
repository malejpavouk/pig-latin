package xyz.micka.piglatin.tokenizer;

public class TokenizerException extends RuntimeException {
    public TokenizerException(Throwable cause) {
        super("Cannot tokenize the given input", cause);
    }
}
