package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.EmbeddedWrapper;
import no.bjornadal.pizzabakeren.model.SearchResource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.ResourceAssembler;

/**
 * Created by alfredw on 9/18/15.
 */
public class SearchResourceAssembler implements ResourceAssembler<Page<OrderDocument>, SearchResource>{

    private OrderResourceAssembler orderResourceAssembler = new OrderResourceAssembler();

    @Override
    public SearchResource toResource(Page<OrderDocument> orderDocumentPage) {
        // TODO clean up
        PageMetadata metadata = new PageMetadata(orderDocumentPage.getSize(), orderDocumentPage.getNumber(), orderDocumentPage.getTotalElements());
        EmbeddedWrapper embeddedWrapper = new EmbeddedWrapper();
        for (OrderDocument orderDocument : orderDocumentPage.getContent()) {
            embeddedWrapper.getOrders().add(orderResourceAssembler.toResource(orderDocument));
        }

        // TODO add hrefs to resources
        return new SearchResource(metadata, embeddedWrapper);
    }
}
