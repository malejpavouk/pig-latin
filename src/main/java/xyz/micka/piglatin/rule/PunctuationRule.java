package xyz.micka.piglatin.rule;

import xyz.micka.piglatin.tokenizer.EnglishAlphabet;

public final class PunctuationRule implements PigLatinRule {
    public static final PunctuationRule INSTANCE = new PunctuationRule();
    private final EnglishAlphabet alphabet = EnglishAlphabet.INSTANCE;

    private PunctuationRule() {
    }

    /**
     * Makes sure that the position of punctuation in target is on the same position (from the end of the String)
     * as in the original String.
     *
     * @param original the original String
     * @param target   the target buffer (will be mutated)
     */
    @Override
    public void transform(String original, StringBuilder target) {
        removeAllPunctuation(target);
        replayPunctuation(original, target);
    }

    /**
     * Replay punctiation from original to target.
     * @param original the original String
     * @param target   the target buffer (will be mutated)
     */
    private void replayPunctuation(String original, StringBuilder target) {
        for (int i = original.length() - 1; i >= 0; i--) {
            if (alphabet.isIntraWordPunctuation(original.charAt(i))) {
                target.insert(target.length() - (original.length() - i) + 1, original.charAt(i));
            }
        }
    }

    private void removeAllPunctuation(StringBuilder target) {
        for (int i = target.length() - 1; i >= 0; i--) {
            if (alphabet.isIntraWordPunctuation(target.charAt(i))) {
                target.deleteCharAt(i);
            }
        }
    }
}
