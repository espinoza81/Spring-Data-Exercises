package productshop.service.impl;

import com.google.gson.Gson;
import productshop.constant.PathFiles;
import productshop.domain.category.Category;
import productshop.domain.product.*;
import productshop.domain.user.User;
import productshop.repository.CategoryRepository;
import productshop.repository.ProductRepository;
import productshop.repository.UserRepository;
import productshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ModelMapper mapper,
            Gson gson) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.gson = gson;
    }


    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (productRepository.count() == 0) {

            final FileReader fileReader = new FileReader(PathFiles.PRODUCTS_FILE_PATH_XML.toFile());

            final JAXBContext context = JAXBContext.newInstance(ProductImportXMLDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();

            ProductImportXMLDto productsXML = (ProductImportXMLDto) unmarshaller.unmarshal(fileReader);

            List<Product> products = productsXML.getProducts()
                    .stream()
                    .map(product -> mapper.map(product, Product.class))
                    .map(this::setRandomBuyer)
                    .map(this::setRandomSeller)
                    .map(this::setRandomCategories)
                    .toList();

//            final List<Product> products = Arrays.stream(gson.fromJson(fileReader, ProductImportDto[].class))
//                    .map(ProductImportDto -> mapper.map(ProductImportDto, Product.class))
//                    .map(this::setRandomSeller)
//                    .map(this::setRandomBuyer)
//                    .map(this::setRandomCategories)
//                    .toList();


            fileReader.close();

            this.productRepository.saveAllAndFlush(products);
        }
    }

    @Override
    public List<ProductWithoutBuyerDto> findAllWithoutBuyer(double bottom, double top) {
        BigDecimal bottom_price = BigDecimal.valueOf(bottom);
        BigDecimal top_price = BigDecimal.valueOf(top);

        return this.productRepository
                .findByBuyerNullAndPriceBetweenOrderByPrice(bottom_price, top_price)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .map(ProductDto::productWithoutBuyerDto)
                .toList();
    }

    private Product setRandomCategories(Product product) {

        final Random random = new Random();

        long high = this.categoryRepository.count();

        final long numberOfCategories = random.nextLong(high);

        final Set<Category> categories = new HashSet<>();

        LongStream.range(0, numberOfCategories)
                .forEach(number -> {
            Category category = this.categoryRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            categories.add(category);
        });

        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if(product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0) {

            User buyer = getRandomUser();

            while (buyer.equals(product.getSeller())) {
                buyer = getRandomUser();
            }

            product.setBuyer(buyer);
        }

        return product;
    }

    private User getRandomUser() {
        return this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
    }

    private Product setRandomSeller(Product product) {
        final User seller = getRandomUser();

        product.setSeller(seller);

        return product;
    }
}