package ifba.inf011.bridge.factory;

import ifba.inf011.bridge.common.Device;

// Abstraction
public class AutoConfiguringRemote {

    protected Device device;

    public AutoConfiguringRemote(DeviceFactory factory) {
        this.device = factory.createDevice();
    }

    public void togglePower() {

        System.out.println("AutoConfiguringRemote: power toggle");

        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void printStatus() {
        device.printStatus();
    }
}