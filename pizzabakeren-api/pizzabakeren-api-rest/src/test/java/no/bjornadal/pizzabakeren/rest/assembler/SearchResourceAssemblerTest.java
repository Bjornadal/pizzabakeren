package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.SearchResource;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Created by alfredw on 9/25/15.
 */
public class SearchResourceAssemblerTest {

    @Test
    public void toResource() {
        SearchResourceAssembler searchResourceAssembler = new SearchResourceAssembler();
        List<OrderDocument> content = Arrays.asList(new OrderDocument(28, "Sprite", "g1", "Ola Nordmann", "2015-09-25", 65));
        Page<OrderDocument> orderDocumentPage = new PageImpl<>(content, new PageRequest(0, 10), 1);

        SearchResource searchResource = searchResourceAssembler.toResource(orderDocumentPage);

        assertThat(searchResource.getEmbeddedWrapper().getOrders(), hasSize(1));
    }
}
