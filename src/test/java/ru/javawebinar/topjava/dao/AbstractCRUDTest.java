package ru.javawebinar.topjava.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * This class is all about simplifying testing CRUD-operations. To make these
 * tests it is should be passed with a test object. That object will be tested
 * through all the CRUD tests. To make tests you should extend this class and
 * implement methods that are accessed to database. <b>IMPORTANT: you should
 * make your derived class to be Parameterized. To do that you should add data
 * provider method into your class. F.e. <b> <tt>
 *
 * @Parameters<b> public static Collection<Object[]> data1() {<b> Object[][]
 *                data = {{some data}};<b>
 *
 *                return Arrays.asList(data);<b> }<b> </tt>For more information
 *                look at the documentation of JUnit4
 *
 * @param <T>
 *            the class that will try to pass CRUD-tests
 *
 * @author ctapobep
 */
@RunWith(Parameterized.class)
public abstract class AbstractCRUDTest<T> {

    private T testObject;

    /**
     * Creates class with specified test object. This object will try to pass
     * CRUD-test.
     *
     * @param testObject
     *            object to test
     */
    public AbstractCRUDTest(T testObject) {
        this.testObject = testObject;
    }

    /**
     * Tests the CRUD(create, read, update, delete) operations. <b>First, it
     * inserts the test object into database. Second, it retrieves the saved
     * data and compares with test object. Third, it makes update to database
     * and repeats the second step. Then it deletes test object from database
     * and tries to obtain it from database. If the retrieved object is not
     * null, then the object was not deleted from database and assertion would
     * fail.
     */
    @Test
    public void testCRUD() {
        insert(testObject);
        T selected = select(testObject);
        compare(selected, testObject);

        update(testObject);
        selected = select(testObject);
        compare(selected, testObject);

        delete(testObject);
        selected = select(testObject);
        assertNull(selected);
    }

    /**
     * Deletes specified object from database.
     *
     * @param testObject
     *            object to delete from database
     */
    public abstract void delete(T testObject);

    /**
     * Retrieves specified object from database. Makes the copy of specified
     * object.
     *
     * @param testObject
     *            the object to get copy from database
     * @return copy of the specified object, that is retrieved from database
     */
    public abstract T select(T testObject);

    /**
     * Updates specified object within the database.
     *
     * @param testObject
     *            object to update
     */
    public abstract void update(T testObject);

    /**
     * Inserts specified object within the database.
     *
     * @param testObject
     *            object to insert into database
     */
    public abstract void insert(T testObject);

    /**
     * Compares two methods for the equality.
     *
     * @param selected
     *            the selected entity
     * @param testObject
     *            the object that is tested
     */
    public void compare(T selected, T testObject) {
        assertNotNull(selected);
        assertTrue(selected.equals(testObject));
    }

    /**
     * Gets the object that is tested, which is specified in the constructor of
     * the class.
     *
     * @return the object that is tested, which is specified in the constructor
     *         of the class
     */
    public T getTestObject() {
        return testObject;
    }

    /**
     * The method, that prepares connection to database.
     */
    @Before
    public abstract void prepareDatabaseConnection();

}
