package cn.com.ssj.controller;

import cn.com.ssj.dto.DataTablesObject;
import cn.com.ssj.dto.MenuObject;
import cn.com.ssj.dto.ResultObject;
import cn.com.ssj.dto.UserRequest;
import cn.com.ssj.mybatis.model.User;
import cn.com.ssj.service.UserService;
import com.github.pagehelper.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String MENU_OBJECT = "MENU_OBJECT";
    private static final List<MenuObject> STATIC_MENU_LIST = new ArrayList<>();

    {
        MenuObject menuObject = new MenuObject();
        menuObject.setTitle("基础资料查询");
        menuObject.setMenuId(1000);
        menuObject.setHref("/demo/goods-list-page");
        menuObject.setIcon("<i class=\"menu-icon fa fa-sliders\"></i>");
        STATIC_MENU_LIST.add(menuObject);

        menuObject = new MenuObject();
        menuObject.setTitle("供应商资料");
        menuObject.setMenuId(1001);
        menuObject.setHref("/demo/supplier-list-page");
        STATIC_MENU_LIST.add(menuObject);

        menuObject = new MenuObject();
        menuObject.setTitle("订单确认");
        menuObject.setMenuId(1002);
        menuObject.setHref("/demo/order-list-page");
        STATIC_MENU_LIST.add(menuObject);

        menuObject = new MenuObject();
        menuObject.setTitle("订单发货");
        menuObject.setMenuId(1003);
        menuObject.setHref("/demo/order-send-page");
        STATIC_MENU_LIST.add(menuObject);

        menuObject = new MenuObject();
        menuObject.setTitle("发货单入库查询");
        menuObject.setMenuId(1004);
        menuObject.setHref("/demo/order-send-history-page");
        STATIC_MENU_LIST.add(menuObject);
    }

    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserService userService;


    @RequestMapping(value = "/user-list", method = RequestMethod.GET)
    @RequiresPermissions("user:user-list")
    public String userListPage() {
        logger.info("userListPage");
        return "user/user-list";
    }


    /**
     * 个人信息查看编辑
     *
     * @return
     */
    @RequestMapping(value = "/user-edit-info-page", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView userInfoPage() {
        User cUser = (User) SecurityUtils.getSubject().getPrincipal();
        User user = this.userService.findUserById(cUser.getUserId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user/user-edit-info");
        logger.info("userInfoPage {}", user.toString());
        return mv;
    }


    /**
     * 按页取用户列表
     *
     * @param userRequest userRequest
     * @return dataTables
     */
    @RequestMapping(value = "/user-page-data", method = RequestMethod.POST)
    @RequiresPermissions("user:user-list")
    @ResponseBody
    public DataTablesObject<User> listAllData(@RequestBody UserRequest userRequest) {

        Page<User> userPage = this.userService.findAllByPage(userRequest);
        DataTablesObject<User> userDataTables = new DataTablesObject<>();
        userDataTables.setData(userPage.getResult());
        userDataTables.setRecordsTotal(userPage.getTotal());
        userDataTables.setRecordsFiltered(userPage.getTotal());
        userDataTables.setDraw(userRequest.getDraw());

        return userDataTables;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getCurrentMenu", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getMenu(HttpServletRequest httpServletRequest) {
        Object menuObject = SecurityUtils.getSubject().getSession().getAttribute(MENU_OBJECT);
        List<MenuObject> menuList = null;
        if (menuObject == null) {
            Object object = SecurityUtils.getSubject().getPrincipal();
            User user = null;
            if (object instanceof User) {
                user = (User) object;
            }
            //SecurityUtils.getSubject().hasRole("ADMIN");
            //httpServletRequest.getContextPath()
            menuList = STATIC_MENU_LIST;
            SecurityUtils.getSubject().getSession().setAttribute(MENU_OBJECT, STATIC_MENU_LIST);
        } else {
            if (menuObject instanceof List) {
                menuList = (List<MenuObject>) menuObject;
            }
        }
        return ResultObject.ACK(menuList);
    }


    @RequestMapping(value = "/user-get-username")
    @ResponseBody
    public ResultObject getUserName() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Map<String, String> data = new HashMap<>();
        data.put("username", user.getUsername());
        return ResultObject.ACK(data);
    }


}
