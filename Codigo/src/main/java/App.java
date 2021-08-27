import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import java.io.File;

public class App {

    public static void main(String[] args) throws Exception {

        String webappDirLocation = "src/main/resources/";
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");

        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new
                File(webappDirLocation).getAbsolutePath());

        System.out.println("configuring app with basedir: " + new File("./" +
                webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");

        WebResourceRoot resources = new StandardRoot(ctx);

        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",

                additionWebInfClasses.getAbsolutePath(), "/"));

        ctx.setResources(resources);

        tomcat.enableNaming();

        tomcat.getConnector();

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();

    }

}