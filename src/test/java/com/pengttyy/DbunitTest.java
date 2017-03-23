package com.pengttyy;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

/**
 * Created by pengt on 2017/3/22.
 */
@DataSet
public class DbunitTest extends UnitilsJUnit4 {

    @Test
    public void testFindByName() throws Exception {
        System.out.println("test");
    }
}
