package com.ldm.myssm.trans;

import com.ldm.myssm.basedao.ConnUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */

public class TransactionManager {


    //开启事务
    public static void beginTrans() throws SQLException {

        ConnUtils.getConn().setAutoCommit(false);

    }

    //提交事务
    public static void commit() throws SQLException{
        Connection conn = ConnUtils.getConn();
        conn.commit();
        ConnUtils.closeConn();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtils.getConn();
        conn.rollback();
        ConnUtils.closeConn();
    }
}
