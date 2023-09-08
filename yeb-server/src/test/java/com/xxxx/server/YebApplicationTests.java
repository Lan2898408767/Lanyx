package com.xxxx.server;


import com.xxxx.server.service.IAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class YebApplicationTests {


    @Autowired
    IAdminService adminService ;

    @Test
    public void test () {
        System.out.println(adminService.count());
        System.out.println("哈哈哈");
    }

}
