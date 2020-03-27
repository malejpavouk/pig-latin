package xyz.micka.piglatin.tokenizer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EnglishAlphabetTest {

    private static final char UPPER_VOWEL = 'A';
    private static final char LOWER_VOWEL = 'a';
    private static final char UPPER_CONSONANT = 'B';
    private static final char LOWER_CONSONANT = 'b';
    private static final char KNOWN_DELIMITER = '\t';
    private static final char UNKNOWN_DELIMITER = '~';
    private static final char CHAR_Y = 'y';

    private EnglishAlphabet alphabet = EnglishAlphabet.INSTANCE;

    @Test
    public void testVowel() {
        Assertions.assertThat(alphabet.isVowel(UPPER_VOWEL)).isTrue();
        Assertions.assertThat(alphabet.isVowel(LOWER_VOWEL)).isTrue();

        Assertions.assertThat(alphabet.isVowel(UPPER_CONSONANT)).isFalse();;
        Assertions.assertThat(alphabet.isVowel(LOWER_CONSONANT)).isFalse();

        Assertions.assertThat(alphabet.isVowel(KNOWN_DELIMITER)).isFalse();
        Assertions.assertThat(alphabet.isVowel(UNKNOWN_DELIMITER)).isFalse();
    }

    @Test
    public void testConsonant() {
        Assertions.assertThat(alphabet.isConsonant('A')).isFalse();
        Assertions.assertThat(alphabet.isConsonant('a')).isFalse();

        Assertions.assertThat(alphabet.isConsonant('B')).isTrue();
        Assertions.assertThat(alphabet.isConsonant('b')).isTrue();

        Assertions.assertThat(alphabet.isVowel(KNOWN_DELIMITER)).isFalse();
        Assertions.assertThat(alphabet.isVowel(UNKNOWN_DELIMITER)).isFalse();
    }

    @Test
    public void charYisAlwaysVowel() {
        Assertions.assertThat(alphabet.isVowel(CHAR_Y)).isTrue();
        Assertions.assertThat(alphabet.isConsonant(CHAR_Y)).isFalse();
        Assertions.assertThat(alphabet.isDelimiter(CHAR_Y)).isFalse();
    }

    @Test
    public void testDelimiter() {
        Assertions.assertThat(alphabet.isDelimiter(KNOWN_DELIMITER)).isTrue();
    }

    @Test
    public void unknownCharactersAreIdentifiedAsDelimiters() {
        Assertions.assertThat(alphabet.isDelimiter(UNKNOWN_DELIMITER)).isTrue();
    }
}