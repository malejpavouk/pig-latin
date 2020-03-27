package xyz.micka.piglatin.rule;

import xyz.micka.piglatin.tokenizer.EnglishAlphabet;

public final class ConsonantRule implements PigLatinRule {
    public static final ConsonantRule INSTANCE = new ConsonantRule();
    private final EnglishAlphabet alphabet = EnglishAlphabet.INSTANCE;

    private ConsonantRule() {
    }

    /**
     * Transforms the words that start with a consonant.
     * Rules:
     * * move first letter to last position (lowercase)
     * * append "ay"
     * hello -> ellohay
     *
     * @param original the original String
     * @param target   the target buffer (will be mutated)
     */
    @Override
    public void transform(String original, StringBuilder target) {
        if (!alphabet.isConsonant(original.charAt(0)) || original.endsWith("way")) {
            return;
        }

        var firstChar = target.charAt(0);

        target.deleteCharAt(0)
                .append(Character.toLowerCase(firstChar))
                .append("ay");
    }
}
