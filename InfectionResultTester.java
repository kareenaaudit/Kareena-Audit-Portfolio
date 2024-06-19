import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * The test class InfectionResultsTester.
 *
 * @author  David J. Barnes
 * @version 2021.11.10
 */
public class InfectionResultsTester
{
    // Catch the output from System.out and System.err.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    // Error report, if any.
    private String report;
    private static ReflectionTester reflect;

    // The name of the class being tested.
    private static final String CLASS_NAME = "InfectionResults";
    // The fields: name and type.
    // NB: For this assignment, we cannot mandate the fields.
    private static final String[] fields = {
            //"F:tests:java.util.ArrayList",
        };
    // The methods: name, return type, params.
    private static final String[] methods = {
            "M:addTest:void:InfectionTest",
            "M:getNumberOfTests:int:",
            "M:list:void:",
            "M:setStatus:void:java.lang.String:boolean",
            "M:findTest:InfectionTest:java.lang.String",
            "M:getPositiveTests:java.util.HashSet:",
            "M:removeUnknownStatus:java.util.LinkedList:",
        };

    private Random rand;
    private InfectionResults lab;
    private List<InfectionTest> shadow;
    private int numTests;
    private List<String> randomIDs;

    /**
     * Default constructor for test class InfectionResultsTester
     */
    public InfectionResultsTester()
    {
        rand = new Random();
        // NB: For non-randomized testing, uncomment the following line.
        // rand = new Random(12345);

        // The remaining fields will be initialised properly
        // in the setup method before each test.
        shadow = new Vector<>();
        randomIDs = new Vector<>();
    }

    @BeforeAll
    public static void reflectionTest()
    {
        try {
            reflect = new ReflectionTester(CLASS_NAME);
        }
        catch(ClassNotFoundException ex) {
            // Class name not found.
            assertTrue(false, "The class must be called " + CLASS_NAME + ".");
        }
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        report = null;

        lab = new InfectionResults();
        numTests = 0;
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        if(report != null) {
            System.err.println(report);
            report = null;
        }

        System.setOut(originalOut);
        System.setErr(originalErr);
        shadow.clear();
        randomIDs.clear();
    }

    /**
     * Test for the correct class name.
     */
    @Test
    public void testClassName()
    {
        assertTrue(reflect != null, "The class must be called " + CLASS_NAME);
    }

    /**
     * Test for the correct fields.
     */
    @Test
    public void testFields()
    {
        if(reflect != null) {
            if(fields.length > 0) {
                String fieldReport = reflect.testFields(fields);
                if(! fieldReport.isEmpty()) {
                    fail(fieldReport);
                }
            }
        }
        else {
            fail("Requirements for the fields cannot be checked until the name of the class is correct.");
        }
    }

    /**
     * Test for the correct methods.
     */
    @Test
    public void testMethods()
    {
        if(reflect != null) {
            String methodReport = reflect.testMethods(methods);
            if(! methodReport.isEmpty()) {
                fail(methodReport);
            }
        }
        else {
            fail("Requirements for the methods cannot be checked until the name of the class is correct.");
        }
    }

    /**
     * Test that addTest appears to be working.
     */
    @Test
    public void testAdd()
    {
        assertEquals(0, lab.getNumberOfTests());
        makeTest("t1");
        assertEquals(1, lab.getNumberOfTests());
        makeTest("t2");
        assertEquals(2, lab.getNumberOfTests());
    }

    /**
     * Test that getNumberOfTests returns the correct
     * result for the initial setup.
     */
    @Test
    public void testGetNumberOfTests()
    {
        makeSample();
        assertEquals(numTests, lab.getNumberOfTests());
    }

    /**
     * Test the output from the list method.
     */
    @Test
    public void testList()
    {
        // Format the expected output via a PrintStream to avoid
        // OS incompatibilities with line endings.
        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        PrintStream expectedPrinter = new PrintStream(expected);
        InfectionTest t1 = makeTest();
        InfectionTest t2 = makeTest();
        InfectionTest t3 = makeTest();
        expectedPrinter.println(t1.getDetails());
        expectedPrinter.println(t2.getDetails());
        expectedPrinter.println(t3.getDetails());
        lab.list();
        assertEquals(expected.toString().trim(), outContent.toString().trim());
    }

    /**
     * Test that the status of a test is set to false.
     */
    @Test
    public void testSetStatusFalse()
    {
        makeSample();
        InfectionTest sample = shadow.get(shadow.size() / 2);
        lab.setStatus(sample.getID(), false);
        assertFalse(sample.isPositive(), "The status should be negative.");
    }

