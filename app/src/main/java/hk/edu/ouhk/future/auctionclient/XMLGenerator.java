package hk.edu.ouhk.future.auctionclient;

import java.util.Hashtable;

/**
 * XMLGenerator generates XML requests from easy API calling.
 */

public class XMLGenerator {
    String output;
    String sessionId;

    public XMLGenerator() {
        output = "";
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addElement(String element) {
        output += "\t<" + element + " />\n";
    }

    public void addElement(String element, Hashtable<String, String> attr) {
        output += "\t<" + element + " ";
        for (String key : attr.keySet()) {
            output += key + "=\"" + attr.get(key) + "\" ";
        }
        output += "/>\n";
    }

    public String getOutput() {
        if (sessionId != null) {
            return "<message sessionId=\"" + sessionId + "\">\n" + output + "\n</message>";
        } else {
            return "<message>\n" + output + "</message>";
        }
    }
}
