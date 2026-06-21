package ifba.inf011.bridge.factory;

import ifba.inf011.bridge.common.Device;
import ifba.inf011.bridge.devices.Tv;

// Concrete Factory
public class TvFactory implements DeviceFactory {

    @Override
    public Device createDevice() {
        return new Tv();
    }
}