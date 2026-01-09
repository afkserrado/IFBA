package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IVehicleTypes;

public class VanPlugin extends IVehicleTypes implements IPlugin {
    
    @Override
    public boolean init() {
        return true;
    }
}
