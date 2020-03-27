package xyz.micka.piglatin.transformer;

import org.apache.commons.io.IOUtils;
import xyz.micka.piglatin.token.Token;
import xyz.micka.piglatin.tokenizer.Tokenizer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PushbackReader;
import java.nio.charset.StandardCharsets;

public class PigLatinTransformer {
    /**
     * Transforms the source string to PigLatin.
     *
     * @param source stream of original english words (UTF-8)
     * @param sink stream where the results will be written
     */
    public void transform(InputStream source, PrintStream sink) {
        try (InputStream inputStream = IOUtils.toBufferedInputStream(source)) {
            var tokenizer = new Tokenizer(new PushbackReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8), 2));
            Token currentToken;
            while ((currentToken = tokenizer.getNext()) != null) {
                sink.print(currentToken.getPigLatin());
            }
        } catch (IOException ex) {
            throw new TransformerException(ex);
        }
    }
}
