package org.smart4j.framework.transaction;

/**
 * Created by chengwenjie on 2017/2/14.
 *
 * Atomicity   原子是构成物质的最小单位
 * Consistency 比如有两个表, 员工/职位, 在员工表操作添加一个员工, 就要在职位表操作
 * Isolation   一个用户执行 UPDATE, 一个用户执行 DELETE
 * Duration    数据库必须保证有一条数据永久地存放在磁盘中
 *
 * 原子性是基础, 隔离性是手段, 持久性是目的, 这三个都是为了 [一致性] 服务的
 *
 * Dirty Read          脏读, 事务 A 读取了事务 B 未提交的数据, 并在这个基础上又做了其他操作
 * Unrepeatable Read  不可重复读, 事务 A 读取了事务 B 已经提交的更改数据
 * Phantom Read        幻读, 事务 A 读取了事务 B 已经提交的新增的数据
 */
public class ReadmeTest {
}
