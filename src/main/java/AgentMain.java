import io.mantisrx.server.agent.AgentV2Main;
import io.mantisrx.common.properties.DefaultMantisPropertiesLoader;
import io.mantisrx.server.agent.config.StaticPropertiesConfigurationFactory;
import java.util.Properties;

public class AgentMain extends AgentV2Main {
    
    public AgentMain(StaticPropertiesConfigurationFactory factory, DefaultMantisPropertiesLoader propertiesLoader) throws Exception {
        super(factory, propertiesLoader);
    }

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.putAll(System.getenv());
            props.putAll(System.getProperties());
            
            StaticPropertiesConfigurationFactory factory = new StaticPropertiesConfigurationFactory(props);
            DefaultMantisPropertiesLoader propertiesLoader = new DefaultMantisPropertiesLoader(props);
            
            AgentMain agent = new AgentMain(factory, propertiesLoader);
            agent.start(); // blocks until shutdown hook (ctrl-c)
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }
}
