package main.properties;

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

@Resource.File("src/main/resources/base.properties")

public interface MainProperties {
    @Property("baseUrl")
    String baseUrl();

    @Property("user1.login")
    String user1Login();

    @Property("user1.password")
    String user1Password();

    @Property("user1.name")
    String user1Name();

    MainProperties props = PropertyLoader.newInstance().populate(MainProperties.class);
}
