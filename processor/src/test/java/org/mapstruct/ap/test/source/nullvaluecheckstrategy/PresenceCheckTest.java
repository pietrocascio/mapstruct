/**
 *  Copyright 2012-2016 Gunnar Morling (http://www.gunnarmorling.de/)
 *  and/or other contributors as indicated by the @authors tag. See the
 *  copyright.txt file in the distribution for a full listing of all
 *  contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mapstruct.ap.test.source.nullvaluecheckstrategy;


import static org.fest.assertions.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;

/**
 *
 * @author Sjaak Derksen
 */
@WithClasses({
    RockFestivalMapper.class,
    RockFestivalSource.class,
    RockFestivalTarget.class,
    RockFestivalMapperConfig.class,
    RockFestivalMapperWithConfig.class,
    RockFestivalMapperOveridingConfig.class,
    Stage.class
})
@RunWith(AnnotationProcessorTestRunner.class)
public class PresenceCheckTest {

    @Test
    public void testCallingMappingMethodWithNullSource() {

        RockFestivalSource source =  new RockFestivalSource();
        RockFestivalTarget target = RockFestivalMapper.INSTANCE.map( source );
        assertThat( target.getStage() ).isNull();

        source.setArtistName( "New Order" );
        target = RockFestivalMapper.INSTANCE.map( source );
        assertThat( target.getStage() ).isEqualTo( Stage.THE_BARN );

   }

    @Test
    public void testCallingMappingMethodWithNullSourceWithConfig() {

        RockFestivalSource source =  new RockFestivalSource();
        RockFestivalTarget target = RockFestivalMapperWithConfig.INSTANCE.map( source );
        assertThat( target.getStage() ).isNull();

        source.setArtistName( "New Order" );
        target = RockFestivalMapperWithConfig.INSTANCE.map( source );
        assertThat( target.getStage() ).isEqualTo( Stage.THE_BARN );

   }

    @Test( expected = IllegalArgumentException.class )
    public void testCallingMappingMethodWithNullSourceOveridingConfig() {

        RockFestivalSource source =  new RockFestivalSource();
        RockFestivalMapperOveridingConfig.INSTANCE.map( source );
   }
}
