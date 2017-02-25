package parser.tools;

/**
 * Class {@code HtmlCleaner} suppose to clean {@code String} from HTML tags.
 */
public interface HtmlCleaner {
    /**
     * Returns {@code String} without any HTML tags.
     * @param text "dirty" with HTML tags
     * @return {@code String} "clean" text without HTML tags
     */
    String clean(String text);
}
