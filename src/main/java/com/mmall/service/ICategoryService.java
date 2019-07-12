package com.mmall.service;

import com.mmall.common.ServiceResponse;

public interface ICategoryService {
    ServiceResponse addCategory(String categoryName,Integer parentId);
    ServiceResponse updateCategoryName(Integer categoryid,String categoryName);
    ServiceResponse getChildrenParallelCategory(Integer categoryId);
    ServiceResponse getAllChildren(Integer categoryId);

}
