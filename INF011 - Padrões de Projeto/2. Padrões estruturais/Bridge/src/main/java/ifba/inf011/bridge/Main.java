package ifba.inf011.bridge;

import ifba.inf011.bridge.common.Device;
import ifba.inf011.bridge.common.Remote;
import ifba.inf011.bridge.devices.Radio;
import ifba.inf011.bridge.devices.Tv;
import ifba.inf011.bridge.factory.AutoConfiguringRemote;
import ifba.inf011.bridge.factory.RadioFactory;
import ifba.inf011.bridge.factory.TvFactory;
import ifba.inf011.bridge.remotes.AdvancedRemote;
import ifba.inf011.bridge.remotes.BasicRemote;

// Client
public class Main {

    public static void main(String[] args) {
        Device tv = new Tv();
        Device radio = new Radio();

        System.out.println("=== Basic Remote with TV ===");
        Remote basicRemote = new BasicRemote(tv);
        basicRemote.togglePower();
        basicRemote.volumeUp();
        basicRemote.volumeUp();
        basicRemote.volumeDown();
        tv.printStatus();

        System.out.println("=== Advanced Remote with TV ===");
        AdvancedRemote advancedRemote = new AdvancedRemote(tv);
        advancedRemote.togglePower();
        advancedRemote.mute();
        tv.printStatus();

        System.out.println("=== Switching the implementation at runtime ===");
        advancedRemote.setDevice(radio);
        advancedRemote.togglePower();
        advancedRemote.volumeUp();
        advancedRemote.channelUp();
        advancedRemote.mute();
        radio.printStatus();
        
        System.out.println("\n=== Bridge com instanciação oculta via Abstract Factory ===");

		AutoConfiguringRemote tvRemote = new AutoConfiguringRemote(new TvFactory());
		tvRemote.togglePower();
		tvRemote.printStatus();
		AutoConfiguringRemote radioRemote = new AutoConfiguringRemote(new RadioFactory());
		radioRemote.togglePower();
		radioRemote.printStatus();
    }
}