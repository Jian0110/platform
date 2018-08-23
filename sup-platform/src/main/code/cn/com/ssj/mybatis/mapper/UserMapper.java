package cn.com.ssj.mybatis.mapper;

import cn.com.ssj.dto.UserRequest;
import cn.com.ssj.mybatis.model.User;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {


    /**
     * 按用户ID查找
     *
     * @param request
     * @return
     */
    @Select("select user.*,(select dept.name from bd_department dept where dept.department_id = user.department_id) department_name from sys_user user where user.user_id = #{userId}")
    User findById(UserRequest request);


    /**
     * 查找用户
     *
     * @param request
     * @return 页
     */
    @SelectProvider(type = UserProvider.class, method = "findAll")
    Page<User> findAll(UserRequest request);

    @SelectProvider(type = UserProvider.class, method = "findByUsername")
    User findByUsername(String username);


    @Update("update sys_user set last_login_ts = now() where user_id = #{userId}")
    void updateLastLoginTs(UserRequest request);

    //查看个人信息
    @Select("select*,(SELECT s.name FROM sys_role s WHERE s.role_id = u.role_id ) role_name,(SELECT d.name FROM bd_department d where d.department_id = u.department_id) department_name from sys_user u where u.user_id=#{userId}")
    User findUserById(@Param("userId") int userId);


    class UserProvider {
        public String findByUsername(String username) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM("sys_user user");
                    WHERE("binary user.username = #{username}");
                }
            }.toString();
        }

        public String findAll(UserRequest request) {
            String sql = new SQL() {
                {
                    SELECT("user.*");
                    SELECT("(case user.`enable` when 0 then '停用' else '正常' end) enable_text");
                    SELECT("(select role.name from sys_role role where role.role_id = user.role_id ) role_name");
                    SELECT("(select dept.name from bd_department dept where dept.department_id = user.department_id ) department_name");
                    FROM("sys_user user");
                    if (StringUtils.isNotBlank(request.getUsername())) {
                        WHERE("user.username like CONCAT('%',#{username},'%') ");
                    }

                }
            }.toString();
            //logger.info(sql);
            return sql;
        }


    }
}
