import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;


public class DatabaseURLPlaceholderConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

	public DatabaseURLPlaceholderConfigurer() {
		super();
		setIgnoreUnresolvablePlaceholders(true);
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	String key = "database";
	String env = "DATABASE_URL";
	
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
		if(!placeholder.startsWith(key)) {
			return null;
		}
		String envValue = System.getenv(env);
		if(envValue==null) {
			return null;
		}
		try {
			URI uri = new URI(envValue);
			if(placeholder.endsWith(".url")) {
				return  (uri.getScheme().equals("postgres") ? "jdbc:postgresql" : uri.getScheme())
						+ "://"
						+ uri.getHost() +
						(uri.getPort()!=-1 ? ":"+uri.getPort() : "")+
						uri.getPath()+
						(uri.getQuery()!=null ? "?"+uri.getQuery(): "");
			} else if(placeholder.endsWith(".username")) {
				return uri.getUserInfo().split(":",-1)[0];
			} else if(placeholder.endsWith(".password")) {
				return uri.getUserInfo().split(":",-1)[1];
			} else if(placeholder.endsWith(".driverClassName")) {
				if(uri.getScheme().equals("postgres") || uri.getScheme().equals("jdbc:postgresql")) {
					return "org.postgresql.Driver";
				} else {
					return null;
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
}
