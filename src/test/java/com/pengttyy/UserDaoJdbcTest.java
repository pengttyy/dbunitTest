package com.pengttyy;

import com.pengttyy.dao.UserDaoJdbcImpl;
import com.pengttyy.dao.Users;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.DatabaseUnitils;
import org.unitils.dbunit.annotation.DataSet;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by pengt on 2017/3/22.
 */
@DataSet
public class UserDaoJdbcTest extends UnitilsJUnit4 {

    @Test
    public void testFindByName() throws Exception {
        DataSource dataSource = DatabaseUnitils.getDataSource();
        Connection connection = dataSource.getConnection();

        UserDaoJdbcImpl ud = new UserDaoJdbcImpl();
        ud.setConnection(connection);

        Users userById = ud.getUserById(1);

    }

    public static void assertUser(Users user) {
        final String USER_FIRST_NAME = "doe";
        final String USER_LAST_NAME = "jdoe";
        final String USER_USERNAME = "jdoe";
        assertNotNull(user);
        assertEquals(USER_FIRST_NAME, user.getFirstName());
        assertEquals(USER_LAST_NAME, user.getLastName());
        assertEquals(USER_USERNAME, user.getUsername());
    }
}
