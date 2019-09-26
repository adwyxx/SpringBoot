package com.adwyxx.springboot.annotation;

import com.adwyxx.springboot.bean.ImportSelectorBeanFirst;
import com.adwyxx.springboot.bean.ImportSelectorBeanSecond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义ImportSelectors实现类，用于{@link EnableImportSelector} 注解类使用
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 17:08
 */
public class MyImportSelector implements ImportSelector {
    private static final Logger logger = LoggerFactory.getLogger(MyImportSelector.class);
    /**
     * 实现接口方法
     * @param annotationMetadata: 当前标注@Import注解的类的所有注解信息
     * @return String[]: 导入到容器中的组件全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("----------selectImports-----------");
        List<String> imports = new ArrayList<>();
        Class<?> annType = GenericTypeResolver.resolveTypeArgument(this.getClass(), MyImportSelector.class);
        Assert.state(annType != null, "Unresolvable type argument for MyImportSelector");
        //获取当前添加@Import(MyImportSelector.class)注解的类的属性信息
        AnnotationAttributes attributes = new AnnotationAttributes( annotationMetadata.getAnnotationAttributes(EnableImportSelector.class.getName()));
        System.out.println(attributes);
        if(attributes==null){
            logger.error("import class has not any attributes。"+ annotationMetadata.getClassName());
        }
        else{
            //获取@EnableImportSelector(model="")配置的属性值
            SelectorBeanModel model = (SelectorBeanModel) attributes.getEnum("model");
            //根据不同的配置装配不同的Bean
            switch (model)
            {
                case FIRST:
                    imports.add(ImportSelectorBeanFirst.class.getName());
                    break;
                case SECOND:
                    imports.add(ImportSelectorBeanSecond.class.getName());
                    break;
            }

        }

        return StringUtils.toStringArray(imports);
    }
}
