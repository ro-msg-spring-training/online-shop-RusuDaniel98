package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dao.IProductCategoryDAO;
import ro.msg.learning.shop.dao.IProductDAO;
import ro.msg.learning.shop.dao.ISupplierDAO;
import ro.msg.learning.shop.dto.ProductAndCategoryDTO;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;

import java.util.List;

@Service
public class ProductAndCategoryService {

    @Autowired
    private IProductDAO productDAO;

    @Autowired
    private IProductCategoryDAO productCategoryDAO;

    @Autowired
    private ISupplierDAO supplierDAO;

    public Product readProductById(int id) {
        return productDAO.findById(id);
    }

    public List<Product> readProducts() {
        return productDAO.findAll();
    }

    public void createProductAndCategory(ProductAndCategoryDTO productAndCategoryDTO) {
        ProductCategory productCategory = new ProductCategory(
                productAndCategoryDTO.getCategoryName(),
                productAndCategoryDTO.getCategoryDescription()
        );
        productCategoryDAO.save(productCategory);

        // dummy supplier needed for a quick test.
        Supplier supplier = new Supplier();
        supplierDAO.save(supplier);

        Product product = new Product(
                productAndCategoryDTO.getName(),
                productAndCategoryDTO.getDescription(),
                productAndCategoryDTO.getPrice(),
                productAndCategoryDTO.getWeight(),
                productCategory,
                supplier,
                productAndCategoryDTO.getImageUrl()
        );
        productDAO.save(product);
    }

    public void updateProduct(ProductAndCategoryDTO productAndCategoryDTO, int productId) {
        Product product = productDAO.findById(productId);
        product.setName(productAndCategoryDTO.getName());
        product.setDescription(productAndCategoryDTO.getDescription());
        product.setPrice(productAndCategoryDTO.getPrice());
        product.setWeight(productAndCategoryDTO.getWeight());
        //TODO manage product category and supplier modification.
//        product.setProductCategory();
//        product.setSupplier();
        product.setImageUrl(productAndCategoryDTO.getImageUrl());
        productDAO.save(product);
    }

    public void deleteProductById(int id) {
        productDAO.deleteById(id);
    }

}
