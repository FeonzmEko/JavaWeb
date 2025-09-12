package com.itheima;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("用户服务测试类")
public class UserServiceTest2 {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    // TC01: 测试 idCard 为 null 的情况
    @Test
    @DisplayName("测试 idCard 为 null 时抛出异常")
    public void testGetAge_IdCardIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge(null),
                "应抛出 IllegalArgumentException"
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    // TC02: 测试 idCard 长度不足
    @Test
    @DisplayName("测试 idCard 长度不足时抛出异常")
    public void testGetAge_IdCardTooShort() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge("12345"),
                "应抛出 IllegalArgumentException"
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    // TC03: 测试 idCard 长度超过
    @Test
    @DisplayName("测试 idCard 长度超过时抛出异常")
    public void testGetAge_IdCardTooLong() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge("1234567890123456789"),
                "应抛出 IllegalArgumentException"
        );
        assertEquals("无效的身份证号码", exception.getMessage());
    }

    // TC04: 测试合法身份证号返回正确年龄
    @Test
    @DisplayName("测试合法身份证号返回正确年龄")
    public void testGetAge_ValidIdCard() {
        // 假设身份证号为：110101199001011234 => 出生日期为 1990-01-01
        String idCard = "110101199001011234";
        Integer age = userService.getAge(idCard);

        // 计算预期年龄
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        int expectedAge = Period.between(birthDate, LocalDate.now()).getYears();

        assertEquals(expectedAge, age, "年龄计算不正确");
    }

    // 参数化测试：多个合法身份证号
    @ParameterizedTest
    @ValueSource(strings = {
            "110101199001011234",
            "100000200010011011",
            "440505198507123456"
    })
    @DisplayName("参数化测试多个合法身份证号")
    public void testGetAge_MultipleValidIdCards(String idCard) {
        assertDoesNotThrow(() -> {
            Integer age = userService.getAge(idCard);
            assertNotNull(age, "年龄不应为 null");
            assertTrue(age >= 0, "年龄应为非负数");
        });
    }
}
