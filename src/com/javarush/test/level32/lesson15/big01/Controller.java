package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller
{
	private View view;
	private HTMLDocument document;
	private File currentFile;

	public Controller(View view)
	{
		this.view = view;
	}

	public void init(){
		createNewDocument();
	}

	public void exit(){
		System.exit(0);
	}

	public HTMLDocument getDocument()
	{
		return document;
	}

	public static void main(String[] args)
	{
		View view = new View();
		Controller controller = new Controller(view);
		view.setController(controller);
		view.init();
		controller.init();
	}

	public void resetDocument(){
		if (document != null){
			document.removeUndoableEditListener(view.getUndoListener());
		}
		HTMLEditorKit kit = new HTMLEditorKit();
		document = (HTMLDocument) kit.createDefaultDocument();
		document.addUndoableEditListener(view.getUndoListener());
		view.update();
	}

	public void setPlainText(String text){
		resetDocument();
		StringReader reader = new StringReader(text);
		HTMLEditorKit kit = new HTMLEditorKit();
		try
		{
			kit.read(reader, document, 0);
		}
		catch (IOException | BadLocationException e)
		{
			ExceptionHandler.log(e);
		}
	}

	public String getPlainText(){
		StringWriter writer = new StringWriter();
		HTMLEditorKit kit = new HTMLEditorKit();
		try
		{
			kit.write(writer, document, 0, document.getLength());
		}
		catch (IOException | BadLocationException e)
		{
			ExceptionHandler.log(e);
		}
		return writer.toString();
	}

	public void createNewDocument(){
		view.selectHtmlTab();
		resetDocument();
		view.setTitle("HTML редактор");
		view.resetUndo();
		currentFile = null;
	}

	public void openDocument(){
		view.selectHtmlTab();
		JFileChooser chooser = new JFileChooser();

		chooser.setFileFilter(new HTMLFileFilter());
		int val = chooser.showOpenDialog(view);
		if (val == chooser.APPROVE_OPTION)
		{
			currentFile = chooser.getSelectedFile();
			resetDocument();
			view.setTitle(currentFile.getName());
			HTMLEditorKit kit = new HTMLEditorKit();
			view.resetUndo();
			try (FileReader reader = new FileReader(currentFile);)
			{
				kit.read(reader, document, 0);
			}
			catch (IOException | BadLocationException exc)
			{
				ExceptionHandler.log(exc);
			}
		}
	}

	public  void saveDocument(){
		view.selectHtmlTab();
		if (currentFile != null)
		{
			view.setTitle(currentFile.getName());
			HTMLEditorKit kit = new HTMLEditorKit();
			try (FileWriter writer = new FileWriter(currentFile);)
			{
				kit.write(writer, document, 0, document.getLength());
			}
			catch (IOException | BadLocationException exc)
			{
				ExceptionHandler.log(exc);
			}
		}
		else saveDocumentAs();
	}

	public void saveDocumentAs(){
		view.selectHtmlTab();
		JFileChooser chooser = new JFileChooser();

		chooser.setFileFilter(new HTMLFileFilter());
		int val = chooser.showSaveDialog(view);
		if (val == chooser.APPROVE_OPTION)
		{
			currentFile = chooser.getSelectedFile();
			view.setTitle(currentFile.getName());
			HTMLEditorKit kit = new HTMLEditorKit();
			try (FileWriter writer = new FileWriter(currentFile);)
			{
				kit.write(writer, document, 0, document.getLength());
			}
			catch (IOException | BadLocationException exc)
			{
				ExceptionHandler.log(exc);
			}
		}
	}


}