    /**
     * Test that the status of a test is set to true.
     */
    @Test
    public void testSetStatusTrue()
    {
        makeSample();
        InfectionTest sample = shadow.get(shadow.size() / 2);
        lab.setStatus(sample.getID(), true);
        assertTrue(sample.isPositive(), "The status should be positive.");
    }

    /**
     * Test that there is no change if the ID does not exist.
     */
    @Test
    public void testSetStatusMissing()
    {
        makeSample();
        InfectionTest example = shadow.get(shadow.size() / 2);
        lab.setStatus(example.getID() + "ABC", true);
        shadow.stream()
              .forEach(sample -> assertThrows(IllegalStateException.class, 
                       () -> sample.isPositive(),
                       "The status is not valid."));
    }

    /**
     * Find the test with the first ID.
     */
    @Test
    public void testFindTestFirst()
    {
        makeSample();
        InfectionTest sample = shadow.get(0);
        InfectionTest result = lab.findTest(sample.getID());
        assertTrue(result != null,
            "The test with ID " + sample.getID() + " should have been returned.");
        assertEquals(sample.getID(), result.getID(),
            "The test with ID " + sample.getID() + " should have been returned.");
    }

    /**
     * Find the test with the first ID.
     */
    @Test
    public void testFindTestLast()
    {
        makeSample();
        InfectionTest sample = shadow.get(shadow.size() - 1);
        InfectionTest result = lab.findTest(sample.getID());
        String expected = "The test with ID " + sample.getID() + " should have been returned.";
        assertNotNull(result, expected);
        assertEquals(sample.getID(), result.getID(), expected);
    }

    /**
     * Find the test with the middle ID.
     */
    @Test
    public void testFindTestMiddle()
    {
        makeSample();
        InfectionTest sample = shadow.get(shadow.size() / 2);
        InfectionTest result = lab.findTest(sample.getID());
        String expected = "The test with ID " + sample.getID() + " should have been returned.";
        assertNotNull(result, expected);
        assertEquals(sample.getID(), result.getID(), expected);
    }

    /**
     * Find the test with the first ID.
     */
    @Test
    public void testFindTestMissing()
    {
        makeSample();
        InfectionTest sample = shadow.get(shadow.size() - 1);
        InfectionTest result = lab.findTest(sample.getID() + "X");
        assertNull(result, "null should have been returned as there is no matching ID.");
    }

    /**
     * Test getPositiveTests with a single positive test.
     */
    @Test
    public void testGetPositiveTestsOne()
    {
        InfectionTest t = makeTest();
        t.setStatus(true);
        Set<InfectionTest> result = lab.getPositiveTests();
        assertEquals(1, result.size(), "One test should have been returned.");
        assertTrue(result.contains(t), "The set did not contain the correct test.");
    }

    /**
     * Test getPositiveTests with no positive tests.
     */
    @Test
    public void testGetPositiveTestsNone()
    {
        makeTest().setStatus(false);
        makeTest().setStatus(false);
        makeTest().setStatus(false);
        Set<InfectionTest> result = lab.getPositiveTests();
        assertEquals(0, result.size(), "No tests should have been returned.");
    }

    /**
     * Test getPositiveTests with all positive tests.
     */
    @Test
    public void testGetPositiveTestsAll()
    {
        shadow.stream()
        .forEach(t -> t.setStatus(true));
        Set<InfectionTest> result = lab.getPositiveTests();
        assertEquals(shadow.size(), result.size(), 
            shadow.size() + " tests should have been returned.");
    }

    /**
     * Test getPositiveTests with tests that do not
     * have their status set.
     */
    @Test
    public void testGetPositiveTestsUnknown()
    {
        makeTest();
        makeTest();
        makeTest();
        Set<InfectionTest> result = lab.getPositiveTests();
        assertEquals(0, result.size(), "No tests should have been returned.");
    }

    /**
     * Test removeUnknownStatus with a mix of known and unknown.
     */
    @Test
    public void testRemoveUnknownStatusMix()
    {
        makeTest().setStatus(false);
        InfectionTest t1 = makeTest();
        makeTest().setStatus(true);
        InfectionTest t2 = makeTest();
        try {
            List<InfectionTest> result = lab.removeUnknownStatus();
            assertEquals(2, result.size(), "Two tests should have been returned.");
            assertTrue(result.contains(t1) && result.contains(t2), 
                "The returned list does not contain the correct test objects.");
            assertEquals(2, lab.getNumberOfTests(), "There should be two tests left.");
        }
        catch(ConcurrentModificationException ex) {
            fail("You cannot use a for-each loop to remove objects.");
        }
    }

