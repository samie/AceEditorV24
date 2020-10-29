package com.hilerio.ace;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Focusable;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.dom.Element;

/**
 * @author: Sergio Alberto Hilerio.
 */
@SuppressWarnings("serial")
@Tag("ace-widget")
@NpmPackage(value = "@granite-elements/ace-widget", version = "2.2.7-b1")
@JsModule("./@granite-elements/ace-widget/ace-widget.js")
public class AceEditor extends AbstractSinglePropertyField<AceEditor, String> implements Focusable<AceEditor> {

	@Id("editor")
	private Div editor;

	private AceTheme editorTheme = AceTheme.eclipse;
	private AceMode editorMode = AceMode.javascript;
	private String fontSize = "14px";
	private boolean softTabs = true;
	private int tabSize = 4;
	private boolean wrap = false;
	private int minLines = 15;
	private int maxLines = Integer.MAX_VALUE;
	private String basePath = "";
	private boolean autoComplete = false;
	private boolean initialFocus = false;
	private String placeHolder = "";
	private boolean readOnly = false;
	private boolean printMargin = false;
	private boolean showInvisibles = false;
	private boolean showGutter = true;
	private boolean hightlightActiveLine = true;
	private boolean displayIndentGuides = false;
	private boolean highlightSelectedWord = false;
	private int[] selection = new int[] { 0, 0 };
	private int cursorPosition = 0;
	private boolean useWorker = false;
	private boolean liveAutocompletion = false;
	private boolean enableSnippets = true;
	private String[] customAutoCompletion = new String[0];

	public AceEditor() {
		super("value", "", false);

		setWidth("100%");
		setHeight("300px");
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		addListener(BlurChanged.class, this::updateText);
	}

	// Updates the Text after the Blur event has been fired (Keyboard lost focus)
	private void updateText(BlurChanged event) {
		setValue(event.getValue());
	}

	/**
	 * Sets the mode(language) of the editor.
	 *
	 * @param mode mode(language)
	 */
	public void setMode(AceMode mode) {
		getElement().setAttribute("mode", mode.toString());
		this.editorMode = mode;
	}

	/**
	 * Returns the current set mode for the editor.
	 * 
	 * @return AceMode mode
	 */
	public AceMode getMode() {
		return this.editorMode;
	}

	/**
	 * Sets the theme (style) of the editor.
	 *
	 * @param theme theme(style)
	 */
	public void setTheme(AceTheme theme) {
		getElement().setAttribute("theme", theme.toString());
	}

	/**
	 * Returns the current set theme for the editor.
	 * 
	 * @return AceTheme theme
	 */
	public AceTheme getTheme() {
		return this.editorTheme;
	}

	/**
	 * Cleans the value contained in the editor.
	 */
	public void clear() {
		getElement().setProperty("value", "");
	}

	/**
	 * Sets value for the editor.
	 * 
	 * @param value String
	 */
	public void setValue(String value) {
		getElement().setProperty("value", value);
	}

	/**
	 * Sets font-size for the editor in pixels.
	 * 
	 * @param value int
	 */
	public void setFontSize(int value) {
		getElement().setAttribute("font-size", value + "px");
		this.fontSize = value + "px";
	}

	/**
	 * Returns the current set font-size of the editor in pixels.
	 * 
	 * @return String font-size
	 */
	public String getFontSize() {
		return this.fontSize;
	}

	/**
	 * Sets softtabs for the editor.
	 * 
	 * @param value boolean
	 */
	public void setSofttabs(boolean value) {
		getElement().setAttribute("softtabs", value);
		this.softTabs = value;
	}

	/**
	 * Returns if softtabs are currently enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getSofttabs() {
		return this.softTabs;
	}

	/**
	 * Sets tab-size for the editor.
	 * 
	 * @param value int
	 */
	public void setTabSize(int value) {
		getElement().setAttribute("tab-size", String.valueOf(value));
		this.tabSize = value;
	}

	/**
	 * Returns the current set tab-size for the editor.
	 * 
	 * @return int tab-size
	 */
	public int getTabSize() {
		return this.tabSize;
	}

	/**
	 * Sets wrap for the editor.
	 * 
	 * @param wrap boolean
	 */
	public void setWrap(boolean wrap) {
		getElement().setAttribute("wrap", wrap);
		this.wrap = wrap;
	}

