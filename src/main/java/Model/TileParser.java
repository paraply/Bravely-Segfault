package Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philip on 2016-04-21.
 */
public class TileParser extends DefaultHandler {
    boolean bName = false;
    boolean bGraphics = false;
    boolean bSolid = false;

    int id;
    String name;
    File filepath;
    boolean solidness = false;

    List<Tile> tileList = new ArrayList<Tile>();

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("tile")) {

            id = Integer.parseInt(attributes.getValue("id"));
        } else if (qName.equalsIgnoreCase("name")) {
            System.out.println("Name found: " + localName);
            bName = true;
        } else if (qName.equalsIgnoreCase("graphics")) {
            System.out.println("Filepath found: " + localName);
            bGraphics = true;
        } else if (qName.equalsIgnoreCase("solidness")) {
            System.out.print("Solidness found: " + localName);
            bSolid = true;
        }
    }

    @Override
    public void characters (char ch[], int start, int length) throws SAXException {
        if (bName) {
            this.name = new String(ch, start, length);
            bName = false;
        } else if (bGraphics) {
            this.filepath = new File(new String (ch, start, length));
            bGraphics = false;
        } else if (bSolid) {
            if ("solid".equalsIgnoreCase(new String(ch, start, length))) {
                this.solidness = true;
            }
        }
    }

    /**
     * Should be called after parsing
     * @return
     */
    public void createTile () {
        try {
            tileList.add(new Tile(name, solidness, filepath));
        } catch (NullPointerException e) {
            System.err.println("Something was missing when creating a tile");
        }
    }
}
