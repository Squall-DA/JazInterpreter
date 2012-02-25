/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jazinterpreter;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author daniel
 */
public class MainEnvironment {
    
    ArrayList<Node> CommandList;
    HashMap<String, Integer> Memory;
    HashMap<String, Integer> LabelLocations;
    
    public MainEnvironment(){
        CommandList = new ArrayList();
	Memory = new HashMap();
	LabelLocations = new HashMap();
    }
    
}
