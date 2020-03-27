package xyz.micka.piglatin.rule;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PunctuationRuleTest extends AbstractRuleTest {

    @Test
    public void punctuationIsPlacedOnTheSameSpotFromEndAsInOriginalWord() {
        String source = "can't";
        String resultByPrevious = "an'tcay";

        assertResult(source, resultByPrevious, "antca'y");
    }

    @Override
    public PigLatinRule getRule() {
        return PunctuationRule.INSTANCE;
    }
}