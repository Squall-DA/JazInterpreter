/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jazinterpreter;

/**
 *
 * @author daniel
 */
public class JazInterpreter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Executer machineState = new Executer();
        machineState.mainEnvironment.CommandList = Parser.ToCommands(args[0]);
        machineState.mainEnvironment.LabelLocations = Parser.GetLabelLocations(machineState.mainEnvironment.CommandList);
        
        machineState.ExecuteInstructions();
        
    }
}
