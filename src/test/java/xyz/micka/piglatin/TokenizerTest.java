package xyz.micka.piglatin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import xyz.micka.piglatin.token.DelimiterToken;
import xyz.micka.piglatin.token.Token;
import xyz.micka.piglatin.token.WordToken;
import xyz.micka.piglatin.tokenizer.Tokenizer;
import xyz.micka.piglatin.tokenizer.TokenizerException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;

class TokenizerTest {

    @Test
    public void testSimpleToken() {
        var token = "test";
        Tokenizer tokenizer = getTokenizer(token);

        assertTokenEquality(tokenizer.getNext(), token, WordToken.class);
        Assertions.assertThat(tokenizer.getNext()).isNull();
    }

    @Test
    public void emptyStringTokenizationResultsInNull() {
        Tokenizer tokenizer = getTokenizer("");

        Assertions.assertThat(tokenizer.getNext()).isNull();
    }

    @Test
    public void multipleDelimitersResultInSeparateTokens() {
        var delim1 = ".";
        var delim2 = "\t";
        var delim3 = " ";
        var delim4 = ";";
        var tokenizer = getTokenizer(delim1 + delim2 + delim3 + delim4);

        assertTokenEquality(tokenizer.getNext(), delim1, DelimiterToken.class);
        assertTokenEquality(tokenizer.getNext(), delim2, DelimiterToken.class);
        assertTokenEquality(tokenizer.getNext(), delim3, DelimiterToken.class);
        assertTokenEquality(tokenizer.getNext(), delim4, DelimiterToken.class);
        Assertions.assertThat(tokenizer.getNext()).isNull();
    }

    @Test
    public void hyphenSplitsTokens() {
        var sugar = "sugar";
        var hyphen = "-";
        var free = "free";
        var sugarFree = sugar + hyphen + free;

        var tokenizer = getTokenizer(sugarFree);

        assertTokenEquality(tokenizer.getNext(), sugar, WordToken.class);
        assertTokenEquality(tokenizer.getNext(), hyphen, DelimiterToken.class);
        assertTokenEquality(tokenizer.getNext(), free, WordToken.class);
    }

    @Test
    public void apostropheInsideWordsDoesNotSplitTokens() {
        var cant = "can't";
        var tokenizer = getTokenizer(cant);

        assertTokenEquality(tokenizer.getNext(), cant, WordToken.class);
    }

    @Test
    public void apostropheAtTheEndOfTheWordIsNewToken() {
        var apostrophe = "'";
        var doin = "doin";
        var tokenizer = getTokenizer(doin + apostrophe);

        assertTokenEquality(tokenizer.getNext(), doin, WordToken.class);
        assertTokenEquality(tokenizer.getNext(), apostrophe, DelimiterToken.class);
    }


    @Test
    public void twoSameDelimitersResultInTwoTokens() {
        var delim1 = " ";
        var delim2 = " ";

        var tokenizer = getTokenizer(delim1 + delim2);

        assertTokenEquality(tokenizer.getNext(), delim1, DelimiterToken.class);
        assertTokenEquality(tokenizer.getNext(), delim2, DelimiterToken.class);
        Assertions.assertThat(tokenizer.getNext()).isNull();
    }

    @Test
    public void twoStringsSeparatedBySpaceResultInThreeTokens() {
        var token = "testA";
        var delimiter = " ";
        var token2 = "testB";
        var tokenizer = getTokenizer(token + delimiter + token2);

        assertTokenEquality(tokenizer.getNext(), token, WordToken.class);
        assertTokenEquality(tokenizer.getNext(), delimiter, DelimiterToken.class);
        assertTokenEquality(tokenizer.getNext(), token2, WordToken.class);
        Assertions.assertThat(tokenizer.getNext()).isNull();
    }

    @Test
    public void tokenizerExceptionThrownIfInputCannotBeRead() throws IOException {

        PushbackReader mock = Mockito.mock(PushbackReader.class);
        Mockito.doThrow(IOException.class).when(mock).read();
        org.junit.jupiter.api.Assertions.assertThrows(TokenizerException.class, () -> {
            var tokenizer = new Tokenizer(mock);
            tokenizer.getNext();
        });
    }

    private Tokenizer getTokenizer(String token) {
        var reader = new PushbackReader(new InputStreamReader(new ByteArrayInputStream(token.getBytes(StandardCharsets.UTF_8))), 2);
        return new Tokenizer(reader);
    }

    private void assertTokenEquality(Token next, String expectedContent, Class<? extends Token> expectedClazz) {
        Assertions.assertThat(next)
                .isInstanceOf(expectedClazz)
                .extracting(Token::getOriginal)
                .isEqualTo(expectedContent);
    }


}