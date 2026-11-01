package nuevodesing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editor extends JFrame {
    private JPanel contentpane;
    private JTextField textField2;
    private JTextField textField1;
    private JComboBox<Genre> comboBox1;
    private JCheckBox Desabilitado;
    private JButton saveButton;
    private JButton cancelButton;

    private Library library;
    private SaveButtonListener saveButtonListener;

    public editor() {
        library = new Library();

        setTitle("Book Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentpane);
        pack();

        populateGenreComboBox();

        setLocationRelativeTo(null);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelChanges();
            }
        });

        setVisible(true);
    }

    public void setSaveButtonListener(SaveButtonListener listener) {
        this.saveButtonListener = listener;
    }

    private void saveChanges() {
        String bookName = textField2.getText();
        String authorName = textField1.getText();
        Genre genre = (Genre) comboBox1.getSelectedItem();
        boolean isTaken = Desabilitado.isSelected();

        Author author = new Author(authorName, "");
        Book book = new Book(author, genre, null, bookName);
        book.setTaken(isTaken);

        if (saveButtonListener != null) {
            saveButtonListener.onSaveClicked(book);
        }

        textField2.setText("");
        textField1.setText("");
        comboBox1.setSelectedIndex(0);
        Desabilitado.setSelected(false);
    }

    private void cancelChanges() {
        textField2.setText("");
        textField1.setText("");
        comboBox1.setSelectedIndex(0);
        Desabilitado.setSelected(false);
    }

    private void populateGenreComboBox() {
        DefaultComboBoxModel<Genre> comboBoxModel = (DefaultComboBoxModel<Genre>) comboBox1.getModel();
        for (Genre genre : Genre.values()) {
            comboBoxModel.addElement(genre);
        }
    }
}