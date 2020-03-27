package xyz.micka.piglatin.rule;

public final class CapitalizationRule implements PigLatinRule {
    public static final CapitalizationRule INSTANCE = new CapitalizationRule();

    private CapitalizationRule() {
    }

    /**
     * Makes sure that the capitalization in target is on the same position as in the original String.
     *
     * @param original the original String
     * @param target   the target buffer (will be mutated)
     */
    @Override
    public void transform(String original, StringBuilder target) {
        if (original.length() > target.length()) {
            throw new IllegalArgumentException("Original: '" + original + "' is longer than target: '" + target + "'");
        }
        for (int i = 0; i < original.length(); i++) {
            if (Character.isUpperCase(original.charAt(i))) {
                char toBeReplaced = target.charAt(i);
                target.replace(i, i + 1, String.valueOf(Character.toUpperCase(toBeReplaced)));
            } else {
                char toBeReplaced = target.charAt(i);
                target.replace(i, i + 1, String.valueOf(Character.toLowerCase(toBeReplaced)));
            }
        }
    }
}
