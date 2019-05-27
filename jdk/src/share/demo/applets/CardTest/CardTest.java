/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


@SuppressWarnings("serial")
final class CardPanel extends Panel {

    ActionListener listener;

    Panel create(LayoutManager layout) {
        Button b = null;
        Panel p = new Panel();

        p.setLayout(layout);

        b = new Button("one");
        b.addActionListener(listener);
        p.add("North", b);

        b = new Button("two");
        b.addActionListener(listener);
        p.add("West", b);

        b = new Button("three");
        b.addActionListener(listener);
        p.add("South", b);

        b = new Button("four");
        b.addActionListener(listener);
        p.add("East", b);

        b = new Button("five");
        b.addActionListener(listener);
        p.add("Center", b);

        b = new Button("six");
        b.addActionListener(listener);
        p.add("Center", b);

        return p;
    }

    CardPanel(ActionListener actionListener) {
        listener = actionListener;
        setLayout(new CardLayout());
        add("one", create(new FlowLayout()));
        add("two", create(new BorderLayout()));
        add("three", create(new GridLayout(2, 2)));
        add("four", create(new BorderLayout(10, 10)));
        add("five", create(new FlowLayout(FlowLayout.LEFT, 10, 10)));
        add("six", create(new GridLayout(2, 2, 10, 10)));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }
}


@SuppressWarnings("serial")
public class CardTest extends Applet
        implements ActionListener,
        ItemListener {

    CardPanel cards;

    @SuppressWarnings("LeakingThisInConstructor")
    public CardTest() {
        setLayout(new BorderLayout());
        add("Center", cards = new CardPanel(this));
        Panel p = new Panel();
        p.setLayout(new FlowLayout());
        add("South", p);

        Button b = new Button("first");
        b.addActionListener(this);
        p.add(b);

        b = new Button("next");
        b.addActionListener(this);
        p.add(b);

        b = new Button("previous");
        b.addActionListener(this);
        p.add(b);

        b = new Button("last");
        b.addActionListener(this);
        p.add(b);

        Choice c = new Choice();
        c.addItem("one");
        c.addItem("two");
        c.addItem("three");
        c.addItem("four");
        c.addItem("five");
        c.addItem("six");
        c.addItemListener(this);
        p.add(c);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ((CardLayout) cards.getLayout()).show(cards,
                (String) (e.getItem()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();

        if ("first".equals(arg)) {
            ((CardLayout) cards.getLayout()).first(cards);
        } else if ("next".equals(arg)) {
            ((CardLayout) cards.getLayout()).next(cards);
        } else if ("previous".equals(arg)) {
            ((CardLayout) cards.getLayout()).previous(cards);
        } else if ("last".equals(arg)) {
            ((CardLayout) cards.getLayout()).last(cards);
        } else {
            ((CardLayout) cards.getLayout()).show(cards, arg);
        }
    }

    public static void main(String args[]) {
        Frame f = new Frame("CardTest");
        CardTest cardTest = new CardTest();
        cardTest.init();
        cardTest.start();

        f.add("Center", cardTest);
        f.setSize(300, 300);
        f.setVisible(true);
    }

    @Override
    public String getAppletInfo() {
        return "Demonstrates the different types of layout managers.";
    }
}
