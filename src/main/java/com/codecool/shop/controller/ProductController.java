package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.CartService;
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
        int cartCounter = 0;

        CartDao cartDataStore = CartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,supplierDataStore);
        CartService cartService = new CartService(cartDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("products", productService.getAllProduct());


        String addCart = req.getParameter("add-cart");

        if (cartService.getAll().size() == 0) cartService.add();


        if (addCart != null)
            cartService.addProductToCart(1, productDataStore.find(Integer.parseInt(addCart)));

        if (cartDataStore.getAll().size() != 0) cartCounter = cartService.getCartSize(1);
         // Alternative setting of the template context
        Map<String, Object> params = new HashMap<>();
        params.put("cartCounter", cartCounter);

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
//        resp.sendRedirect("/");
        context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}


