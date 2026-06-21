package ifba.inf011.factory_method.extended_parameterized.creators;

import ifba.inf011.factory_method.extended_parameterized.enums.ExtendedProductType;
import ifba.inf011.factory_method.extended_parameterized.enums.ProductType;
import ifba.inf011.factory_method.extended_parameterized.interfaces.Product;
import ifba.inf011.factory_method.extended_parameterized.products.ProductC;

// Subclasse estende os produtos suportados sem quebrar o Creator base
public class ExtendedCreator extends Creator {

    @Override
    public Product create(ProductType type) {
        if (type == ExtendedProductType.C) {
            return new ProductC();
        }
        // Delega ao pai para A e B: não quebra o existente
        return super.create(type);
    }
}