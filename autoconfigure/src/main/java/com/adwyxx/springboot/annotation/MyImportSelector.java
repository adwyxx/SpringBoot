package com.adwyxx.springboot.annotation;

import com.adwyxx.springboot.bean.ImportSelectorBeanFirst;
import com.adwyxx.springboot.bean.ImportSelectorBeanSecond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义ImportSelectors实现类，用于{@link EnableImportSelector} 注解类使用
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-26 17:08
 */
public class MyImportSelector implements ImportSelector {
    private static final Logger logger = LoggerFactory.getLogger(MyImportSelector.class);
    private final SelectorBeanModel DEFAULT_MODEL = SelectorBeanModel.FIRST;
    /**
     * 实现接口方法
     * @param annotationMetadata: 当前标注@Import注解的类的所有注解信息
     * @return String[]: 导入到容器中的组件全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("----------selectImports-----------");
        List<String> imports = new ArrayList<>();
        //获取@Enable注解类的属性信息
        Map<String,Object> properties = annotationMetadata.getAnnotationAttributes(EnableImportSelector.class.getName());

        SelectorBeanModel model = DEFAULT_MODEL;
        System.out.println(properties);
        if(properties==null){
            logger.error("import class has not any properties。"+ annotationMetadata.getClassName());
        }
        else{
            //获取@EnableImportSelector(model="")配置的属性值
            model = (SelectorBeanModel) properties.get("model");
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
