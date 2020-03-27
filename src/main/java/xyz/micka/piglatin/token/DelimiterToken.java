package xyz.micka.piglatin.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelimiterToken extends Token {
    private static final Logger logger = LoggerFactory.getLogger(DelimiterToken.class);

    public DelimiterToken(String original) {
        super(original);
    }

    @Override
    public String getPigLatin() {
        logger.debug("Delimiter translation is the same as its original: '{}'", getOriginal());
        return getOriginal();
    }
}
