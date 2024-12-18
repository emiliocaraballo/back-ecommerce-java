package co.com.ecommerce.infrastructure.inadapter.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.ecommerce.application.business.interfaces.ProductBusiness;
import co.com.ecommerce.application.dto.request.ProductCreateDTO;
import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.application.dto.response.ProductSalesDTO;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.infrastructure.outadapter.persistence.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.request.mappings}/v1/product")
@Tag(name = "product", description = "Product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
@Slf4j
public class ProductController {
    private final ProductBusiness productBusiness;
    private final ProductMapper productMapper;


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(
        @Valid @RequestBody ProductCreateDTO body
    ) {
        log.info("start process: Creating product DTO: {}", body);
        Product product = productMapper.dtoToModel(body);
        Product response = productBusiness.createProduct(product);
        return Mono.just(response);
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable Long id) {
        log.info("start process: Finding product by id: {}", id);
        Product response = productBusiness.findById(id);
        return Mono.just(response);
    }

    @PutMapping("/update/{id}")
    public Mono<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductCreateDTO body) {
        log.info("start process: Updating product DTO: {}", body);
        Product product = productMapper.dtoToModel(body);
        product.setId(id);
        Product response = productBusiness.updatedProduct(product);
        return Mono.just(response);
    }

    @GetMapping( value = "/list")
    public Mono<List<Product>> getList(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "tradeMark", required = false) String tradeMark,
        @RequestParam(name = "status", required = false) String status
    ){
        List<Product> list = new ArrayList<>();
        List<Product> products = productBusiness.findAll(new ProductSearchDto(name, tradeMark, status));
        if(products.size() > 0) {
            list.addAll(products);
        }
        return Mono.just(list);
    }


    @GetMapping( value = "/recent-products")
    public Mono<List<Product>> getRecentProducts(){
        List<Product> list = new ArrayList<>();
        
        List<Product> products = productBusiness.findTopMostRecent();
        if(products.size() > 0) {
            list.addAll(products);
        }

        return Mono.just(list);
    }

    @GetMapping( value = "/best-sellers")
    public Mono<List<ProductSalesDTO>> getBestSellers(){
        List<ProductSalesDTO> list = new ArrayList<>();
    
        List<ProductSalesDTO> products = productBusiness.findTopMostSold();
        if(products.size() > 0) {
            list.addAll(products);
        }
        return Mono.just(list);
    }
}
