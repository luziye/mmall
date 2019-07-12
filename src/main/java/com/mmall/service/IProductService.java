package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    ServiceResponse saveOrUpdateProduct(Product product);

    ServiceResponse<String> setSalesStatus(Integer productId, Integer status);

    ServiceResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServiceResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServiceResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);


}
