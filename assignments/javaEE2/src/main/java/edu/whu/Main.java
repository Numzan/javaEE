package edu.whu;
import edu.whu.MyClass.initMethod;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException {
        // Class c1 = Class.forName("edu.whu.MyClass");
        // c1.getDeclaredMethod("Init",void.class);
        Properties prop = new Properties();
        try(InputStream input = Main.class.getResourceAsStream("/myapp.properties")) {
            if (input == null) {
                return;
            }
            prop.load(input);
            System.out.println(prop.getProperty("bootstrapClass"));
            Class c1 = Class.forName("edu.whu.MyClass."+prop.getProperty("bootstrapClass"));
            Object instance = c1.newInstance();
            Method init = c1.getDeclaredMethod("Init", void.class);
            init.invoke(instance);
        }catch (IOException e) {
            System.out.println("Load properties error!");
        }catch (ClassNotFoundException e) {
            System.out.println("Load Class error!");
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }catch (NoSuchMethodException e){
            throw new RuntimeException(e);
        }


    }
}
