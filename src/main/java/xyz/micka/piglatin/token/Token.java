package xyz.micka.piglatin.token;

public abstract class Token {
    private final String original;

    public Token(String original) {
        this.original = original;
    }

    public String getOriginal() {
        return this.original;
    }

    public abstract String getPigLatin();
}
