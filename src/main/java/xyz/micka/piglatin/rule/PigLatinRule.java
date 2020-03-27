package xyz.micka.piglatin.rule;

public interface PigLatinRule {
    void transform(String original, StringBuilder target);
}
