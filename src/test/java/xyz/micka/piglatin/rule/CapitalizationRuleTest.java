package xyz.micka.piglatin.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CapitalizationRuleTest extends AbstractRuleTest {
    @Test
    public void exceptionThrownWhenOriginalIsLongerThanTarget() {
        assertThrows(IllegalArgumentException.class, () -> getRule().transform("aBc", new StringBuilder("a")));
    }

    @Test
    public void capitalizesTargetOnTheSamePlacesAsSourceWas() {
        String source = "McCloud";
        String resultByPrevious = "ccloudmay";

        assertResult(source, resultByPrevious, "CcLoudmay");
    }

    @Override
    public PigLatinRule getRule() {
        return CapitalizationRule.INSTANCE;
    }
}