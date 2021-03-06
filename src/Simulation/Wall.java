package Simulation;

import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.bullet.BulletAppState;
import com.jme3.scene.shape.Box;
import com.jme3.scene.Node;
import Components.PhysicsComponent;
import Components.GraphicsMeshComponent;

public class Wall implements Entity {
    GraphicsMeshComponent gfx;
    PhysicsComponent phyc;

    public Wall(float x, float y, float z, Node parent, BulletAppState bState) {
        final String boxName = "Wall: " + x + ", " + y + ", " + z;
        gfx = new GraphicsMeshComponent(this, parent, boxName, new Box(x, y, z), Assets.BRICK_WALL);
        phyc = new PhysicsComponent(getSpatial(), bState);
    }

    public void update(float tpf) {
    }

    public Spatial getSpatial() {
        return gfx.getSpatial();
    }

    public Identificator getIdentificator() {
        return Identificator.WALL;
    }

    public void collision() {
    }

    @Override
    public void setPosition(Vector3f pos) {
        //phyc.setPosition(pos);
    }

    public Vector3f getPosition(){
        return getSpatial().getLocalTranslation();
    }
}
