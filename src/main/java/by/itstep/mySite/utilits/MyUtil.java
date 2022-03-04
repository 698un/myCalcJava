package by.itstep.mySite.utilits;


import java.sql.Time;
import java.util.Date;

public class MyUtil {

    public static boolean equalsSB2(StringBuffer sb1, StringBuffer sb2) {

        if (sb1.length() != sb2.length()) return false;

        //if two StringBuffer is empty then TRUE
        if (sb1.length() == 0) return true;

        //equal every symbol
        for (int i = 0; i < sb1.length(); i++) {
            if (sb1.charAt(i) != sb2.charAt(i)) return false;
        }//next i

        return true;
    }//equalsSB2


    public static void startLog() {

        //String dateStr = new Date().getTime().toString();
        //System.out.println(dateStr);

        String timeStr =String.valueOf( new Date().getTime());
        System.out.println(timeStr);





        System.out.println("08:22:13.164 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating CacheAwareContextLoaderDelegate from class [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]");
        System.out.println("08:22:13.239 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating BootstrapContext using constructor [public org.springframework.test.context.support.DefaultBootstrapContext(java.lang.Class,org.springframework.test.context.CacheAwareContextLoaderDelegate)]");
        System.out.println("08:22:13.319 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating TestContextBootstrapper for test class [by.unil2.itstep.testSring1.controllers.VideoControllerTest] from class [org.springframework.boot.test.context.SpringBootTestContextBootstrapper]");
        System.out.println("08:22:13.378 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Neither @ContextConfiguration nor @ContextHierarchy found for test class [by.unil2.itstep.testSring1.controllers.VideoControllerTest], using SpringBootContextLoader");
        System.out.println("08:22:13.396 [main] DEBUG org.springframework.test.context.support.AbstractContextLoader - Did not detect default resource location for test class [by.unil2.itstep.testSring1.controllers.VideoControllerTest]: class path resource [by/unil2/itstep/testSring1/controllers/VideoControllerTest-context.xml] does not exist");
        System.out.println("08:22:13.396 [main] DEBUG org.springframework.test.context.support.AbstractContextLoader - Did not detect default resource location for test class [by.unil2.itstep.testSring1.controllers.VideoControllerTest]: class path resource [by/unil2/itstep/testSring1/controllers/VideoControllerTestContext.groovy] does not exist");
        System.out.println("08:22:13.396 [main] INFO org.springframework.test.context.support.AbstractContextLoader - Could not detect default resource locations for test class [by.unil2.itstep.testSring1.controllers.VideoControllerTest]: no resource found for suffixes {-context.xml, Context.groovy}.");
        System.out.println("08:22:13.397 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils - Could not detect default configuration classes for test class [by.unil2.itstep.testSring1.controllers.VideoControllerTest]: VideoControllerTest does not declare any static, non-private, non-final, nested classes annotated with @Configuration.");

        System.out.println("08:22:13.635 [main] DEBUG org.springframework.test.context.support.ActiveProfilesUtils - Could not find an 'annotation declaring class' for annotation type [org.springframework.test.context.ActiveProfiles] and class [by.unil2.itstep.testSring1.controllers.VideoControllerTest]");
        System.out.println("08:22:14.354 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Found @SpringBootConfiguration by.unil2.itstep.testSring1.TestSring1Application for test class by.unil2.itstep.testSring1.controllers.VideoControllerTest");
        System.out.println("08:22:14.741 [main] DEBUG org.springframework.boot.test.context.SpringBootTestContextBootstrapper - @TestExecutionListeners is not present for class [by.unil2.itstep.testSring1.controllers.VideoControllerTest]: using defaults.");
        System.out.println("08:22:14.741 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener, org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.event.ApplicationEventsTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener, org.springframework.test.context.event.EventPublishingTestExecutionListener]");
        System.out.println("08:22:14.938 [main] DEBUG org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Skipping candidate TestExecutionListener [org.springframework.test.context.transaction.TransactionalTestExecutionListener] due to a missing dependency. Specify custom listener classes or make the default listener classes and their required dependencies available. Offending class: [org/springframework/transaction/interceptor/TransactionAttributeSource]");
        System.out.println("08:22:14.938 [main] DEBUG org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Skipping candidate TestExecutionListener [org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener] due to a missing dependency. Specify custom listener classes or make the default listener classes and their required dependencies available. Offending class: [org/springframework/transaction/interceptor/TransactionAttribute]");
        System.out.println("08:22:14.939 [main] INFO org.springframework.boot.test.context.SpringBootTestContextBootstrapper - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@7d61eb55, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@222a59e6, org.springframework.test.context.event.ApplicationEventsTestExecutionListener@6c7a164b, org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener@4c2bb6e0, org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener@3e62d773, org.springframework.test.context.support.DirtiesContextTestExecutionListener@4ef74c30, org.springframework.test.context.event.EventPublishingTestExecutionListener@7283d3eb, org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener@638ef7ed, org.springframework.boot.test.autoconfigure.restdocs.RestDocsTestExecutionListener@4bff7da0, org.springframework.boot.test.autoconfigure.web.client.MockRestServiceServerResetTestExecutionListener@3f1d2e23, org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrintOnlyOnFailureTestExecutionListener@536dbea0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverTestExecutionListener@47c81abf, org.springframework.boot.test.autoconfigure.webservices.client.MockWebServiceServerTestExecutionListener@776a6d9b]");
        System.out.println("08:22:14.964 [main] DEBUG org.springframework.test.context.support.AbstractDirtiesContextTestExecutionListener - Before test class: context [DefaultTestContext@2ca923bb testClass = VideoControllerTest, testInstance = [null], testMethod = [null], testException = [null], mergedContextConfiguration = [WebMergedContextConfiguration@13df2a8c testClass = VideoControllerTest, locations = '{}', classes = '{class by.unil2.itstep.testSring1.TestSring1Application}', contextInitializerClasses = '[]', activeProfiles = '{}', propertySourceLocations = '{}', propertySourceProperties = '{org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true}', contextCustomizers = set[org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@2898ac89, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@2638011, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@4470f8a6, org.springframework.boot.test.autoconfigure.actuate.metrics.MetricsExportContextCustomizerFactory$DisableMetricExportContextCustomizer@2f54a33d, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@3c947bc5, org.springframework.boot.test.context.SpringBootTestArgs@1, org.springframework.boot.test.context.SpringBootTestWebEnvironment@2bbf4b8b], resourceBasePath = 'src/main/webapp', contextLoader = 'org.springframework.boot.test.context.SpringBootContextLoader', parent = [null]], attributes = map['org.springframework.test.context.web.ServletTestExecutionListener.activateListener' -> true]], class annotated with @DirtiesContext [false] with mode [null].");
        System.out.println();
        System.out.println("  .   ____          _            __ _ _");
        System.out.println("  /\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\");
        System.out.println(" (( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\");
        System.out.println("  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )");
        System.out.println("   ' |____| .__|_| |_|_| |_\\__, | / / / /");
        System.out.println(" =========|_|==============|___/=/_/_/_/");
        System.out.println(":: Spring Boot ::                (v2.6.3)");
        System.out.println("");
        System.out.println("2022-03-04 08:22:17.670  INFO 15300 --- [           main] b.u.i.t.controllers.VideoControllerTest  : Starting VideoControllerTest using Java 1.8.0_60 on skif16 with PID 15300 (started by 698un in E:\\698un\\ITSTEP_collection\\springTest\\v4\\MyCalcJavaSpring)");
        System.out.println("2022-03-04 08:22:17.672  INFO 15300 --- [           main] b.u.i.t.controllers.VideoControllerTest  : No active profile set, falling back to default profiles: default");
        System.out.println("Read complette.");


        System.out.println("[DEBUG]	2022-03-04 08:22:20.962	ImageRepository	Delete allImages");
        System.out.println("2022-03-04 08:22:22.275  INFO 15300 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]");
        System.out.println("2022-03-04 08:22:23.132  INFO 15300 --- [           main] b.u.i.t.controllers.VideoControllerTest  : Started VideoControllerTest in 7.607 seconds (JVM running for 14.474)");

    }

}
