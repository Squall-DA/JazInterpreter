/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jazinterpreter;
import java.util.HashMap;
/**
 *
 * @author daniel
 */
public class LocalEnvironment {
    int backToCallingFunction;
    HashMap<String, Integer> Memory;
    HashMap<String, Integer> parentMemory;
    
    public LocalEnvironment(){
        backToCallingFunction = 0;
	Memory = new HashMap();
        parentMemory = new HashMap();
    }
}
