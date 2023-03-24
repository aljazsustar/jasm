package com.example.parser.types.attributes.util.types.annotations;

import com.example.parser.interfaces.ClassFileElement;

import java.util.ArrayList;
import java.util.List;

public class ElementValuePairs implements ClassFileElement {

    private List<ElementValuePair> elementValuePairs;

    public ElementValuePairs() {
        this.elementValuePairs = new ArrayList<>();
    }

    public ElementValuePairs(Integer elementValuePairsLength) {
        this.elementValuePairs = new ArrayList<>(elementValuePairsLength);
    }

    public List<ElementValuePair> getElementValuePairs() {
        return elementValuePairs;
    }

    public void addElementValuePair(ElementValuePair elementValuePair) {
        this.elementValuePairs.add(elementValuePair);
    }

    @Override
    public List<Byte> toHex() {
        return this.elementValuePairs
                .stream()
                .map(ClassFileElement::toHex)
                .reduce(new ArrayList<>(), (acc, el) -> {
                    acc.addAll(el);
                    return acc;
                });
    }
}
