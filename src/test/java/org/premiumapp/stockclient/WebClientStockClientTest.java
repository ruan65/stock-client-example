package org.premiumapp.stockclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

class WebClientStockClientIntegrationTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        // given
        WebClientStockClient client = new WebClientStockClient(webClient);
        String symbol = "SYMBOL";

        // when
        Flux<StockPrice> prices = client.pricesFor(symbol);

        // then
        Assertions.assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertTrue(fivePrices.count().block() > 0);
        Assertions.assertEquals(symbol, fivePrices.blockFirst().getSymbol());


    }
}