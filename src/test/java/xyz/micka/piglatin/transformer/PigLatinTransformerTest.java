package xyz.micka.piglatin.transformer;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

class PigLatinTransformerTest {
    public static final Charset UTF8 = StandardCharsets.UTF_8;
    private PigLatinTransformer transformer = new PigLatinTransformer();

    @Test
    public void wordContainingHyphenIsHandledAsTwoWords() {
        assertTransformerResult("this-thing", "histay-hingtay");
    }

    @Test
    public void punctuationAtTheEndOfTheWordsStaysThere() {
        assertTransformerResult("end.", "endway.");
    }

    @Test
    public void quotationUsingApostrophesIsTreatedAsOutsideOfTheWord() {
        assertTransformerResult("'apple'", "'appleway'");
    }

    @Test
    public void quotationUsingSingleQuotesIsTreatedAsOutsideOfTheWord() {
        assertTransformerResult("’apple’", "’appleway’");
    }

    @Test
    public void transformerExceptionThrownIfInputCannotBeRead() throws IOException {
        InputStream mock = Mockito.mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doThrow(IOException.class).when(mock).read();
        Mockito.doThrow(IOException.class).when(mock).read(Mockito.any()); // be sure that some read is called :-)
        org.junit.jupiter.api.Assertions.assertThrows(TransformerException.class, () -> {
            var transformer = new PigLatinTransformer();
            transformer.transform(mock, System.out);
        });
    }

    private void assertTransformerResult(String source, String expected) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_8);
        transformer.transform(IOUtils.toInputStream(source, UTF8), out);
        Assertions.assertThat(baos.toString(StandardCharsets.UTF_8)).isEqualTo(expected);
    }
}
