package View;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by paraply on 2016-04-19.
 */
public class Render {
    private static Render render; //Used by getInstance
    private GraphicsContext gc;

    // graphics context = main-canvas context
    // this is set by WindowController in its initialization
    public void setGraphicsContext(GraphicsContext gc){
        this.gc = gc;
    }

    //This class is currently singleton, since its instance needs to be accessed by both WindowController and the Model. May change...
    public static synchronized Render getInstance(){
        if (render == null){
            render = new Render();
        }
        return render;
    }

}
