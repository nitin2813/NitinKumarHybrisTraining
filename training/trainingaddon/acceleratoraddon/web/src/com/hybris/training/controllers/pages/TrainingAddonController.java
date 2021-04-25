package com.hybris.training.controllers.pages;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("/addon/product")
public class TrainingAddonController {
    @Autowired
    private ProductFacade productFacade;

    private static final Logger LOG = Logger.getLogger(TrainingAddonController.class);
    private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "/{code:.*}";

    @RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
    public String getProductDetails(@RequestParam("code") final String code,final Model model) throws CMSItemNotFoundException
    {

        final ProductData productData = productFacade.getProductForCodeAndOptions(code, Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
                ProductOption.URL, ProductOption.STOCK, ProductOption.VARIANT_MATRIX_BASE, ProductOption.VARIANT_MATRIX_URL,
                ProductOption.VARIANT_MATRIX_MEDIA));

        model.addAttribute("productData", productData);

        return "addon:/trainingaddon/pages/trainingaddonproductdetails";
    }
}
