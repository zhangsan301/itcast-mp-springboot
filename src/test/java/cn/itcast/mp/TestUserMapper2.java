package cn.itcast.mp;

import cn.itcast.mp.enums.SexEnum;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectById(){
        User user= new User();
        user.setId(11L);

        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testInsert(){
        User user= new User();
        user.setUserName("diaochan");
        user.setPassword("123456");
        user.setAge(20);
        user.setName("貂蝉");
        user.setMail("diaochan@itcast.cn");
        user.setVersion(1);
        user.setSex(SexEnum.WOMAN);

        //调用AR的insert方法进行插入数据
        boolean insert = user.insert();
        System.out.println("result =>"+insert);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(2L);
        user.setAge(31);

        boolean result = user.updateById();
        System.out.println("result =>"+result);
    }

    /**
     * 测试乐关锁
     */
    @Test
    public void testUpdateVersion(){
        User user = new User();
        user.setId(2L); //查询条件

        User userVersion = user.selectById();

        user.setAge(23); //更新的数据
        user.setVersion(userVersion.getVersion()); //当前的版本信息

        boolean result = user.updateById();
        System.out.println("result =>"+result);
    }



    //测试全表更新，SQL分析器阻断效果
    @Test
    public void testUpdateAll(){
        User user = new User();
        user.setAge(31);

        boolean result = user.update(null); //全表更新
        System.out.println("result =>"+result);
    }



    @Test
    public void testDelete(){
        User user = new User();
        user.setId(7L);

        boolean delete = user.deleteById();
        System.out.println(delete);

    }

    @Test
    public void testSelect(){
        User user = new User();

        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.ge("age",30);

        List<User> users = user.selectList(wrapper);
        for(User user1:users){
            System.out.println(user1);
        }
    }

    @Test
    public void testSelectBySex(){
        User user = new User();

        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.ge("sex",SexEnum.WOMAN); //查询性别为女性的数据

        List<User> users = user.selectList(wrapper);
        for(User user1:users){
            System.out.println(user1);
        }
    }



}
