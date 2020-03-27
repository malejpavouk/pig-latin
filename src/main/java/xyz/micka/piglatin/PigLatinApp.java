package xyz.micka.piglatin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entrypoint to our Pig Latin application. Its a command line tool that reads English from standard in (in UTF-8)
 * and writes the result in Pig Latin to standard out.
 */
public final class PigLatinApp {
    private static final Logger logger = LoggerFactory.getLogger(PigLatinApp.class);

    private PigLatinApp() {
    }

    /**
     * Main entrypoint.
     * @param args the args are ignored, as this app does not use them
     */
    public static void main(String[] args) {
        logger.info("PigLatin App started");
        var transformer = new PigLatinTransformer();
        transformer.transform(System.in, System.out);
    }
}
