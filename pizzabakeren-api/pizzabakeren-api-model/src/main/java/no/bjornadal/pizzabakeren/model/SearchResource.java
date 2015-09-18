package no.bjornadal.pizzabakeren.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by alfredw on 9/18/15.
 */
public class SearchResource extends ResourceSupport {

    private PageMetadata metadata;
    private EmbeddedWrapper embeddedWrapper;

    @JsonCreator
    public SearchResource(@JsonProperty(value = "page") PageMetadata metadata,
                          @JsonProperty(value = "_embedded") EmbeddedWrapper embeddedWrapper) {
        this.metadata = metadata;
        this.embeddedWrapper = embeddedWrapper;
    }

    public PageMetadata getMetadata() {
        return metadata;
    }

    public EmbeddedWrapper getEmbeddedWrapper() {
        return embeddedWrapper;
    }
}
