package xyz.micka.piglatin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

class PigLatinAppTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    @Test
    public void pigLatinFullSentence() throws IOException {
        assertOutput("Hello apple, starway can't end.", "Ellohay appleway, starway antca'y endway.");
    }

    @Test
    public void pigLatinTwoParagraphs() throws IOException {
        assertOutput("Hello apple.\r\n starway can't end this-way.", "Ellohay appleway.\r\n starway antca'y endway histay-way.");
    }

    private void assertOutput(String input, String output) {
        var inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        var outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);

        System.setOut(ps);
        PigLatinApp.main(new String[]{});

        String sysout = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        Assertions.assertThat(sysout).isEqualTo(output);
    }

    @AfterEach
    public void resetStandardStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
}