    /**
     * Test removeUnknownStatus by calling it twice.
     */
    @Test
    public void testRemoveUnknownStatusTwice()
    {
        makeTest().setStatus(false);
        InfectionTest t1 = makeTest();
        makeTest().setStatus(true);
        InfectionTest t2 = makeTest();
        try {
            List<InfectionTest> result = lab.removeUnknownStatus();
            assertEquals(2, result.size(), "Two tests should have been returned the first time.");
            assertTrue(result.contains(t1) && result.contains(t2), 
                "The returned list does not contain the correct test objects.");
            assertEquals(2, lab.getNumberOfTests(),
                "There should be two tests left.");
            result = lab.removeUnknownStatus();
            assertTrue(result.isEmpty(), "No tests should have been returned the second time.");
            assertEquals(2, lab.getNumberOfTests(),
                "There should be two tests left.");
        }
        catch(ConcurrentModificationException ex) {
            fail("You cannot use a for-each loop to remove objects.");
        }
    }

    /**
     * Test removeUnknownStatus with all statuses known.
     */
    @Test
    public void testRemoveUnknownStatusNone()
    {
        makeTest().setStatus(false);
        makeTest().setStatus(true);
        makeTest().setStatus(true);
        makeTest().setStatus(false);
        try {
            List<InfectionTest> result = lab.removeUnknownStatus();
            assertTrue(result.isEmpty(), "No tests should have been returned.");
            assertEquals(4, lab.getNumberOfTests(),
                "There should be four tests left.");
        }
        catch(ConcurrentModificationException ex) {
            fail("You cannot use a for-each loop to remove objects.");
        }
    }

    /**
     * Test removeUnknownStatus with all statuses unknown.
     */
    @Test
    public void testRemoveUnknownStatusAll()
    {
        makeTest();
        makeTest();
        makeTest();
        makeTest();
        try {
            List<InfectionTest> result = lab.removeUnknownStatus();
            assertEquals(4, result.size(), "All the tests should have been returned.");
            assertEquals(0, lab.getNumberOfTests(), "There should be no tests left.");
        }
        catch(ConcurrentModificationException ex) {
            fail("You cannot use a for-each loop to remove objects.");
        }
    }

    /**
     * Make a sample of tests with random IDs.
     */
    private void makeSample()
    {
        numTests = 8 + rand.nextInt(5);
        int base = 10 + rand.nextInt(100);
        for(int t = 1; t <= numTests; t++) {
            makeTest(makeRandomID());
        }
    }

    /**
     * Create a random test ID.
     * @return the ID
     */
    private String makeRandomID()
    {
        String id;
        do {
            id = "t: " + rand.nextInt(100);
        }
        while(randomIDs.contains(id));
        randomIDs.add(id);
        return id;
    }

    /**
     * Make a new InfectionTest with a random ID.
     * @return the test
     */
    private InfectionTest makeTest()
    {
        return makeTest(makeRandomID());
    }

    /**
     * Make a new InfectionTest with the given ID.
     * @param id The ID of the test.
     * @return the test
     */
    private InfectionTest makeTest(String id)
    {
        InfectionTest t = new InfectionTest(id);
        shadow.add(t);
        lab.addTest(t);
        return t;
    }

    /**
     * Make a new InfectionTest with the given id and status.
     * @param id The ID of the test.
     * @param status The status of the test.
     */
    private InfectionTest makeTest(String id, boolean status)
    {
        InfectionTest t = makeTest(id);
        t.setStatus(status);
        return t;
    }

    /**
     * Driver for Java assignment analyser.
     * Requirements for fields and methods are defined here.
     * 
     * @author djb
     */
    private static class ReflectionTester {
        private Reflector reflector;

        /**
         * Create a tester for the given class.
         * @param subject The name of the class to be tested.
         */
        public ReflectionTester(String subject)
        throws ClassNotFoundException
        {
            reflector = new Reflector(subject);
        }

        /**
         * Test the fields given their descriptions.
         * Return a report of any issues found.
         * @param fields Descriptions of the required fields.
         * @return A report string, or blank if there are no issues.
         */
        public String testFields(String[] fields)
        {
            try {
                RequiredField[] requiredFields = setupFields(fields);
                return reflector.checkFields(requiredFields);
            }
            catch(ClassNotFoundException ex) {
                return null;
            }
        }

