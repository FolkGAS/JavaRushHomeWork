package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener
{

	public View(){
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception exc){
			ExceptionHandler.log(exc);
		}
	}

	private JTabbedPane tabbedPane = new JTabbedPane();
	private JTextPane htmlTextPane = new JTextPane();
	private JEditorPane plainTextPane = new JEditorPane();
	private UndoManager undoManager = new UndoManager();
	private UndoListener undoListener = new UndoListener(undoManager);

	private Controller controller;

	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		String command = actionEvent.getActionCommand();
		switch (command){
			case "Новый" :
				controller.createNewDocument();
				break;
			case "Открыть" :
				controller.openDocument();
				break;
			case "Сохранить" :
				controller.saveDocument();
				break;
			case "Сохранить как..." :
				controller.saveDocumentAs();
				break;
			case "Выход" :
				controller.exit();
				break;
			case "О программе" :
				showAbout();
				break;
		}
	}

	public Controller getController()
	{
		return controller;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	public void init(){
		initGui();
		FrameListener listener = new FrameListener(this);
		addWindowListener(listener);
		setVisible(true);
	}

	public void exit(){
		controller.exit();
	}

	public void initMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		MenuHelper.initFileMenu(this, menuBar);
		MenuHelper.initEditMenu(this, menuBar);
		MenuHelper.initStyleMenu(this, menuBar);
		MenuHelper.initAlignMenu(this, menuBar);
		MenuHelper.initColorMenu(this, menuBar);
		MenuHelper.initFontMenu(this, menuBar);
		MenuHelper.initHelpMenu(this, menuBar);
		getContentPane().add(menuBar, BorderLayout.NORTH);
	}

	public void initEditor(){
		htmlTextPane.setContentType("text/html");
		JScrollPane jScrollPaneHtml = new JScrollPane(htmlTextPane);
		JScrollPane jScrollPaneText = new JScrollPane(plainTextPane);
		tabbedPane.add("HTML", jScrollPaneHtml);
		tabbedPane.add("Текст", jScrollPaneText);
		tabbedPane.setPreferredSize(new Dimension(800, 800));
		TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
		tabbedPane.addChangeListener(tabbedPaneChangeListener);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}


	public void initGui(){
		initMenuBar();
		initEditor();
		pack();
	}

	public void selectedTabChanged(){
		int tabIndex = tabbedPane.getSelectedIndex();
		if (tabIndex == 0)
			controller.setPlainText(plainTextPane.getText());
		if (tabIndex == 1)
			plainTextPane.setText(controller.getPlainText());
		resetUndo();
	}

	public boolean canUndo(){
		return undoManager.canUndo();
	}

	public boolean canRedo(){
		return undoManager.canRedo();
	}

	public void resetUndo(){
		undoManager.discardAllEdits();
	}

	public void undo(){
		undoManager.undo();
	}

	public void redo(){
		undoManager.redo();
	}

	public UndoListener getUndoListener()
	{
		return undoListener;
	}

	public boolean isHtmlTabSelected(){
		return tabbedPane.getSelectedIndex() == 0;
	}

	public void selectHtmlTab(){
		tabbedPane.setSelectedIndex(0);
		resetUndo();
	}

	public void update(){
		htmlTextPane.setDocument(controller.getDocument());
	}

	public void showAbout(){
		JOptionPane.showMessageDialog(getContentPane(), "swi-i-i-i-ing", "About" , JOptionPane.INFORMATION_MESSAGE);
	}
}
