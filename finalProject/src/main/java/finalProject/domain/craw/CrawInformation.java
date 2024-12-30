package finalProject.domain.craw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrawInformation {
    public List<CrawItem> items;
}
