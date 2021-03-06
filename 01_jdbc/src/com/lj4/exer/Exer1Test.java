package com.lj4.exer;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import static com.lj3.util.JDBCUtils.closeResource;
import static com.lj3.util.JDBCUtils.getConnection;

/**
 * @author luojie
 * @Description
 * @date 2021/10/3 16/37
 */
public class Exer1Test {
    @Test
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入用户名：");
        String name = scanner.next();
        System.out.print("输入邮箱：");
        String email = scanner.next();
        System.out.print("输入生日：");
        String birth = scanner.next();

        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        int insertCount = update(sql, name, email, birth);
        if(insertCount>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    public static int update(String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(conn,ps);
        }
        return 0;
    }
}
