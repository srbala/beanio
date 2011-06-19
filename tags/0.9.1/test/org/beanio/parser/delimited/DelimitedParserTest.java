/*
 * Copyright 2010-2011 Kevin Seim
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.beanio.parser.delimited;

import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.util.Map;

import org.beanio.*;
import org.beanio.parser.ParserTest;
import org.junit.*;

/**
 * JUnit test cases for delimited streams.
 * 
 * @author Kevin Seim
 * @since 1.0
 */
public class DelimitedParserTest extends ParserTest {

    private StreamFactory factory;

    @Before
    public void setup() throws Exception {
        factory = newStreamFactory("delimited.xml");
    }
    
    @SuppressWarnings("rawtypes")
    @Test
    public void testOptionalFields() {
        BeanReader in = factory.createReader("d1", new InputStreamReader(
            getClass().getResourceAsStream("d1_recordErrors.txt")));
        
        try {
            assertRecordError(in, 1, "record1", "Too few fields 2");
            assertRecordError(in, 2, "record1", "Too many fields 4");
            
            Map map = (Map) in.read();
            assertEquals("value3", map.get("field3"));
            assertNull(map.get("field4"));
        }
        finally {
            in.close();
        }
    }
}