package ifba.inf011.bridge.factory;

import ifba.inf011.bridge.common.Device;
import ifba.inf011.bridge.devices.Radio;

// Concrete Factory
public class RadioFactory implements DeviceFactory {

    @Override
    public Device createDevice() {
        return new Radio();
    }
}