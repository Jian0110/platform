package cn.com.ssj.service;

import cn.com.ssj.dto.UserRequest;
import cn.com.ssj.mybatis.helper.PaginationHelper;
import cn.com.ssj.mybatis.model.User;
import com.github.pagehelper.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends PaginationHelper {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    /*
    @Autowired
    UserMapper userMapper;

    @Autowired
    CacheManager cacheManager;
    */

    public User findByUsername(String username) throws MyBatisSystemException {
        return null;
    }

    /**
     * 个人信息查看与编辑
     *
     * @param userId
     * @return
     */
    public User findUserById(int userId) {
        return null;
    }


    /**
     * 按条件查找，返回页
     *
     * @param userRequest userRequest
     * @return page
     */
    public Page<User> findAllByPage(UserRequest userRequest) {
        //this.pagination(userRequest);
        //return userMapper.findAll(userRequest);
        return null;
    }



    public void updateLastLoginTs(UserRequest request) {
        //this.userMapper.updateLastLoginTs(request);

    }



}
