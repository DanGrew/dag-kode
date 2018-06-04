/*
 * ----------------------------------------
 *           Json Upgrading and 
 *        Persistence Architecture
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * {@link TestCommon} test.
 */
public class TestCommonTest {
   
   @Test public void constructorShouldBePrivate() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
     Constructor<TestCommon> constructor = TestCommon.class.getDeclaredConstructor();
     assertThat( Modifier.isPrivate( constructor.getModifiers() ), is( true ) );
     constructor.setAccessible(true);
     assertThat( constructor.newInstance(), is( notNullValue() ) );
   }//End Method

}//End Class
