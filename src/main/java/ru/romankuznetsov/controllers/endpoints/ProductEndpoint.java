package ru.romankuznetsov.controllers.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.ws.products.GetAllProductsRequest;
import ru.geekbrains.spring.ws.products.GetAllProductsResponse;
import ru.geekbrains.spring.ws.products.GetProductByIdRequest;
import ru.geekbrains.spring.ws.products.GetProductByIdResponse;
import ru.romankuznetsov.service.ProductServiceSoap;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/ws/products";
    private final ProductServiceSoap productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse GetProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getProductById(request.getId()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.geekbrains.ru/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.geekbrains.ru/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>1</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