	/**
	 * Returns if wrap is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getWrap() {
		return this.wrap;
	}

	/**
	 * Sets AutoComplete for the editor.
	 * 
	 * @param value boolean
	 */
	public void setAutoComplete(boolean value) {
		getElement().setProperty("enableAutocompletion", value);
		this.autoComplete = value;
	}

	/**
	 * Returns if autocomplete is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getAutoComplete() {
		return this.autoComplete;
	}

	/**
	 * Sets minlines for the editor.
	 * 
	 * @param minlines int
	 */
	public void setMinlines(int minlines) {
		getElement().setAttribute("minlines", String.valueOf(minlines));
		this.minLines = minlines;
	}

	/**
	 * Returns the minimum set lines for the editor.
	 * 
	 * @return int minlines
	 */
	public int getMinLines() {
		return this.minLines;
	}

	/**
	 * Sets maxlines for the editor.
	 * 
	 * @param maxlines int
	 */
	public void setMaxlines(int maxlines) {
		getElement().setAttribute("maxlines", String.valueOf(maxlines));
		this.maxLines = maxlines;
	}

	/**
	 * Return the maximum lines set for the editor.
	 * 
	 * @return int maxlines
	 */
	public int getMaxLines() {
		return this.maxLines;
	}

	/**
	 * Sets initialFocus for the editor.
	 * 
	 * @param value boolean
	 */
	public void setInitialFocus(boolean value) {
		getElement().setAttribute("initialFocus", value);
		this.initialFocus = value;
	}

	/**
	 * Returns if initial focus is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getInitialFocus() {
		return this.initialFocus;
	}

	/**
	 * Sets placeholder for the editor.
	 * 
	 * @param value String
	 */
	public void setPlaceholder(String value) {
		getElement().setAttribute("placeholder", value);
		this.placeHolder = value;
	}

	/**
	 * Returns the placeholder set for the editor.
	 * 
	 * @return String placeholder
	 */
	public String getPlaceholder() {
		return this.placeHolder;
	}

	/**
	 * Sets readOnly for the editor.
	 * 
	 * @param value boolean
	 */
	public void setReadOnly(boolean value) {
		getElement().setAttribute("readonly", value);
		this.readOnly = value;
	}

	/**
	 * Returns if readOnly is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getReadOnly() {
		return this.readOnly;
	}

	/**
	 * Sets height in px/pixel or percent.
	 * 
	 * @param height String
	 */
	public void setHeight(String height) {
		getElement().getStyle().set("height", height);
	};

	/**
	 * Returns the height set for the editor in px/pixel or percent.
	 * 
	 * @return String height
	 */
	public String getHeight() {
		return getElement().getStyle().get("height");
	}

	/**
	 * Sets max-height in px/pixel or percent.
	 * 
	 * @param height String
	 */
	public void setMaxHeight(String height) {
		getElement().getStyle().set("max-height", height);
	};

	/**
	 * Returns the max-height set for the editor in px/pixel or percent.
	 * 
	 * @return String max-height
	 */
	public String getMaxHeight() {
		return getElement().getStyle().get("max-height");
	}

	/**
	 * Sets min-height in px/pixel or percent.
	 * 
	 * @param height String
	 */
	public void setMinHeight(String height) {
		getElement().getStyle().set("min-height", height);
	};

	/**
	 * Returns the min-height set for the editor in px/pixel or percent.
	 * 
	 * @return String min-height
	 */
	public String getMinHeight() {
		return getElement().getStyle().get("min-height");
	}

	/**
	 * Sets width in px/pixel or percent.
	 * 
	 * @param width String
	 */
	public void setWidth(String width) {
		getElement().getStyle().set("width", width);
	};

	/**
	 * Returns the width set for the editor in px/pixel or percent.
	 * 
	 * @return String width
	 */
	public String getWidth() {
		return getElement().getStyle().get("width");
	}

	/**
	 * Sets max-width in px/pixel or percent.
	 * 
	 * @param width String
	 */
	public void setMaxWidth(String width) {
		getElement().getStyle().set("max-width", width);
	};

	/**
	 * Returns the max-width set for the editor in px/pixel or percent.
	 * 
	 * @return String max-width
	 */
	public String getMaxWidth() {
		return getElement().getStyle().get("max-width");
	}

