package xyz.micka.piglatin.tokenizer;

import java.util.HashSet;
import java.util.Set;

/**
 * A helper class that helps to evaluate which is character is consonant and which is a vowel.
 * <p>
 * This implementation can be enhanced with context hints in case it would be necessary to distinguish
 * phonetics.
 * </p>
 */
public final class EnglishAlphabet {
    /* Char classes as one String, its easier to read/manage it this way */

    // y is considered as vowel in this assigment (validated with ordering party)
    private static final String VOWELS = "aeiouy";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    private static final String WORD_PUNCTUATIONS = "'’"; // we consider apostrophe and single quot mark as interchangeable

    private final Set<Character> vowels;
    private final Set<Character> consonants;
    private final Set<Character> punctuations;

    public static final EnglishAlphabet INSTANCE = new EnglishAlphabet();

    private EnglishAlphabet() {
        vowels = toSet(VOWELS);
        consonants = toSet(CONSONANTS);
        punctuations = toSet(WORD_PUNCTUATIONS);
    }

    public boolean isVowel(char character) {
        return vowels.contains(character);
    }

    public boolean isConsonant(char character) {
        return consonants.contains(character);
    }

    /**
     * All characters that are not vowels or consonants are considered
     * to be a delimiter (including intra word punctuations).
     *
     * @param character character to evaluate
     * @return true if the given char is a delimiter, false otherwise
     */
    public boolean isDelimiter(char character) {
        return !isConsonant(character) && !isVowel(character);
    }

    public boolean isIntraWordPunctuation(char character) {
        return punctuations.contains(character);
    }

    private Set<Character> toSet(String alphabetString) {
        var result = new HashSet<Character>();
        alphabetString.chars()
                .mapToObj(character -> (char) character)
                .forEach(character -> {
                    result.add(character);
                    result.add(Character.toUpperCase(character));
                });

        return result;
    }
}
