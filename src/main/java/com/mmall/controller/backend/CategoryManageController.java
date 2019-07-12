package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;
    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse addCategory(HttpSession session, String categoryName,
                                       @RequestParam(value = "parentId",defaultValue = "0")int parentId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            return iCategoryService.addCategory(categoryName,parentId);
        }else {
            return ServiceResponse.createByErrorMessage("无权限，需要管理员权限");
        }
    }
    @RequestMapping(value = "set_category_name.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse updateCategoryName(HttpSession session, Integer categoryId,String categoryName){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }else {
            return ServiceResponse.createByErrorMessage("无权限，需要管理员权限");
        }
    }
    @RequestMapping(value = "get_category.do",method = RequestMethod.POST)
    @ResponseBody
    public  ServiceResponse getChildrenParallelCategpry(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0")int categoryId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //查询子节点category信息，不递归，平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else {
            return ServiceResponse.createByErrorMessage("无权限，需要管理员权限");
        }
    }
    @RequestMapping(value = "get_all_category.do",method = RequestMethod.POST)
    @ResponseBody
    public  ServiceResponse getAllChildrenCategpry(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0")int categoryId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //查询子节点category信息，递归
            return iCategoryService.getAllChildren(categoryId);
        }else {
            return ServiceResponse.createByErrorMessage("无权限，需要管理员权限");
        }
    }
}
