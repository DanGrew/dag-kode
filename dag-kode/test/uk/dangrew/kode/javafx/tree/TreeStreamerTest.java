package uk.dangrew.kode.javafx.tree;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javafx.scene.control.TreeItem;
import uk.dangrew.kode.launch.TestApplication;

public class TreeStreamerTest {

   private TreeItem< String > item1;
   private TreeItem< String > item1A;
   private TreeItem< String > item1B;
   private TreeItem< String > item1C;
   private TreeItem< String > item2;
   private TreeItem< String > item2A;
   private TreeItem< String > item2B;
   private TreeItem< String > item2BA;
   private TreeItem< String > item2BAA;
   private TreeItem< String > item3;
   
   private TreeItem< String > root;
   private TreeStreamer systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      root        = new TreeItem<>( "Root" );
      item1      = new TreeItem<>( "item1   ");
      item1A      = new TreeItem<>( "item1A   ");
      item1B      = new TreeItem<>( "item1B   ");
      item1C      = new TreeItem<>( "item1C   ");
      item2     = new TreeItem<>( "item2  ");
      item2A     = new TreeItem<>( "item2A  ");
      item2B     = new TreeItem<>( "item2B  ");
      item2BA    = new TreeItem<>( "item2BA ");
      item2BAA   = new TreeItem<>( "item2BAA");
      item3       = new TreeItem<>( "item3    ");
      
      root.getChildren().add( item1 );
      root.getChildren().add( item2 );
      root.getChildren().add( item3 );
      
      item1.getChildren().add( item1A );
      item1.getChildren().add( item1B );
      item1.getChildren().add( item1C );
      
      item2.getChildren().add( item2A );
      item2.getChildren().add( item2B );
      
      item2B.getChildren().add( item2BA );
      item2BA.getChildren().add( item2BAA );
      
      systemUnderTest = new TreeStreamer();
   }//End Method

   @Test public void shouldStreamAllTreeItems() {
      Stream< TreeItem< String > > result = systemUnderTest.flatten( root );
      List< TreeItem< String > > list = result.collect( Collectors.toList() );
      assertThat( list.get( 0 ), is( root ) );
      assertThat( list.get( 1 ), is( item1 ) );
      assertThat( list.get( 2 ), is( item1A ) );
      assertThat( list.get( 3 ), is( item1B ) );
      assertThat( list.get( 4 ), is( item1C ) );
      assertThat( list.get( 5 ), is( item2 ) );
      assertThat( list.get( 6 ), is( item2A ) );
      assertThat( list.get( 7 ), is( item2B ) );
      assertThat( list.get( 8 ), is( item2BA  ) );
      assertThat( list.get( 9 ), is( item2BAA ) );
      assertThat( list.get( 10 ), is( item3 ) ); 
      assertThat( list, hasSize( 11 ) );
   }//End Method         
   
   @Test public void shouldStreamBranchItems() {
      Stream< TreeItem< String > > result = systemUnderTest.flatten( item2 );
      List< TreeItem< String > > list = result.collect( Collectors.toList() );
      assertThat( list.get( 0 ), is( item2 ) );
      assertThat( list.get( 1 ), is( item2A ) );
      assertThat( list.get( 2 ), is( item2B ) );
      assertThat( list.get( 3 ), is( item2BA  ) );
      assertThat( list.get( 4 ), is( item2BAA ) );
      assertThat( list, hasSize( 5 ) );
   }//End Method  

}//End Class
