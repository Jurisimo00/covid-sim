import Components.Lightning;
import Components.PathCalculator;
import java.util.ArrayList;
import java.util.List;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.renderer.RenderManager;

import Components.PathGenerator;
import Simulation.Assets;
import Simulation.Person;
import Simulation.Virus;

/**
 * @author chris, rob, jurismo, savi
 */
public class App extends SimpleApplication {
    // constants
    final int NUM_PERSON = 40;

    private BulletAppState bState;
    private List<Person> crowd;
    Virus v;

    public App() {
        //super(new FlyCamAppState());
    }

    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.Cyan);
        bState = new BulletAppState();
        //bState.setDebugEnabled(true);
        stateManager.attach(bState);
        Assets.loadAssets(assetManager);
        flyCam.setMoveSpeed(50);
        cam.setLocation(new Vector3f(20, 20, 5));
        createScene();
    }

    public static void main(String[] args) {
        new App().start();
    }

    @Override
    public void simpleUpdate(float tpf) {
        for (var p: crowd) {
            p.update(tpf);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }

    private void createScene() {
        // load city
        Node scene = (Node) assetManager.loadModel("Scenes/pogger" + ".j3o");
        scene.setName("Simulation_scene");
        scene.setLocalTranslation(new Vector3f(2, -10, 1));
        
        
        bState.getPhysicsSpace().addAll(scene);
        rootNode.attachChild(scene);
        
        var pathCalc = new PathCalculator((Node)rootNode.getChild("Simulation_scene"));

        // create an array of Person and fill it with 100 Person
        // every Person starts from a random point inside path generator
        crowd = new ArrayList<Person>();
        var pg = new PathGenerator(scene);
        for (int i = 0; i < NUM_PERSON; i++) {
            Person p = new Person(scene, pg.getRandomPoint(), this, pathCalc);
            crowd.add(p);
        }
        
        //v = new Virus(crowd, 2);
        Thread t = new Virus(crowd, 2);
        t.start();

        var a = new Lightning(this);
        a.setLight();
    }
}
