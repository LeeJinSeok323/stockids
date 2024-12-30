package finalProject.domain.craw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrawItem {
    public String title;
    public String description;
    public String link;
    public String body;
}
