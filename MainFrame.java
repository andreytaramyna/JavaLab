import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private Double memoryValue = 0.0;

    JButton buttonMemoryPlus = new JButton("M+");
    JButton buttonMemoryClear = new JButton("MC");

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldM;
    private JTextField textFieldResult;

    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    public Double calculate1(Double x, Double y, Double z) {
        return Math.sin(Math.log(y) + Math.sin(Math.PI * Math.pow(y, 2))) * Math.pow(Math.pow(x, 2) + Math.sin(z) + Math.exp(Math.cos(z)), 0.25);
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (Math.exp(0.5 * x)) / Math.sqrt(z + y) * Math.pow(Math.log(x), z);
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));

        int strutWidth = 20;

        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(strutWidth));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(strutWidth));

        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(strutWidth));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(strutWidth));

        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(strutWidth));
        hboxVariables.add(textFieldZ);

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonMemoryPlus = new JButton("M+");
        textFieldM = new JTextField("0", 20);
        textFieldM.setMaximumSize(textFieldM.getPreferredSize());
        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double newValue = Double.parseDouble(textFieldResult.getText()) + memoryValue;
                    memoryValue = newValue;
                    textFieldM.setText(memoryValue.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoryValue = 0.0;
                textFieldM.setText(memoryValue.toString());
            }
        });

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonMemoryPlus);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(textFieldM);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMemoryClear);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);

        Box hboxMemory = Box.createHorizontalBox();
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(buttonCalc);
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(buttonReset);
        hboxMemory.add(Box.createHorizontalGlue());

        contentBox.add(hboxMemory);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


