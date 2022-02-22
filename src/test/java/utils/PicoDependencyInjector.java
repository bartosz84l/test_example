package utils;

import config.DriverFactory;
import cucumber.runtime.java.picocontainer.PicoFactory;

public class PicoDependencyInjector extends PicoFactory {
    public PicoDependencyInjector() {
        addClass(DriverFactory.class);
    }
}
