package com.itheima;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("用户信息测试类")
public class UserServiceTest {
    @Test
    public void testGetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000200010011011");
        System.out.println(age);
    }

    @Test
    public void testGetGender(){
        UserService userService = new UserService();
        String gender = userService.getGender("100000200010011011");
        System.out.println(gender);
    }


    @Test
    public void testGetGenderWithAssert(){
        UserService userService = new UserService();
        String gender = userService.getGender("100000200010011011");
        Assertions.assertEquals("男",gender,"性别获取逻辑有问题");
    }

    /*@BeforeAll
    public static void beforeAll(){
        System.out.println("beforeAll");
    }*/

    /*
    *   参数化注释
    * */
    @DisplayName("测试用户性别")
    @ParameterizedTest
    @ValueSource(strings={"100000200010011011","100000200010011031","100000200010011051"})
    public void testGender2(String idCard){
        UserService userService = new UserService();
        String gender = userService.getGender(idCard);
        Assertions.assertEquals("男",gender,"性别获取逻辑有问题");
    }
}
