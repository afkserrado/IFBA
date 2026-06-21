package ifba.inf011.bridge.remotes;

import ifba.inf011.bridge.common.Device;

// RefinedAbstraction
public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}