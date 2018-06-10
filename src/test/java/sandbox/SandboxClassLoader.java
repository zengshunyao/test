package sandbox;


import sandbox.util.ConstantParam;

import java.io.File;
import java.io.FileInputStream;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * [重写的类加载器]
 * 	沙箱程序类加载器,可根据指定路径加载制定类class文件.
 *
 *  [说明]
 *  仅包内可见
 * @project_name：test
 * @author zengshunyao
 * @date 2018/3/5 23:53
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class SandboxClassLoader extends ClassLoader{
	/**默认classPath*/
	private String _classPath;

	/**
	 * 	构造函数
	 * @param classPath 类加载器默认classPath
	 * */
	public SandboxClassLoader(String classPath) {
		this._classPath = classPath;
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
       return this.loadClass(_classPath, className);
	}

	/**
	 * 	更改类加载器加载类的classpath,在制定路径下加载制定的类class文件
	 * @param		classPath	要加载的类路径
	 * @param		className 	要加载的类名
	 * 				最为限定,只能加载不含包的类.
	 * */
	protected Class<?> loadClass(String classPath, String className) throws ClassNotFoundException{
        if(className.indexOf('.') >= 0) {
            throw new ClassNotFoundException(className);
        }

        File classFile = new File(classPath + ConstantParam.SEPARATOR + className + ".class");
        byte[] mainClass = new byte[(int) classFile.length()];
        try {
            FileInputStream in = new FileInputStream(classFile);
            in.read(mainClass);
            in.close();
        } catch (Exception e) {
            //e.printStackTrace();
            throw new ClassNotFoundException(className);
        }

		return super.defineClass(className, mainClass, 0, mainClass.length);
	}

	/**
	 * 	获取classPath
	 * @return String		classPath
	 * */
	public String getClassPath(){
		return _classPath + ConstantParam.SEPARATOR;
	}
}