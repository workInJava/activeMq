package com.test.Introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.mq.bean.UserInfo;

/**
 * PropertyDescriptor类表示JavaBean类通过存储器导出一个属性。主要方法： 1.
 * getPropertyType()，获得属性的Class对象; 2.
 * getReadMethod()，获得用于读取属性值的方法；getWriteMethod()，获得用于写入属性值的方法; 3.
 * hashCode()，获取对象的哈希值; 4. setReadMethod(Method readMethod)，设置用于读取属性值的方法; 5.
 * setWriteMethod(Method writeMethod)，设置用于写入属性值的方法。
 *
 */

public class BeanInfoUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(BeanInfoUtil.class);

	public static void setProperty(UserInfo userInfo, String userName) throws Exception {
		PropertyDescriptor propDes = new PropertyDescriptor(userName, UserInfo.class);
		Method methodSetUserName = propDes.getWriteMethod();
		methodSetUserName.invoke(userInfo, "whm");
		LOGGER.info(getProperty(userInfo,userName));
	}

	public static String getProperty(UserInfo userInfo, String userName) throws Exception {
		PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, UserInfo.class);
		Method methodGetUserName = proDescriptor.getReadMethod();
		Object objUserName = methodGetUserName.invoke(userInfo);
		return objUserName.toString();
	}
	
	public static void main(String[] args) throws Exception{
		UserInfo userInfo = new UserInfo();
		setPropertyByIntrospector(userInfo,"userName");
		//setProperty(userInfo,"userName");
	}
	
	public static void setPropertyByIntrospector(UserInfo userInfo,String userName) throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
		PropertyDescriptor[] proDes = beanInfo.getPropertyDescriptors();
		if(proDes!=null&&proDes.length>0){
			for(int i=0;i<proDes.length;i++){
				if(userName.equals(proDes[i].getName())){
					proDes[i].getWriteMethod().invoke(userInfo, "www");
					LOGGER.info(proDes[i].getReadMethod().invoke(userInfo).toString());
					break;
				}
			}
		}
	}
}