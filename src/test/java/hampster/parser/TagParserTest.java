package hampster.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import hampster.exception.HampsterException;

class TagParserTest {

    @Test
    void parseOption_withoutTag_keepsDescription() throws HampsterException {
        TagParser.ParsedInput result = TagParser.parseOption("buy milk");

        assertEquals("buy milk", result.description());
        assertEquals("", result.tag());
    }

    @Test
    void parseOption_withTag_removesOptionAndNormalizesTag() throws HampsterException {
        TagParser.ParsedInput result = TagParser.parseOption("Buy milk /tag #PERSONAL");

        assertEquals("Buy milk", result.description());
        assertEquals("#personal", result.tag());
    }

    @Test
    void parseOption_withTagBeforeDescription_preservesRemainingWords()
            throws HampsterException {
        TagParser.ParsedInput result = TagParser.parseOption("/tag #work finish report");

        assertEquals("finish report", result.description());
        assertEquals("#work", result.tag());
    }

    @Test
    void parseOption_withDuplicateTagOption_throwsException() {
        assertThrows(HampsterException.class, () ->
                TagParser.parseOption("task /tag #one /tag #two"));
    }

    @Test
    void parseOption_withoutTagValue_throwsException() {
        assertThrows(HampsterException.class, () ->
                TagParser.parseOption("task /tag"));
    }

    @Test
    void normalize_validTag_returnsLowercaseTag() throws HampsterException {
        assertEquals("#urgent", TagParser.normalize("#URGENT"));
    }

    @Test
    void normalize_missingHash_throwsException() {
        assertThrows(HampsterException.class, () -> TagParser.normalize("urgent"));
    }

    @Test
    void normalize_emptyTag_throwsException() {
        assertThrows(HampsterException.class, () -> TagParser.normalize(""));
    }

    @Test
    void normalize_nullTag_throwsException() {
        assertThrows(HampsterException.class, () -> TagParser.normalize(null));
    }

    @Test
    void normalize_tagWithUnsupportedCharacters_throwsException() {
        assertThrows(HampsterException.class, () -> TagParser.normalize("#bad|tag"));
        assertThrows(HampsterException.class, () -> TagParser.normalize("#bad tag"));
        assertThrows(HampsterException.class, () -> TagParser.normalize("#café"));
    }
}