        /**
         * Test the public methods given their descriptions.
         * Return a report of any issues found.
         * @param methods Descriptions of the public methods.
         * @return A report string, or blank if there are no issues.
         */
        public String testMethods(String[] methods)
        {
            try {
                RequiredMethod[] requiredMethods = setupMethods(methods);
                return reflector.checkMethods(requiredMethods);
            }
            catch(ClassNotFoundException ex) {
                return null;
            }

        }

        /**
         * Set up the required fields from the name and type info.
         * @param fields F:name:type for each field.
         * @return an array of RequiredField details.
         * @throws ClassNotFoundException if a type cannot be found.
         */
        private static RequiredField[] setupFields(String[] fields) throws ClassNotFoundException
        {
            RequiredField[] requiredFields = new RequiredField[fields.length];
            for(int i = 0; i < fields.length; i++) {
                String f = fields[i];
                String[] parts = f.split(":");
                assert parts[0].equals("F");
                assert parts.length == 3;
                requiredFields[i] = new RequiredField(parts[1], getClass(parts[2]));
            }
            return requiredFields;
        }

        /**
         * Set up the required methods from the given info.
         * @param fields M:name:return-type:param-types for each field.
         * @return an array of RequiredMethod details.
         * @throws ClassNotFoundException if a type cannot be found.
         */
        private static RequiredMethod[] setupMethods(String[] methods) 
        throws ClassNotFoundException
        {
            RequiredMethod[] requiredMethods = new RequiredMethod[methods.length];
            for(int i = 0; i < methods.length; i++) {
                String m = methods[i];
                String[] parts = m.split(":");
                assert parts[0].equals("M");
                assert parts.length >= 3 : m + " " + parts.length;
                Class[] params = new Class[parts.length - 3];
                for(int p = 3; p < parts.length; p++) {
                    params[p - 3] = getClass(parts[p]);
                }
                requiredMethods[i] = 
                new RequiredMethod(parts[1],
                    getClass(parts[2]), 
                    params);
            }
            return requiredMethods;

        }

        /**
         * Get the Class object corresponding to the given type name.
         * @param typeName The type to be found.
         * @return The Class object for the type.
         * @throws ClassNotFoundException if the Class cannot be found.
         */
        private static Class getClass(String typeName) throws ClassNotFoundException
        {
            switch(typeName) {
                case "boolean": return boolean.class;
                case "byte": return byte.class;
                case "double": return double.class;
                case "float": return float.class;
                case "int": return int.class;
                case "long": return long.class;
                case "short": return short.class;
                case "void": return void.class;
                default:
                    return Class.forName(typeName);
            }
        }
        /**
         * Details of a required field: its name and type.
         * @author djb
         */
        private static class RequiredField {
            private final String name;
            private final Class type;

            /**
             * Store details of a required field.
             * @param name The name of the field.
             * @param type The type of the field.
             */
            public RequiredField(String name, Class type) {
                this.name = name;
                this.type = type;
            }

            /**
             * Get the field's name.
             *
             * @return The field's name.
             */
            public String getName() {
                return name;
            }

            /**
             * Get the field's type.
             *
             * @return The field's type.
             */
            public Class getType() {
                return type;
            }

        }
        /**
         * Details of a required method: its name, return type and parameters.
         * @author djb
         */
        private static class RequiredMethod {
            private final String name;
            private final Class returnType;
            private final Class params[];

            /**
             * Store details of a required public method.
             * @param name The method's name.
             * @param returnType The return type.
             * @param params The method's parameters.
             */
            public RequiredMethod(String name, Class returnType, Class[] params) {
                this.name = name;
                this.returnType = returnType;
                this.params = params;
            }

            /**
             * Get the method's name.
             * @return The method's name.
             */
            public String getName() {
                return name;
            }

            /**
             * Get the method's return type.
             * @return The method's return type.
             */
            public Class getReturnType() {
                return returnType;
            }

            /**
             * Get the method's parameters.
             * @return The method's parameters.
             */
            public Class[] getParams() {
                return params;
            }

        }
        /**
         * Check that the required fields and methods in a class
         * are present.
         * @author djb
         */
        public class Reflector {
            private final Field[] fields;
            private final Method[] methods;
            private final Class targetClass;  

