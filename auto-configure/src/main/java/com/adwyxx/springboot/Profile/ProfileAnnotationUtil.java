package com.adwyxx.springboot.Profile;

/**
 * TODO..
 *
 * @author: Leo.Wang, adwyxx@qq.com
 * @date: 2019-09-27 16:15
 */
public class ProfileAnnotationUtil {
    public static ProfileAnnotation getProfileAnnotation (Class<?> type){
        ProfileAnnotation annotation = type.getAnnotation(ProfileAnnotation.class);
        return annotation;
    }
}
