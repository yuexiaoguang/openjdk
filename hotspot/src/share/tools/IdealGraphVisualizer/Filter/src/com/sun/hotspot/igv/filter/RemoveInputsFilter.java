package com.sun.hotspot.igv.filter;

import com.sun.hotspot.igv.graph.Connection;
import com.sun.hotspot.igv.graph.Diagram;
import com.sun.hotspot.igv.graph.Figure;
import com.sun.hotspot.igv.graph.InputSlot;
import com.sun.hotspot.igv.graph.OutputSlot;
import com.sun.hotspot.igv.graph.Selector;
import java.util.ArrayList;
import java.util.List;

public class RemoveInputsFilter extends AbstractFilter {

    private List<RemoveInputsRule> rules;
    private String name;

    public RemoveInputsFilter(String name) {
        this.name = name;
        rules = new ArrayList<RemoveInputsRule>();
    }

    public String getName() {
        return name;
    }

    public void apply(Diagram diagram) {

        for (RemoveInputsRule r : rules) {

            List<Figure> list = r.getSelector().selected(diagram);
            for (Figure f : list) {
                int z = 0;
                List<InputSlot> last = new ArrayList<InputSlot>();
                for (InputSlot is : f.getInputSlots()) {
                    if (z >= r.getStartingIndex() && z <= r.getEndIndex() && is.getConnections().size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        List<Connection> conns = is.getConnections();
                        for (int i = 0; i < conns.size(); i++) {
                            Connection c = conns.get(i);
                            OutputSlot os = c.getOutputSlot();
                            Figure pred = os.getFigure();
                            if (i != 0) {
                                sb.append("<BR>");
                            }
                            sb.append(pred.getLines()[0]);
                        }
                        is.removeAllConnections();
                        is.setShortName("X");
                        is.setName(sb.toString());
                        last.add(is);
                    } else {
                        last.clear();
                    }
                    z++;
                }

                if (last.size() > 3) {
                    InputSlot first = last.get(0);
                    first.setShortName("XX");

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < last.size(); i++) {
                        InputSlot is2 = last.get(i);
                        if (i != 0) {
                            sb.append("<BR>");
                        }
                        sb.append(is2.getName());
                    }

                    first.setName(sb.toString());

                    for (int i = 1; i < last.size(); i++) {
                        f.removeSlot(last.get(i));
                    }
                }
            }
        }
    }

    public void addRule(RemoveInputsRule rule) {
        rules.add(rule);
    }

    public static class RemoveInputsRule {

        private Selector selector;
        private int startingIndex;
        private int endIndex;

        public RemoveInputsRule(Selector selector) {
            this(selector, 0);
        }

        public RemoveInputsRule(Selector selector, int startIndex) {
            this(selector, startIndex, Integer.MAX_VALUE);
        }

        public RemoveInputsRule(Selector selector, int startIndex, int endIndex) {
            this.startingIndex = startIndex;
            this.endIndex = endIndex;
            this.selector = selector;
        }

        public int getStartingIndex() {
            return startingIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public Selector getSelector() {
            return selector;
        }
    }
}
