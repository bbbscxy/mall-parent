package com.bbbscxy.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.bbbscxy.common.entity.BaseEntity;

import java.util.ArrayList;

public class CodeGenerate {

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("d:\\generate");
        gc.setAuthor("bbbscxy");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(false);
        generator.setGlobalConfig(gc);

        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriverName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://47.93.185.126:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDbType(DbType.MYSQL);
        generator.setDataSource(ds);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.bbbscxy.modules");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        generator.setPackageInfo(pc);

        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setSuperEntityClass(BaseEntity.class);
        sc.setEntityLombokModel(true);
        sc.setLogicDeleteFieldName("del_flag");
        TableFill create = new TableFill("create_time", FieldFill.INSERT);
        TableFill update = new TableFill("update_time", FieldFill.UPDATE);
        ArrayList<TableFill> fills = new ArrayList<TableFill>();
        fills.add(create);
        fills.add(update);
        sc.setTableFillList(fills);
        generator.setStrategy(sc);

        generator.execute();
    }
}