            /**
             * Create a Reflector for the given class.
             * @param className the class to be analysed.
             * @throws ClassNotFoundException if the class cannot be found.
             */
            public Reflector(String className) throws ClassNotFoundException {
                this.targetClass = Class.forName(className);
                this.fields = targetClass.getDeclaredFields();
                this.methods = targetClass.getDeclaredMethods();
            }

            /**
             * Check the required fields.
             * @param requiredFields The required fields.
             */
            public String checkFields(RequiredField[] requiredFields)
            {
                StringBuilder builder = new StringBuilder();
                boolean allOk = true;
                // Whether each has been found in the class.
                boolean[] matched = new boolean[fields.length];

                for(RequiredField f : requiredFields) {
                    // Look for a matching name.
                    String name = f.getName();
                    int index = -1;
                    for(int i = 0; i < matched.length && index < 0; i++) {
                        if(name.equals(fields[i].getName())) {
                            matched[i] = true;
                            index = i;
                        }
                    }
                    if(index >= 0) {
                        // Check that the field is private and
                        // has the correct type.
                        Field field = fields[index];
                        int modifiers = field.getModifiers();
                        if((modifiers & Modifier.PRIVATE) != Modifier.PRIVATE) {
                            builder.append(name + " has not been defined as private.")
                            .append(' ');
                            allOk = false;
                        }
                        if(field.getType() != f.getType()) {
                            builder.append(name + " does not have the correct type.")
                            .append(' ');
                            allOk = false;
                        }
                    }
                    else {
                        builder.append(name + " not defined as a field.")
                        .append(' ');
                        allOk = false;
                    }
                }
                // Report on any missing fields.
                for(int i = 0; i < matched.length; i++) {
                    if(! matched[i]) {
                        builder.append(fields[i].getName() + 
                            " does not match a required field.")
                        .append(' ');
                        allOk = false;
                    }
                }
                return builder.toString().trim();
            }

            /**
             * Check the required methods.
             * @param requiredMethods The required methods.
             */
            public String checkMethods(RequiredMethod[] requiredMethods)
            {
                StringBuilder builder = new StringBuilder();
                boolean allOk = true;
                // Whether each has been found in the class.
                boolean[] matched = new boolean[methods.length];

                for(RequiredMethod m : requiredMethods) {
                    // Look for a matching name.
                    String name = m.getName();
                    int index = -1;
                    for(int i = 0; i < matched.length && index < 0; i++) {
                        if(name.equals(methods[i].getName())) {
                            matched[i] = true;
                            index = i;
                        }
                    }
                    if(index >= 0) {
                        // Check that the method is public,
                        // has the correct return type and params.
                        Method method = methods[index];
                        int modifiers = method.getModifiers();
                        if((modifiers & Modifier.PUBLIC) != Modifier.PUBLIC) {
                            builder.append(name + " has not been defined as public.")
                            .append(' ');
                        }
                        if(method.getReturnType() != m.getReturnType()) {
                            builder.append(name + " does not have the correct return type.")
                            .append(' ');
                            allOk = false;
                        }
                        Class[] params = method.getParameterTypes();
                        Class[] requiredParams = m.getParams();
                        if(params.length == requiredParams.length) {
                            boolean ok = true;
                            for(int p = 0; p < requiredParams.length; p++) {
                                if(params[p] != requiredParams[p]) {
                                    ok = false;
                                }
                            }
                            if(!ok) {
                                builder.append("The parameters of " + name + 
                                    " do not all have the correct type.")
                                .append(' ');
                                allOk = false;
                            }
                        }
                        else {
                            builder.append(name + " should have " +
                                requiredParams.length + " instead of " +
                                params.length + " parameters.")
                            .append(' ');
                            allOk = false;
                        }
                    }
                    else {
                        builder.append(name + " not defined as a method.")
                        .append(' ');
                        allOk = false;
                    }
                }
                // Report on any additional methods.
                for(int i = 0; i < matched.length; i++) {
                    if(! matched[i]) {
                        Method method = methods[i];
                        int modifiers = method.getModifiers();
                        String name = methods[i].getName();
                        if(name.equals("toString") || name.equals("equals") ||
                        name.equals("hashCode")) {
                            // Let those pass.
                        }
                        else if((modifiers & Modifier.PUBLIC) == Modifier.PUBLIC) {
                            builder.append(name + 
                                " does not match a required public method.")
                            .append(' ');
                            allOk = false;
                        }
                    }
                }
                return builder.toString().trim();
            }

        }
    }
}
