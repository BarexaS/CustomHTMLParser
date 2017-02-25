package pharser;

/**
 * Class {@code PageReader} suppose to download html-page
 */
public interface PageReader {

    /**
     * Returns {@code String} that represent html-page.
     *
     * @param link to page for downloading
     * @return html page as {@code String}
     */
    String getPage(String link);
}
