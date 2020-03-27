package xyz.micka.piglatin.rule;

import xyz.micka.piglatin.tokenizer.EnglishAlphabet;

public final class VowelRule implements PigLatinRule {
    public static final VowelRule INSTANCE = new VowelRule();
    private final EnglishAlphabet alphabet = EnglishAlphabet.INSTANCE;

    private VowelRule() {
    }

    /**
     * Transforms words that start with a vowel.
     * Rules:
     * * append "way"
     * apple -> appleway
     *
     * @param original the original String
     * @param target   the target buffer (will be mutated)
     */
    @Override
    public void transform(String original, StringBuilder target) {
        if (!alphabet.isVowel(original.charAt(0)) || original.endsWith("way")) {
            return;
        }

        target.append("way");
    }
}