	/**
	 * Sets min-width in px/pixel or percent.
	 * 
	 * @param width String
	 */
	public void setMinWidth(String width) {
		getElement().getStyle().set("min-width", width);
	};

	/**
	 * Returns the min-width set for the editor in px/pixel or percent.
	 * 
	 * @return String min-width
	 */
	public String getMinWidth() {
		return getElement().getStyle().get("min-width");
	}

	/**
	 * Sets BasePath / BaseUrl.
	 * 
	 * @param baseurl String
	 */
	public void setBasePath(String baseurl) {
		getElement().setProperty("baseUrl", baseurl);
		this.basePath = baseurl;

	};

	/**
	 * Return the current BasePath / BaseUrl.
	 * 
	 * @return String BaseUrl
	 */
	public String getBasePath() {
		return this.basePath;
	}

	/**
	 * Sets showPrintMargin for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowPrintMargin(boolean value) {
		getElement().setProperty("showPrintMargin", value);
		this.printMargin = value;
	}

	/**
	 * Returns if showPrintMargin is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getShowPrintMargin() {
		return this.printMargin;
	}

	/**
	 * Sets showInvisibles for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowInvisibles(boolean value) {
		getElement().setProperty("showInvisibles", value);
		this.showInvisibles = value;
	}

	/**
	 * Returns if showInvisibles is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getShowInvisibles() {
		return this.showInvisibles;
	}

	/**
	 * Sets showGutter for the editor.
	 * 
	 * @param value boolean
	 */
	public void setShowGutter(boolean value) {
		getElement().setProperty("showGutter", value);
		this.showGutter = value;
	}

	/**
	 * Returns if showGutter is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getShowGutter() {
		return this.showGutter;
	}

	/**
	 * Sets highlightActiveLine for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightActiveLine(boolean value) {
		getElement().setProperty("highlightActiveLine", value);
		this.hightlightActiveLine = value;
	}

	/**
	 * Returns if hightlightActiveLine is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getHightlightActiveLine() {
		return this.hightlightActiveLine;
	}

	/**
	 * Sets displayIndentGuides for the editor.
	 * 
	 * @param value boolean
	 */
	public void setDisplayIndentGuides(boolean value) {
		getElement().setProperty("displayIndentGuides", value);
		this.displayIndentGuides = value;
	}

	/**
	 * Returns if displayIndentGuides is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getDisplayIndentGuides() {
		return this.displayIndentGuides;
	}

	/**
	 * Sets highlightSelectedWord for the editor.
	 * 
	 * @param value boolean
	 */
	public void setHighlightSelectedWord(boolean value) {
		getElement().setProperty("highlightSelectedWord", value);
		this.highlightSelectedWord = value;
	}

	/**
	 * Returns if hightlightSelectedWord is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getHightlightSelectedWord() {
		return this.highlightSelectedWord;
	}

	/**
	 * Sets selection for the editor.
	 * 
	 * @param from int
	 * @param to   int
	 */
	public void setSelection(int from, int to) {
		if (to < 0) {
			to = 0;
		}
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (from > getValue().length() - 1) {
			from = getValue().length() - 1;
		}
		getElement().setProperty("selection", from + "|" + to);
		this.selection = new int[] { from, to };
		this.cursorPosition = to;
	}

	/**
	 * Sets selection for the editor and optionally also sets the focus.
	 * 
	 * @param from  int
	 * @param to    int
	 * @param focus boolean
	 */
	public void setSelection(int from, int to, boolean focus) {
		if (to < 0) {
			to = 0;
		}
		if (to < from) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		if (from > getValue().length() - 1) {
			from = getValue().length() - 1;
		}
		getElement().setProperty("selection", from + "|" + to);
		this.selection = new int[] { from, to };
		this.cursorPosition = to;
		if (focus) {
			this.focus();
		}
	}

	/**
	 * Returns an int array of the current selection where the first index
	 * represents "from" and the second index "to".
	 * 
	 * @return int[] selection
	 */
	public int[] getSelection() {
		return this.selection;
	}

	/**
	 * Sets useWorker for the editor.
	 * 
	 * @param value boolean
	 */
	public void setUseWorker(boolean value) {
		getElement().setProperty("useWorker", value);
		this.useWorker = value;
	}

