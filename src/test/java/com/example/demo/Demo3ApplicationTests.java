package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.demo.entity.Reptileconfig;
import com.example.demo.entity.User;
import com.example.demo.mapper.ReptileconfigMapper;
import com.example.demo.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class Demo3ApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ReptileconfigMapper reptileconfigMapper;

    @Test
    public void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }


    @Test
    public void test1() {
        System.out.println(userMapper.selectById(1));
    }

    @Test
    public void test2() {
        HashMap hashMap = new HashMap();
        System.out.println(userMapper.selectByMap(hashMap));
    }


    @Test
    public void test3() {
        User user = new User();
        user.setAge(13);


        System.out.println(userMapper.selectBatchIds(Collections.emptyList()));
    }

    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper(null, "* from user; delete from user;select name, age");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users.getClass());
        System.out.println(users);
    }

    @Test
    public void test5() {
        UpdateWrapper updateWrapper = new UpdateWrapper();

        updateWrapper.ne("name", "1231");

        int update = userMapper.update(null, updateWrapper);
        System.out.println(update);
    }

    @Test
    public void test6() {
        User user = new User();
        user.setId(1L);
        user.setName("123123");

        userMapper.updateById(user);
    }


    @Test
    public void test7() {
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setAuthor("");
        globalConfig.setBaseColumnList(false);
        globalConfig.setFileOverride(true);
        autoGenerator.setGlobalConfig(globalConfig);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        autoGenerator.setDataSource(dataSourceConfig);


        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setCapitalMode(false);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setLogicDeleteFieldName("del_flag");
        strategyConfig.setEntityTableFieldAnnotationEnable(true);

        autoGenerator.setStrategy(strategyConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setXml("mapper");
        packageConfig.setParent("com.example.demo");
        autoGenerator.setPackageInfo(packageConfig);

        autoGenerator.execute();
    }


    @Test
    public void test8() {
        QueryWrapper<Reptileconfig> queryWrapper = new QueryWrapper();
        List<Map<String, Object>> maps = reptileconfigMapper.selectMaps(queryWrapper);

        System.out.println(maps);
    }


    @Test
    public void test9() {
        QueryWrapper<User> query = Wrappers.query();

        query.ne("name", "abc");

        query.and(userQueryWrapper -> userQueryWrapper.ge("id", 1).likeLeft("email", "test"));

        query.apply("email != {0}", "test2@baomidou.com';select");

        List<User> users = userMapper.selectList(query);

        System.out.println(users);
    }

    @Test
    public void test10() {
        QueryWrapper<User> queryWrapper = new QueryWrapper(null, "ID");

        queryWrapper.select("name");
        //多次设值,只会用最后一个
        queryWrapper.select(User.class, info -> false);

        System.out.println(userMapper.selectList(queryWrapper));
    }

    @Test
    @Rollback
    public void test11() {
        User user = new User();
        user.setName("abc");
        UpdateWrapper updateWrapper = new UpdateWrapper(user);
        userMapper.selectList(updateWrapper);
        userMapper.delete(updateWrapper);
    }


    @Test
    public void test12() {
        System.out.println(userMapper.delete(null));
    }


    @Test
    public void test13() {
        QueryWrapper<User> query = Wrappers.query();
        Map map = new HashMap();
        map.put("id", 1);
        map.put("b", null);

        query.allEq((name, obj) -> {
            System.out.println("name = " + name);
            System.out.println("obj = " + obj);
            return true;
        }, map, false);

        System.out.println(userMapper.selectList(query));
    }

    @Test
    public void test14() {
        LambdaUpdateWrapper<User> objectLambdaUpdateWrapper = Wrappers.lambdaUpdate();
        objectLambdaUpdateWrapper.like(column -> {
            System.out.println("column = " + column);
            return "abc";
        }, 123);

        User user = new User();
        user.setName("张飒");
        userMapper.update(user, objectLambdaUpdateWrapper);
    }

    @Test
    public void test15() {
        QueryWrapper<User> query = Wrappers.query();
        List<User> users = userMapper.selectList(query);
        System.out.println(users);
    }


    @Test
    public void tst16() {
        LambdaQueryWrapper<User> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.like(User::getEmail, "@").orderByDesc(User::getId);

        System.out.println(userMapper.selectList(objectLambdaQueryWrapper));
    }


    @Test
    public void test17() {
        LambdaQueryChainWrapper<User> userLambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(userMapper);

        List<User> users = userLambdaQueryChainWrapper.like(User::getEmail, "a").list();

        users.forEach(System.out::println);
    }

    @Test
    public void test18() {
        User user = new User();
        user.setName("123");
        System.out.println(new LambdaUpdateChainWrapper<>(userMapper).update(user));
    }

    @Test
    public void test19() {
        List<User> all = userMapper.getAll(Wrappers.<User>lambdaQuery().like(User::getId, "2"));
        all.forEach(System.out::println);
    }

    @Test
    public void test20(){
        Page<User> page = new Page<>();
        page.setSize(2);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        List<User> records = userIPage.getRecords();

        records.forEach(System.out::println);
    }

    @Test
    public void test21(){
        Page<User> page = new Page<>();

        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
        List<Map<String, Object>> records = mapIPage.getRecords();

        System.out.println(records);
    }

    @Test
    public void test22() throws JsonProcessingException {
        Page<User> page = new Page<>(3,3000);

        IPage<User> userIPage = userMapper.selectPage(page, null);
        String json = new ObjectMapper().writeValueAsString(userIPage);
        System.out.println(json);
    }


    @Test
    public void test23(){
        //自定义方法只需要第一个参数使用Page就可以1
        Page page = new Page();
        System.out.println(userMapper.selectUserPage(page));
    }

    void printQueryWrapper(QueryWrapper wrapper) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(wrapper));
    }

}
