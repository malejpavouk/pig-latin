package xyz.micka.piglatin.tokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.micka.piglatin.token.DelimiterToken;
import xyz.micka.piglatin.token.Token;
import xyz.micka.piglatin.token.WordToken;

import java.io.IOException;
import java.io.PushbackReader;

/**
 * Allows to iterate over individual tokens of the given string. The token can be:
 * * Word (see {@link WordToken})
 * * Delimiter (see {@link DelimiterToken})
 */
// Note: it would be more eye pleasing to make it implement Iterator interface
public class Tokenizer {
    private static final Logger logger = LoggerFactory.getLogger(Tokenizer.class);
    private final PushbackReader source;
    private final EnglishAlphabet alphabet;

    public Tokenizer(PushbackReader source) {
        this.source = source;
        alphabet = EnglishAlphabet.INSTANCE;
    }

    /**
     * Get next token from the source string.
     *
     * @return Token. Either Word ({@link WordToken}) or Delimiter {@link DelimiterToken}) or null if
     *     there are no more tokens in the given source String.
     */
    public Token getNext() {
        StringBuilder currentToken;
        try {
            int read = source.read();
            if (read == -1) {
                return null;
            } else if (alphabet.isDelimiter((char) read)) {
                logger.debug("New delimiter token produced: '{}'", (char) read);
                return new DelimiterToken(String.valueOf((char) read));
            }

            currentToken = new StringBuilder();
            do {
                char current = (char) read;
                currentToken.append(current);
                read = source.read();

                if (read != -1 && !isInWordCharacter((char) read, source)) {
                    source.unread(read); // the last character is part of the next token
                    break;
                }

            } while (read != -1);
        } catch (IOException ex) {
            logger.error("Exception thrown during tokenization", ex);
            throw new TokenizerException(ex);
        }

        logger.debug("New word token produced: '{}'", currentToken);
        return new WordToken(currentToken.toString());
    }

    /**
     * Take a look on the next character (and if read, return it back to the PushBackReader).
     *
     * @param reader the reader to perfor peek on
     * @return -1 if there is no character in the reader, otherwise int representing the next character
     * @throws IOException if unable to read from the reader
     */
    private int peek(PushbackReader reader) throws IOException {
        int read = reader.read();
        if (read != -1) {
            reader.unread(read);
        }
        return read;
    }

    /**
     * Checks if the character is inside the word. This means that it is either in-word puntuation and there is one
     * more vowel or consonant after this character in the word. Or is vowel or punctuation.
     *
     * @param character current charcter
     * @param reader    the reader, positioned directly after the current character
     * @return if the current character should be considered as part of the word
     * @throws IOException if unable to read from the reader
     */
    @SuppressWarnings("PMD.UselessParentheses")
    private boolean isInWordCharacter(char character, PushbackReader reader) throws IOException {
        var next = peek(reader);
        boolean nextCharIsLetter = next != -1 && !alphabet.isDelimiter((char) next);

        return alphabet.isVowel(character)
                || alphabet.isConsonant(character)
                || (alphabet.isIntraWordPunctuation(character) && nextCharIsLetter);
    }
}
