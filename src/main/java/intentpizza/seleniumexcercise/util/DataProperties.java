package intentpizza.seleniumexcercise.util;



import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DataProperties {

	public static Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();
		URL props = ClassLoader
				.getSystemResource("intentpizza.properties");

		try {
			PROPERTIES.load(props.openStream());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		return PROPERTIES.getProperty(key);
	}

	public static String getLocation(String file) {
		return ClassLoader.getSystemResource(file).getPath().toString();

	}
	
	
	
}