	/**
	 * Returns if useWorker is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getUseWorker() {
		return this.useWorker;
	}

	/**
	 * Sets cursorPosition for the editor.
	 * 
	 * @param pos int
	 */
	public void setCursorPosition(int pos) {
		if (pos > getValue().length() - 1) {
			pos = getValue().length() - 1;
		}
		getElement().setProperty("selection", pos + "|" + pos);
		this.cursorPosition = pos;
	}

	/**
	 * Sets cursorPosition for the editor and optionally also sets the focus.
	 * 
	 * @param pos   int
	 * @param focus boolean
	 */
	public void setCursorPosition(int pos, boolean focus) {
		if (pos > getValue().length() - 1) {
			pos = getValue().length() - 1;
		}
		getElement().setProperty("selection", pos + "|" + pos);
		this.cursorPosition = pos;
		if (focus) {
			this.focus();
		}
	}

	/**
	 * Returns the current set cursor position in the editor.
	 * 
	 * @return int position
	 */
	public int getCursorPosition() {
		return this.cursorPosition;
	}

	/**
	 * Sets liveAutocompletion for the editor.
	 * 
	 * @param value boolean
	 */
	public void setLiveAutocompletion(boolean value) {
		getElement().setProperty("enableLiveAutocompletion", value);
		this.liveAutocompletion = value;
	}

	/**
	 * Returns if live autocompletion is enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getLiveAutocompletion() {
		return this.liveAutocompletion;
	}

	/**
	 * Sets enableSnippets for the editor.
	 * 
	 * @param value boolean
	 */
	public void setEnableSnippets(boolean value) {
		getElement().setProperty("enableSnippets", value);
		this.enableSnippets = value;
	}

	/**
	 * Returns if snippets are enabled/disabled for the editor.
	 * 
	 * @return boolean enabled/disabled
	 */
	public boolean getEnableSnippets() {
		return this.enableSnippets;
	}

	/**
	 * Sets a custom autocompletion list for the editor.
	 * 
	 * @param wordList String[]
	 */
	public void setCustomAutoCompletion(String[] wordList) {
		if (wordList.length == 0) {
			this.customAutoCompletion = new String[0];
			return;
		}
		getElement().setProperty("customAutoCompletion", String.join(",", wordList));
		this.customAutoCompletion = wordList;
	}

	/**
	 * Returns a String array of the current custom autocompletion for the editor.
	 * 
	 * @return String[] customAutoCompletion
	 */
	public String[] getCustomAutoCompletion() {
		return this.customAutoCompletion;
	}

	/**
	 * Removes the custom autocompletion list set with setCustomAutoCompletiton()
	 * and replaces it with the default one.
	 */
	public void disableCustomAutoCompletion() {
		getElement().setProperty("customAutoCompletion", "");
	}

	/**
	 * Adds text to a specific position of the editor.
	 * 
	 * @param text     String
	 * @param position int
	 */
	public void addTextAtPosition(String text, int position) {
		String currentVal = this.getValue();
		StringBuilder newVal = new StringBuilder();
		if (position > currentVal.length()) {
			position = currentVal.length() - 1;
		}
		for (int x = 0; x < currentVal.length(); x++) {
			newVal.append(currentVal.charAt(x));
			if (x == position) {
				newVal.append(text);
			}
		}
		this.setValue(newVal.toString());
	}

	/**
	 * Adds text at the current position of the editor. First Priority: If there is
	 * a selection, the selection will be replaced. Second Priority: If the cursor
	 * position has been set, the text will be added at the position of the cursor.
	 * Third/Last Priority: If no text is selected and the cursor has not been set,
	 * the text will be added to the end of the text.
	 * 
	 * @param text String
	 */
	public void addTextAtCurrentPosition(String text) {
		String currentVal = this.getValue();
		if (this.selection != new int[] { 0, 0 }) {
			int from = this.selection[0];
			int to = this.selection[1];
			String toReplace = currentVal.substring(from, to);
			String newVal = currentVal.replace(toReplace, text);
			this.setValue(newVal);
			this.selection = new int[] { 0, 0 };
		} else if (this.cursorPosition != 0) {
			addTextAtPosition(text, this.cursorPosition);
			this.cursorPosition = 0;
		} else {
			addTextAtPosition(text, currentVal.length() - 1);
		}
		this.blur(); // To force the exchange between client and server.
	}
}