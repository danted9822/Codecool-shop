package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        int categoryId = (category != null) ? Integer.parseInt(category) : 1;
        String supplier = req.getParameter("supplier");
        int supplierId = (supplier != null) ? Integer.parseInt(supplier) : 0;

        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDaoMem supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,supplierDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("products", productService.getAllProduct());
         // Alternative setting of the template context
        Map<String, Object> params = new HashMap<>();

        if (category == null && supplier == null) {

            params.put("category", null);
            params.put("products", productDataStore.getAll());
        } else if (supplier == null){
            params.put("category", productCategoryDataStore.find(categoryId));
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        } else {
            params.put("category", supplierDataStore.find(supplierId));
            params.put("products", productDataStore.getBy(supplierDataStore.find(supplierId)));

        }
        context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}


